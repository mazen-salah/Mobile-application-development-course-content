# Android Studio Setup

You need three things working before you can build an Android app: the IDE, an SDK, and a way to run the app (emulator or real device).

## 1. Install Android Studio

Download from **[developer.android.com/studio](https://developer.android.com/studio)**. The installer bundles:

- Android Studio IDE (based on IntelliJ)
- Android SDK
- Android Virtual Device (AVD) Manager + emulator

Pick the **stable** channel. ~3 GB download, ~10 GB after install (with one emulator image).

## 2. First-run setup wizard

When you launch for the first time it'll ask you to download the SDK. Accept defaults — you can add more later.

The wizard installs:

- **SDK Platforms** — one per Android version you want to target
- **SDK Tools** — build tools, platform tools, emulator
- **System Images** — emulator OS images

## 3. Create your first project

`File → New → New Project → Empty Activity`.

Fill in:

| Field | Value |
|---|---|
| Name | `Hello Android` |
| Package name | `com.example.helloandroid` |
| Save location | wherever |
| Language | **Kotlin** |
| Minimum SDK | API 24 (Android 7.0) — covers ~98% of devices |
| Build config language | Kotlin DSL (`.kts`) |

Click **Finish**. Gradle will download dependencies — first time can take 5–10 minutes. Have a coffee.

## 4. Set up an emulator

`Tools → Device Manager → Create Device`.

Choose:

- **Phone** category, **Pixel 7** (good middle-ground)
- **System Image** → pick the latest stable (e.g. API 34, Tiramisu)
- Click **Download** next to it (~1.5 GB)
- Accept defaults, click **Finish**

To launch: click the ▶ next to your device in Device Manager. The emulator window opens. First boot can take 1–2 minutes; subsequent boots ~20 seconds.

### Performance tips for the emulator

- **Enable hardware acceleration**: on Windows, install HAXM or use WHPX. On Mac/Linux, KVM. The emulator is unusably slow without it.
- Don't run two emulators at once unless you have 32+ GB of RAM.
- Snapshots: enable "Quick boot" in AVD settings — saves emulator state between sessions.

## 5. Run on a real device (recommended)

The emulator is fine for early lessons. For polish, performance testing, and especially Module 4 (Flutter), use a real device:

1. On your phone: **Settings → About phone → tap "Build number" 7 times** to enable Developer Options
2. **Settings → System → Developer options → enable USB debugging**
3. Plug into your computer
4. Accept the "Allow USB debugging?" dialog on the phone
5. The device appears in Android Studio's device dropdown

You can now run/debug directly on the phone — much faster than the emulator.

## 6. Run Hello World

Click the ▶ in Android Studio's toolbar. The app builds, installs, and launches on your selected device. You should see "Hello Android" centered on the screen.

If it works → you've conquered the hardest part of getting started.

## What's coming

We'll spend the next 13 lessons unpacking everything Android Studio just generated for you — the manifest, the activity, the resources, the build system, the lifecycle, layouts, and beyond.

## Troubleshooting

| Symptom | Fix |
|---|---|
| Gradle takes forever | Check your internet; first sync downloads ~500 MB |
| "Unsupported Java version" | `File → Settings → Build → Gradle → Gradle JDK` → pick 17 or 21 |
| Emulator won't start | Disable Hyper-V (or enable WHPX), update graphics drivers |
| App installs but crashes immediately | Check Logcat (bottom of Android Studio) — it shows the stack trace |
| "Could not resolve dependency" | `File → Sync Project with Gradle Files` |

## Try it yourself

1. Modify `MainActivity.kt` to change the displayed text from "Hello Android" to your name
2. Re-run — you should see your name without restarting the emulator (Android Studio supports **Instant Run** / **Apply Changes**)

[← Module overview](index.md){ .md-button } [Next: Anatomy of an App →](02-anatomy.md){ .md-button }
