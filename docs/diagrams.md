# Diagrams Reference

A consolidated view of every diagram in the course. Useful for quick visual recall before exams or interviews.

## Activity Lifecycle (Android)

```mermaid
stateDiagram-v2
    [*] --> Created: onCreate()
    Created --> Started: onStart()
    Started --> Resumed: onResume()
    Resumed --> Paused: onPause()
    Paused --> Stopped: onStop()
    Stopped --> Destroyed: onDestroy()
    Destroyed --> [*]
    Paused --> Resumed: onResume()
    Stopped --> Started: onRestart() → onStart()
```

## Fragment Lifecycle (Android)

```mermaid
stateDiagram-v2
    [*] --> Attached: onAttach()
    Attached --> Created: onCreate()
    Created --> ViewCreated: onCreateView() → onViewCreated()
    ViewCreated --> Started: onStart()
    Started --> Resumed: onResume()
    Resumed --> Paused: onPause()
    Paused --> Stopped: onStop()
    Stopped --> ViewDestroyed: onDestroyView()
    ViewDestroyed --> Destroyed: onDestroy()
    Destroyed --> Detached: onDetach()
    Detached --> [*]
```

## MVVM Architecture

```mermaid
flowchart LR
    V[View<br/>Activity / Fragment / Composable] -->|observes| VM[ViewModel<br/>holds UI state]
    VM -->|requests data| M[Model / Repository]
    M -->|fetches| API[Remote API]
    M -->|caches| DB[(Local DB)]
    M -->|returns data| VM
    VM -->|emits state| V

    style V fill:#00D4FF,color:#0B1A3E
    style VM fill:#0B1A3E,color:#fff
    style M fill:#0B1A3E,color:#fff
```

## Flutter Widget Tree

```mermaid
flowchart TD
    App[MaterialApp] --> Scaffold
    Scaffold --> AppBar
    Scaffold --> Body[Body: Column]
    Body --> Text1[Text — Title]
    Body --> Row
    Row --> Icon
    Row --> Text2[Text — Subtitle]
    Body --> List[ListView.builder]
    List --> Card1[Card]
    List --> Card2[Card]
    List --> Card3[Card]
    Scaffold --> FAB[FloatingActionButton]

    style App fill:#00D4FF,color:#0B1A3E
    style Scaffold fill:#0B1A3E,color:#fff
```

## BLoC Pattern (Flutter)

```mermaid
flowchart LR
    UI[Widget] -->|adds Event| Bloc
    Bloc -->|business logic| Repo[Repository]
    Repo -->|data| Bloc
    Bloc -->|emits State| UI
    UI -->|rebuilds| UI

    style UI fill:#00D4FF,color:#0B1A3E
    style Bloc fill:#0B1A3E,color:#fff
    style Repo fill:#0B1A3E,color:#fff
```

## Navigation Component (Android)

```mermaid
flowchart LR
    Home[HomeFragment] -->|action_home_to_detail| Detail[DetailFragment]
    Detail -->|action_detail_to_edit| Edit[EditFragment]
    Edit -->|popBackStack| Detail
    Detail -->|popBackStack| Home
    Home -->|action_home_to_settings| Settings[SettingsFragment]
```

## Room Database Layers (Android)

```mermaid
flowchart TD
    UI[UI Layer] --> VM[ViewModel]
    VM --> Repo[Repository]
    Repo --> DAO[DAO Interface]
    DAO --> DB[Room Database<br/>SQLite]
    Repo --> API[Retrofit Service]
    API --> Net[Network]

    style DB fill:#00D4FF,color:#0B1A3E
    style Net fill:#0B1A3E,color:#fff
```

## State Management Spectrum (Flutter)

```mermaid
flowchart LR
    A[setState] -->|outgrows| B[InheritedWidget / Provider]
    B -->|complexity grows| C[Riverpod]
    B -->|enterprise patterns| D[BLoC / Cubit]
    D -->|massive apps| E[Redux / MobX]

    style A fill:#00D4FF,color:#0B1A3E
    style D fill:#00D4FF,color:#0B1A3E
```

## Course Learning Path

```mermaid
flowchart LR
    A[Java Foundations] --> B[Kotlin]
    B --> C[Android Native]
    A --> D[Dart-via-Flutter]
    D --> E[Flutter]
    C --> F[Capstone]
    E --> F[Capstone]

    style A fill:#0B1A3E,color:#fff
    style B fill:#0B1A3E,color:#fff
    style C fill:#00D4FF,color:#0B1A3E
    style D fill:#0B1A3E,color:#fff
    style E fill:#00D4FF,color:#0B1A3E
    style F fill:#00D4FF,color:#0B1A3E
```
