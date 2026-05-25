# Module 5 — Capstone

<p align="center" markdown>
  ![TaskMate capstone](../assets/taskmate.png){ .hero-image width=100% }
</p>

You've made it. Now you build the real thing.

The capstone is **one app, shipped to the Play Store** (or TestFlight if you have a Mac and Apple Developer account). You can choose either stack. Both tracks build **TaskMate** — a task management app with social features.

## Pick your track

<div class="module-grid" markdown>

<div class="module-card" markdown>
### 🤖 Android Native
Built in Kotlin with Jetpack Compose. MVVM architecture, Room DB, Retrofit API, Firebase Auth.
[Project brief →](android-native-project.md)
</div>

<div class="module-card" markdown>
### 🐦 Flutter
Cross-platform. BLoC for state, dio for HTTP, Hive for cache, Firebase Auth.
[Project brief →](flutter-project.md)
</div>

</div>

Both tracks build a **task management app with social features** — different enough that you'll exercise everything you learned, similar enough that I can grade them fairly.

## Timeline

| Week | Milestone |
|---|---|
| 1 | Set up project, push initial commit, design schemas |
| 2 | Auth + main screens + navigation skeleton |
| 3 | CRUD operations + persistence |
| 4 | API integration + sync logic |
| 5 | Polish, write tests, accessibility pass |
| 6 | Build release, publish, submit |

Don't skip weeks. Compounding deadlines crush capstones.

## Grading

See **[Grading Rubric](rubric.md)** for the 100-point breakdown.

Top-level criteria:
- ✅ **Functionality** (30 pts) — does it work, does it match the spec?
- ✅ **Architecture** (20 pts) — proper separation of UI / business / data layers
- ✅ **State management** (15 pts) — correct, no leaks, survives lifecycle changes
- ✅ **Code quality** (15 pts) — readable, no warnings, formatted
- ✅ **Testing** (10 pts) — at least unit tests on business logic
- ✅ **Shipping** (10 pts) — published with screenshots, description, icon

## Submission

1. Push final code to a public GitHub repo
2. Tag a release `v1.0.0`
3. Include a README with: app description, screenshots, download link, build instructions
4. Open an issue in this course repo with title `[Capstone Submission] <Your Name>` and link to your repo

Good luck. Ship it.
