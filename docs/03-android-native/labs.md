# Module 3 — Labs

Three labs of growing scope. Submit each to the course repo.

---

## Lab 1 — Profile & Lifecycle

Build a single-activity app that:

- Shows a Profile screen with name, bio, age (display only)
- Has an Edit button → opens `EditActivity` with the data via Intent extras
- `EditActivity` lets the user change the values, "Save" returns to Profile
- **Survives rotation** — use ViewModel + StateFlow for the data

Deliverable: GitHub repo with at least 2 commits.

---

## Lab 2 — Newsfeed (RecyclerView + Retrofit)

Build a real-feeling newsfeed.

### Requirements

- Use the public [JSONPlaceholder posts API](https://jsonplaceholder.typicode.com/posts)
- RecyclerView showing post title + body excerpt
- Tap a post → navigate to `DetailFragment` showing full body + user info (`/users/{id}`)
- Pull-to-refresh (use `SwipeRefreshLayout`)
- Loading spinner + error state
- Use ViewModel, StateFlow, Retrofit, Coroutines

### Bonus

- Cache posts in Room for offline access
- Use Navigation Component for the two screens
- Use Compose for the detail screen (XML for the list — practice interop)

---

## Lab 3 — Habit Tracker (full stack)

A complete app putting everything together.

### Requirements

- **Entities**: `Habit(id, name, color, createdAt)`, `Completion(id, habitId, date)`
- **Room DB** with two tables and a one-to-many relation
- **MVVM** — Activity/Compose UI, ViewModel, Repository, Room
- **UI**:
    - Home screen: list of habits with a checkbox for "done today"
    - Add habit (FAB → BottomSheet with name + color picker)
    - Long-press habit → delete with Snackbar undo
    - Detail screen: 30-day grid showing completion history
- **Material 3 theming** with dynamic color where available
- **Tests**: at least one unit test on the Repository (use an in-memory Room DB)

### Stretch

- Streak calculation (consecutive days)
- Notifications: daily reminder via WorkManager
- Export data as CSV via implicit Intent

Submit as a public GitHub repo with a README including screenshots and a description.

[← Previous: Jetpack Compose](13-jetpack-compose.md){ .md-button } [Next: Module 4 — Flutter →](../04-flutter/index.md){ .md-button .md-button--primary }
