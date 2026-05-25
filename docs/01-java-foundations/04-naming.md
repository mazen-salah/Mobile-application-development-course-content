# Naming Conventions

You'll write code far more often than you'll think up clever logic. **Names** dominate how readable your code is.

## The rules (Java enforces these)

A valid name:

- Must start with a letter, `$`, or `_` — never a digit
- Can contain letters, digits, `$`, `_`
- Is **case-sensitive** (`age` and `Age` are different)
- Cannot be a reserved word (`int`, `class`, `if`, `new`, etc.)

```java
int age;          // valid
int _count;       // valid (but unusual)
int $price;       // valid (but rare)
int 1stPlace;     // INVALID — starts with digit
int my-var;       // INVALID — hyphens not allowed
int class;        // INVALID — reserved word
```

## The conventions (the community enforces these)

These aren't rules the compiler checks — but every Java codebase on Earth follows them, and breaking them will get your PR rejected.

| Thing | Convention | Example |
|---|---|---|
| Variable | `camelCase` | `firstName`, `totalAmount` |
| Method | `camelCase`, usually a verb | `getUserById()`, `calculateTotal()` |
| Class | `PascalCase`, usually a noun | `Customer`, `OrderService` |
| Constant | `UPPER_SNAKE_CASE` | `MAX_RETRIES`, `PI` |
| Package | `lowercase.dotted` | `com.summationworks.cart` |

## Make names self-documenting

**Bad:**

```java
int d;         // what's d?
int s;         // sum? size? speed?
List<Item> l;  // please no
```

**Good:**

```java
int daysSinceSignup;
int totalScore;
List<Item> cartItems;
```

Yes, longer names. Yes, you'll type more. **Reading is 10× more frequent than writing** — optimize for the reader.

## Acronyms

Treat them like words: capitalize first letter, lowercase the rest in `camelCase` contexts.

```java
String userId;      // not userID
URL apiUrl;         // not apiURL
HttpClient client;  // not HTTPClient
```

(Java's own standard library breaks this rule sometimes, e.g. `URL`. Don't copy that.)

## Try it yourself

Rename these variables to follow conventions:

```java
int A;
String first_name;
boolean IS_LOGGED_IN;       // hint: not a constant
final int max_users = 100;  // hint: this IS a constant
```

??? success "Solution"
    ```java
    int a;                  // single-letter is OK if scope is tiny (a loop)
    String firstName;
    boolean isLoggedIn;
    final int MAX_USERS = 100;
    ```

## Common mistakes

!!! warning "Hungarian notation"
    Don't prefix names with type info (`strName`, `iCount`, `bIsActive`). The IDE shows you the type — and Java is strongly typed, so misuse is caught at compile time.

!!! warning "Single letters outside tiny scopes"
    `i`, `j` in a `for` loop are fine. `x`, `y` for coordinates are fine. Anything else: use a real word.

[← Previous: Comments](03-comments.md){ .md-button } [Next: Data Types →](05-data-types.md){ .md-button }
