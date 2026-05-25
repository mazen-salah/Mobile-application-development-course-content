# Firebase Intro

Firebase is Google's backend-as-a-service. Three things you'll use most: **Auth** (login), **Firestore** (cloud DB), **Storage** (files/images).

Free tier is generous and great for prototyping or indie apps.

## Setup

1. **Create a project** at [console.firebase.google.com](https://console.firebase.google.com)
2. Install the FlutterFire CLI: `dart pub global activate flutterfire_cli`
3. From your Flutter project: `flutterfire configure` — picks a Firebase project, generates `firebase_options.dart`
4. In `main.dart`:

```dart
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(options: DefaultFirebaseOptions.currentPlatform);
  runApp(const MyApp());
}
```

5. Add only the packages you need:

```bash
flutter pub add firebase_auth cloud_firestore firebase_storage
```

## Auth — email/password

```dart
import 'package:firebase_auth/firebase_auth.dart';

final auth = FirebaseAuth.instance;

// Sign up
try {
  final credential = await auth.createUserWithEmailAndPassword(
    email: 'mazen@example.com',
    password: 'strongPassword123',
  );
  print('Signed up: ${credential.user!.uid}');
} on FirebaseAuthException catch (e) {
  print('Error: ${e.message}');
}

// Sign in
await auth.signInWithEmailAndPassword(email: '...', password: '...');

// Sign out
await auth.signOut();

// Listen to auth state changes
auth.authStateChanges().listen((User? user) {
  if (user == null) {
    print('No user');
  } else {
    print('Signed in: ${user.email}');
  }
});
```

Hook this into your router to redirect to login when signed out.

## Other auth providers

Google, Apple, GitHub, phone OTP, anonymous — all supported, each needs minor setup (see Firebase docs). Common in production apps:

- **Google sign-in** for Android/web
- **Apple sign-in** for iOS (required if you support Google sign-in too)
- **Phone OTP** for emerging markets

## Firestore — NoSQL cloud DB

```dart
import 'package:cloud_firestore/cloud_firestore.dart';

final db = FirebaseFirestore.instance;

// Create / update a document
await db.collection('todos').add({
  'title': 'Buy bread',
  'done': false,
  'createdAt': FieldValue.serverTimestamp(),
  'userId': auth.currentUser!.uid,
});

// Read once
final snapshot = await db.collection('todos').get();
for (final doc in snapshot.docs) {
  print('${doc.id} → ${doc.data()}');
}

// Update
await db.collection('todos').doc('xyz123').update({'done': true});

// Delete
await db.collection('todos').doc('xyz123').delete();

// Reactive stream
final stream = db.collection('todos')
    .where('userId', isEqualTo: auth.currentUser!.uid)
    .orderBy('createdAt', descending: true)
    .snapshots();

stream.listen((snapshot) {
  for (final doc in snapshot.docs) {
    print(doc.data());
  }
});
```

The `.snapshots()` returns a `Stream<QuerySnapshot>` — your UI auto-updates as the cloud DB changes. Use with `StreamBuilder` or BLoC.

## Security rules

By default, Firestore is locked down. Add rules in the Firebase Console (Firestore → Rules):

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /todos/{todoId} {
      allow read, write: if request.auth != null
                        && request.auth.uid == resource.data.userId;
    }
  }
}
```

This means: only authenticated users can read/write **their own** todos. Test rules in the simulator before deploying.

## Storage — files

```dart
import 'package:firebase_storage/firebase_storage.dart';

final storage = FirebaseStorage.instance;

// Upload
final file = File('/path/to/photo.jpg');
final ref = storage.ref().child('avatars/${auth.currentUser!.uid}.jpg');
await ref.putFile(file);

// Get download URL
final url = await ref.getDownloadURL();

// Display in app
Image.network(url);
```

Storage also has security rules (separate from Firestore's). Lock by user UID.

## StreamBuilder pattern

Bridge Firebase streams to UI:

```dart
StreamBuilder<QuerySnapshot>(
  stream: FirebaseFirestore.instance
      .collection('todos')
      .orderBy('createdAt', descending: true)
      .snapshots(),
  builder: (context, snapshot) {
    if (snapshot.connectionState == ConnectionState.waiting) {
      return const CircularProgressIndicator();
    }
    if (snapshot.hasError) {
      return Text('Error: ${snapshot.error}');
    }
    final docs = snapshot.data!.docs;
    return ListView.builder(
      itemCount: docs.length,
      itemBuilder: (_, i) {
        final data = docs[i].data() as Map<String, dynamic>;
        return ListTile(title: Text(data['title']));
      },
    );
  },
)
```

For production, abstract Firestore behind a Repository and use BLoC — `StreamBuilder` in the UI is fine for prototypes.

## Other Firebase services

| Service | Use |
|---|---|
| **Cloud Functions** | Run Node.js/TypeScript on Firebase events (new user, doc change) |
| **Cloud Messaging (FCM)** | Push notifications |
| **Remote Config** | Feature flags, A/B testing |
| **Crashlytics** | Crash reports |
| **Analytics** | User behavior |
| **Performance Monitoring** | Latency & frame metrics |

All have Flutter plugins on pub.dev.

## Cost gotchas

- **Reads are billed.** Listening with `.snapshots()` counts reads every time the data changes — design with this in mind for large lists.
- **Storage egress** (downloads) is the biggest expense for image-heavy apps. Cache aggressively.
- **Cloud Functions** are pay-per-invocation — fine for occasional triggers, bad for high-frequency.

## Try it yourself

Build a "Group chat" minimal app:

1. Auth: anonymous sign-in (`auth.signInAnonymously()`)
2. Firestore: `/messages/{auto-id}` with `text`, `userId`, `createdAt`
3. UI:
    - StreamBuilder showing messages in real time
    - Text field + send button at the bottom that adds a doc
4. Open the app on two devices/emulators — messages appear on both instantly

[← Previous](10-persistence.md){ .md-button } [Next: Testing →](12-testing.md){ .md-button }
