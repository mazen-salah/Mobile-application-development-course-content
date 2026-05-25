# Room Database

Room is Google's official ORM for SQLite — turns SQL queries into Kotlin functions, with compile-time validation of your schema and queries.

## Three core annotations

| Annotation | Goes on | What it does |
|---|---|---|
| `@Entity` | Data class | "This represents a table" |
| `@Dao` | Interface | "Methods here are SQL queries" |
| `@Database` | Abstract class | "Wire it all together" |

## Setup

```kotlin
dependencies {
    val room = "2.6.1"
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    ksp("androidx.room:room-compiler:$room")
}

// in plugins block:
plugins {
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
}
```

## Entity

```kotlin
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isDone: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
```

Each field becomes a column. The data class is the entity.

## DAO (Data Access Object)

```kotlin
@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getById(id: Int): Task?

    @Insert
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM tasks WHERE isDone = 1")
    suspend fun deleteCompleted()
}
```

`@Query` validates the SQL at compile time — typos in the SQL or column names error out before you run.

## Database

```kotlin
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app.db"
                ).build()
                instance = db
                db
            }
        }
    }
}
```

The singleton pattern prevents creating multiple connections to the same SQLite file.

## Using it

```kotlin
class TaskViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = AppDatabase.get(app).taskDao()

    val tasks: StateFlow<List<Task>> = dao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(title: String) {
        viewModelScope.launch {
            dao.insert(Task(title = title))
        }
    }

    fun toggleDone(task: Task) {
        viewModelScope.launch {
            dao.update(task.copy(isDone = !task.isDone))
        }
    }
}
```

Note `dao.getAll()` returns `Flow<List<Task>>` — Room emits a new list **every time the table changes**. Your UI auto-updates when you insert/delete/update from anywhere.

## Repository pattern

For testability and architecture, wrap DAOs in a Repository:

```kotlin
class TaskRepository(private val dao: TaskDao) {
    val tasks = dao.getAll()
    suspend fun add(title: String) = dao.insert(Task(title = title))
    suspend fun delete(task: Task) = dao.delete(task)
}
```

Then the ViewModel takes a Repository instead of a DAO. Now the ViewModel can be unit-tested with a fake Repository.

## Relationships

### One-to-many

```kotlin
@Entity
data class Project(@PrimaryKey val id: Int, val name: String)

@Entity(foreignKeys = [ForeignKey(
    entity = Project::class,
    parentColumns = ["id"],
    childColumns = ["projectId"],
    onDelete = ForeignKey.CASCADE
)])
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val projectId: Int,
    val title: String
)

data class ProjectWithTasks(
    @Embedded val project: Project,
    @Relation(parentColumn = "id", entityColumn = "projectId")
    val tasks: List<Task>
)

@Query("SELECT * FROM Project")
fun getProjectsWithTasks(): Flow<List<ProjectWithTasks>>
```

Room generates the JOIN for you.

## Type converters

Room handles primitives + Strings natively. For other types (Date, enums, lists), define converters:

```kotlin
class Converters {
    @TypeConverter fun fromDate(d: Date?): Long? = d?.time
    @TypeConverter fun toDate(t: Long?): Date? = t?.let(::Date)
}

@Database(entities = [...], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() { ... }
```

## Migrations

When you change the schema (add a column, rename), Room throws unless you provide a migration:

```kotlin
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE tasks ADD COLUMN priority INTEGER NOT NULL DEFAULT 0")
    }
}

Room.databaseBuilder(...)
    .addMigrations(MIGRATION_1_2)
    .build()
```

In development, you can call `.fallbackToDestructiveMigration()` to wipe the DB on schema mismatch. **Never do this in production.**

## Inspecting the DB

Use **App Inspection** in Android Studio (`View → Tool Windows → App Inspection`). It shows tables and lets you run SQL while the app runs. Game-changer for debugging.

## Try it yourself

Build a "Notes" app:

- `Note(id, title, body, createdAt)` entity
- DAO with: `getAll`, `getById`, `insert`, `delete`, `searchByTitle(query)`
- ViewModel that exposes notes as StateFlow
- UI: RecyclerView of notes, FloatingActionButton to add, tap to delete

[← Previous](09-viewmodel-livedata.md){ .md-button } [Next: Retrofit →](11-retrofit-networking.md){ .md-button }
