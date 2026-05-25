# Publishing to Stores

Shipping is the final 20% of effort that delivers 80% of the value. Skip it and your code lives on your laptop forever.

## Pre-publish checklist

Before either store, you need:

- **App icon** — 1024×1024 source PNG (generated for all sizes via `flutter_launcher_icons`)
- **Splash screen** — branded loading screen (`flutter_native_splash` package)
- **App name** — final, not "Untitled" or "my_app"
- **App ID / bundle ID** — reverse-DNS format like `com.summationworks.myapp`. Unique forever; can't be changed after publish.
- **Version** — `1.0.0+1` in `pubspec.yaml`. Bump for every release.
- **Screenshots** for each store
- **Privacy policy URL** — required even if you don't collect data
- **App description, keywords, categories**

## Generate launcher icons

```yaml title="pubspec.yaml"
dev_dependencies:
  flutter_launcher_icons: ^0.13.1

flutter_launcher_icons:
  android: true
  ios: true
  image_path: "assets/icon-1024.png"
  adaptive_icon_background: "#0B1A3E"
  adaptive_icon_foreground: "assets/icon-foreground.png"
```

```bash
dart run flutter_launcher_icons
```

## Generate splash

```yaml
dev_dependencies:
  flutter_native_splash: ^2.3.10

flutter_native_splash:
  color: "#0B1A3E"
  image: assets/splash.png
  android_12:
    color: "#0B1A3E"
    image: assets/splash-android12.png
```

```bash
dart run flutter_native_splash:create
```

## Android — Play Store

### 1. Sign your app

```bash
keytool -genkey -v -keystore upload-keystore.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias upload
```

**Save the keystore + password securely.** If you lose it, you can't push updates — you'd have to publish a new app.

Create `android/key.properties` (gitignored!):

```
storePassword=...
keyPassword=...
keyAlias=upload
storeFile=../upload-keystore.jks
```

Wire it up in `android/app/build.gradle` (see [official guide](https://docs.flutter.dev/deployment/android)).

### 2. Build a release App Bundle

```bash
flutter build appbundle --release
```

Output: `build/app/outputs/bundle/release/app-release.aab`. That's what you upload.

### 3. Play Console setup

1. Create an account at [play.google.com/console](https://play.google.com/console) ($25 one-time)
2. Create a new app
3. Fill in store listing: name, description, screenshots, icon, feature graphic (1024×500)
4. Upload your `.aab` to **Internal testing** first (instant rollout to your test accounts)
5. After verifying: promote to **Production**
6. First release goes through ~3-day review

### 4. Subsequent releases

Bump `version: 1.0.1+2` (semver + build number), rebuild AAB, upload to a release track. Approval is usually < 24 hours.

## iOS — App Store

iOS publishing is more involved than Android.

### Requirements

- Mac with Xcode (mandatory — no way around this)
- Apple Developer account ($99/yr)
- App ID registered in Apple Developer portal
- Provisioning profiles + signing certs

### Build

```bash
flutter build ipa --release
```

Output: `build/ios/ipa/Runner.ipa`.

### Upload

Open Xcode, **Window → Organizer**, drag the `.ipa`. It uploads to App Store Connect.

In **App Store Connect**:

1. Create the app entry
2. Fill listing: name, description, screenshots (per device size), keywords
3. Submit for review
4. Apple's review is 1–3 days, more rigorous than Google's

## Web

```bash
flutter build web --release
```

Output: `build/web/`. Deploy to anything that serves static files:

- **GitHub Pages** (free)
- **Vercel** / **Netlify** (free)
- **Firebase Hosting** (`firebase deploy`)

## Versioning strategy

Semantic versioning: `MAJOR.MINOR.PATCH`

- `1.0.0+1` — first release
- `1.0.1+2` — bug fix
- `1.1.0+3` — new features, backwards-compatible
- `2.0.0+4` — breaking changes

The `+1` is the build number. Increment for **every** upload to a store, regardless of version.

## Build flavors (dev / staging / prod)

```bash
flutter build apk --flavor dev --target lib/main_dev.dart
flutter build apk --flavor prod --target lib/main_prod.dart
```

With different `main_*.dart` files setting different Firebase configs, API URLs, app names. Required for serious teams to test before shipping to prod.

## Crash reporting & analytics

Before publishing publicly, integrate:

- **Sentry** or **Firebase Crashlytics** — catch crashes in the wild
- **Firebase Analytics** or **Mixpanel** — what features get used
- **Performance monitoring** — Firebase Performance, Sentry Performance

Without these, you ship blind.

## What to put in your store listing

| Slot | Tips |
|---|---|
| Title | Include 1–2 keywords without being spammy |
| Short description | One sentence — what does this do and for whom? |
| Full description | Lead with benefits, list features, social proof |
| Screenshots | First 2 are most important — they decide whether users tap "Install" |
| Feature graphic | Hero image, your brand colors, big readable text |
| Reviews | Reply to every early review |

## Common rejections

| Reason | Fix |
|---|---|
| Missing privacy policy | Generate one with a free tool, host on GitHub Pages |
| Permissions you don't use | Remove unused permissions from manifest |
| App crashes on review | Test on a fresh device with a fresh account before submitting |
| Misleading screenshots | Match real UI exactly |
| Apple specific: minor design issues, references to other platforms ("get it on Google Play") | Read the rejection email carefully, fix and resubmit |

## After publish

- Watch crash reports daily for the first week
- Read every review
- Ship updates often (weekly or bi-weekly for the first 3 months)
- Use Remote Config / feature flags so you can disable broken features without a re-release

## Try it yourself

For your capstone (next module):

1. Generate icons and splash with the tools above
2. Build a release APK and install on your phone via `adb install`
3. Create a Play Console account, upload to internal testing
4. Get one friend to install via internal testing link

Your app is now live in some sense — congrats, you're an app publisher.

[← Previous](12-testing.md){ .md-button } [Next: Labs →](labs.md){ .md-button }
