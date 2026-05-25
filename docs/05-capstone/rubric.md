# Grading Rubric

100 points total. Below are the breakdowns.

---

## Functionality (30 points)

| Criterion | Points |
|---|---|
| All required screens present and reachable | 5 |
| Auth flow works (sign up, sign in, sign out) | 5 |
| CRUD on local data works | 5 |
| Cloud sync works in real time | 5 |
| Sharing / friends works | 5 |
| App is usable offline (local cache, sync on reconnect) | 5 |

Disqualifiers:

- Crashes on the golden path: **−20**
- Missing entire required feature: **−10 per**

---

## Architecture (20 points)

| Criterion | Points |
|---|---|
| Clear separation of UI / business / data | 6 |
| Single Source of Truth (local DB, not duplicated) | 4 |
| Dependency injection (Hilt for Android, get_it or constructor injection for Flutter) | 3 |
| ViewModels / Blocs are unit-testable (no direct framework refs) | 4 |
| Repository pattern correctly used | 3 |

Disqualifiers:

- Business logic in the UI layer: **−5 per screen**
- DB access from UI/ViewModel directly: **−5**

---

## State management (15 points)

| Criterion | Points |
|---|---|
| Survives configuration changes (rotation, dark mode toggle) | 4 |
| Survives process death (saved state restored) | 4 |
| No memory leaks (use the Android Studio profiler / Flutter DevTools) | 4 |
| State updates are correct and predictable | 3 |

---

## Code quality (15 points)

| Criterion | Points |
|---|---|
| Lint-clean (no warnings, no `unused_*`) | 3 |
| Code is formatted consistently (`ktlint` / `dart format`) | 2 |
| Naming is descriptive and idiomatic | 3 |
| No dead/commented-out code | 2 |
| No `TODO` comments left for "later" — file an issue instead | 2 |
| Functions/classes are small and focused | 3 |

---

## Testing (10 points)

| Criterion | Points |
|---|---|
| Unit tests on all ViewModels / Blocs | 4 |
| Repository tests with mocked dependencies | 3 |
| At least one widget/UI test for a key screen | 3 |

Bonus: integration test on the golden path: **+2**

---

## Shipping (10 points)

| Criterion | Points |
|---|---|
| Built and signed release artifact (AAB / IPA) | 2 |
| Published to internal testing track (Play Store or TestFlight) | 3 |
| Repo README has: description, screenshots, install instructions | 2 |
| Custom launcher icon, splash, app name (not default) | 2 |
| Privacy policy linked in store listing | 1 |

---

## Bonus (up to +10)

- **+3**: Significant stretch goal completed (web build, widget, push notifications)
- **+2**: Beautiful UI that goes beyond "out-of-the-box Material"
- **+2**: Accessibility audit completed and addressed
- **+2**: Performance tuning evidence (60fps everywhere, no jank)
- **+1**: Localized into a second language

---

## How submissions are evaluated

1. I read the README first. If it doesn't tell me what the app is and how to run it in 30 seconds, that's a deduction.
2. I install the app from the AAB or store link. If it doesn't open or asks for nonsense permissions, that's a deduction.
3. I tap through the golden path. Crashes are heavy penalties.
4. I open the repo. I read the package structure, the build files, the tests.
5. I run the tests. They must pass.
6. I check the codebase for the architecture criteria above.

**No partial credit for "I planned to do X but ran out of time."** Ship what you have, document what's missing in your README.

[← Capstone overview](index.md){ .md-button .md-button--primary }
