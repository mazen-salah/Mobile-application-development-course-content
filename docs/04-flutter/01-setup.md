# Flutter Setup

Flutter needs three things: the Flutter SDK, an IDE plugin, and a way to run the app.

## 1. Install the Flutter SDK

Follow the official [install guide](https://docs.flutter.dev/get-started/install) — pick your OS.

### Windows (recommended path)

```powershell
# In an admin PowerShell or via Git Bash:
git clone https://github.com/flutter/flutter.git -b stable C:\flutter
# Add C:\flutter\bin to your PATH
```

Or use **fvm** (Flutter Version Manager) to manage multiple Flutter versions cleanly.

### macOS

```bash
brew install --cask flutter
```

### Linux

```bash
sudo snap install flutter --classic
```

## 2. Run `flutter doctor`

This checks your setup:

```
$ flutter doctor

Doctor summary (to see all details, run flutter doctor -v):
[✓] Flutter (Channel stable, 3.19.x)
[✓] Windows Version (10.0.22631)
[✓] Android toolchain - develop for Android devices (Android SDK version 34.0.0)
[!] Chrome - develop for the web
[✓] Visual Studio - develop Windows apps
[✓] Android Studio (version 2023.1)
[✓] VS Code (version 1.85)
[✓] Connected device (3 available)
[✓] Network resources
```

Fix anything with `[!]` or `[✗]`. Common issues:

- **Android licenses not accepted** → `flutter doctor --android-licenses` (accept all)
- **Chrome missing** → install Chrome for web development support (optional)
- **No Visual Studio** → install Visual Studio Build Tools for Windows desktop builds (optional)

## 3. Editor

Two equally good options:

### VS Code (lightweight)

Install extensions:

- **Flutter** (by Dart Code) — includes Dart support
- **Awesome Flutter Snippets** — speeds up common widgets

### Android Studio (heavier, more features)

`File → Settings → Plugins → Marketplace → search "Flutter" → install`. Restart.

VS Code is faster to start up; Android Studio has better refactoring and integrated emulator management. **Pick one** and stick with it for a while.

## 4. Create your first app

```bash
flutter create my_first_app
cd my_first_app
flutter run
```

`flutter run` builds, installs, and launches on whatever device is connected. If multiple devices: `flutter run -d chrome` or `flutter run -d <device-id>` (find IDs with `flutter devices`).

You should see the default counter app. Press the FAB to increment.

## 5. Hot reload — the killer feature

While the app runs, edit `lib/main.dart`. Change the AppBar title from "Flutter Demo Home Page" to "Hello Flutter". **Save the file.** The change appears on screen in ~500 ms **without restarting the app or losing state**.

Two flavors:

- **Hot Reload (`r`)** — fastest, preserves state
- **Hot Restart (`R`)** — restarts the app, resets state

In your terminal you can press `r` / `R` / `q` (quit).

In VS Code, hot reload happens automatically on save (configurable).

## 6. Project structure

```
my_first_app/
├── lib/                  # Dart source code (where you'll spend 99% of your time)
│   └── main.dart         # entry point
├── android/              # Android-specific code (rarely touched)
├── ios/                  # iOS-specific code (rarely touched)
├── web/                  # web build config
├── test/                 # unit + widget tests
├── pubspec.yaml          # dependencies + assets — your "package.json"
└── pubspec.lock          # locked dependency versions
```

99% of your work is in `lib/` and `pubspec.yaml`.

## 7. Adding dependencies

```yaml title="pubspec.yaml"
dependencies:
  flutter:
    sdk: flutter
  http: ^1.2.0
  shared_preferences: ^2.2.2
```

Run `flutter pub get` to install. Or use VS Code's quick-fix to add.

Browse packages at **[pub.dev](https://pub.dev)** — Flutter's npm.

## 8. Pick your development device

- **Android emulator** — easiest if you already use Android Studio
- **iOS simulator** — Mac only, faster than Android emulator
- **Chrome** — instant, but constrained (no native plugins, no camera in some setups)
- **Real Android device** — best for performance testing
- **Real iPhone** — requires a paid Apple Developer account ($99/yr) for sideloading

## Common commands

```bash
flutter doctor               # health check
flutter devices              # list connected devices
flutter run                  # build + install + launch
flutter run -d chrome        # specifically web
flutter pub get              # install dependencies
flutter pub upgrade          # update to latest compatible
flutter pub outdated         # show what could be upgraded
flutter clean                # nuke build artifacts (fixes weird issues)
flutter build apk            # production Android build
flutter build appbundle      # production Play Store build
flutter build ios            # production iOS build (Mac only)
```

## What's coming

Next lesson: a quick tour of Dart, the language Flutter is written in. Coming from Kotlin/Java, you'll be fluent in an hour.

[← Module overview](index.md){ .md-button } [Next: Dart Essentials →](02-dart-essentials.md){ .md-button }
