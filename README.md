# User Display Page App- Modern Android Architecture

 <!-- ABOUT THE PROJECT -->
## About The Project
This Sample Application was developed by using Jetpack Compose. Clean architecture and best practice are followed. You can find everything you need in the Jetpack Compose project within the application.

* Architecture;
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

* UI
    * [Compose](https://developer.android.com/jetpack/compose) declarative UI framework


* Tech/Tools
    * [Kotlin](https://kotlinlang.org/) 100% coverage
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://developer.android.com/kotlin/flow) for async operations
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)  - Easier way to incorporate Dagger DI into Android application.
    * [Jetpack](https://developer.android.com/jetpack)
        * [Compose](https://developer.android.com/jetpack/compose)
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that stores, exposes and manages UI state
    * [Retrofit](https://square.github.io/retrofit/) A type-safe HTTP client for Android and Java.
    * [Coil](https://github.com/coil-kt/coil) for image loading
    * [Paging3]() - Load and display small chunks of data at a time.
    * [Room]() - Persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

## Clean Architecture
   * Data - Here we put the logic of bringing data either from local source or server.
   * Domain - Here we put the logic of business: convert, filter, mix and sort raw data that comes from Data layer to be ready and easy to handle in Presentationlayer. 
   * Presentation - Here we put the UI components and the logic that related to user interactions or navigation in order to get data from the user.



