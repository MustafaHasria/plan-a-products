# Plan A Products

An Android application for browsing and viewing product listings with a modern, clean architecture approach.

## 📱 Features

- Browse products from an external API
- Filter products by categories
- View detailed product information
- Pull-to-refresh functionality
- Clean and intuitive Material Design UI
- Offline-ready architecture
- Comprehensive unit testing

## 🏗️ Architecture

This project follows **Clean Architecture** principles combined with **MVVM (Model-View-ViewModel)** pattern, ensuring separation of concerns, testability, and maintainability.

### Architecture Layers

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  (UI, ViewModels, Compose Screens)      │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Domain Layer                   │
│  (Use Cases, Business Logic, Models)    │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│           Data Layer                    │
│  (Repositories, API, Data Sources)      │
└─────────────────────────────────────────┘
```

#### 1. **Presentation Layer**
- **UI Components**: Built with Jetpack Compose for modern, declarative UI
- **ViewModels**: Manage UI state and handle business logic orchestration
- **Screen Components**: Reusable UI components (ProductItem, CategoryFilter, etc.)
- **State Management**: Unidirectional data flow using StateFlow

#### 2. **Domain Layer**
- **Use Cases**: Encapsulate business logic (GetProductsUseCase)
- **Domain Models**: Pure Kotlin data classes representing business entities
- **Repository Interfaces**: Define contracts for data operations
- **Business Rules**: Independent of frameworks and external dependencies

#### 3. **Data Layer**
- **Repository Implementation**: Concrete implementation of domain contracts
- **Data Sources**: API services using Retrofit
- **DTOs (Data Transfer Objects)**: API response models
- **Mappers**: Convert DTOs to domain models

## 🛠️ Tech Stack

### Core Technologies
- **Language**: Kotlin 2.0.21
- **Build Tool**: Gradle with Kotlin DSL
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 36

### Android Jetpack & Architecture Components
- **Jetpack Compose** (BOM 2024.09.00) - Modern declarative UI toolkit
- **Material Design 3** - Modern Material Design components
- **Navigation Compose** (2.8.2) - Type-safe navigation for Compose
- **ViewModel** (2.10.0) - Lifecycle-aware UI state management
- **Lifecycle Runtime KTX** (2.10.0) - Lifecycle-aware components
- **Activity Compose** (1.12.2) - Compose integration with Activities

### Dependency Injection
- **Dagger Hilt** (2.51.1) - Compile-time dependency injection framework
- **Hilt Navigation Compose** (1.2.0) - Hilt integration with Compose Navigation

### Networking
- **Retrofit** (2.9.0) - Type-safe HTTP client
- **OkHttp** (4.12.0) - Efficient HTTP client
- **OkHttp Logging Interceptor** - Network request/response logging
- **Gson** (2.10.1) - JSON serialization/deserialization

### Asynchronous Programming
- **Kotlin Coroutines** (1.7.3) - Asynchronous programming
- **Kotlin Flow** - Reactive stream processing

### Image Loading
- **Coil Compose** (2.5.0) - Modern image loading library optimized for Compose

### Testing
- **JUnit** (4.13.2) - Unit testing framework
- **MockK** (1.13.10) - Mocking library for Kotlin
- **Coroutines Test** (1.7.3) - Testing utilities for coroutines
- **Espresso** (3.7.0) - UI testing framework
- **Compose UI Test** - Testing utilities for Compose UI

## 📁 Project Structure

```
com.mustafa.products/
├── core/
│   ├── app/
│   │   ├── ProductsApplication.kt      # Application class with Hilt
│   │   └── di/                          # Dependency injection modules
│   │       ├── NetworkModule.kt         # Network dependencies
│   │       └── RepositoryModule.kt      # Repository dependencies
│   ├── component/                       # Reusable UI components
│   │   ├── CategoryFilter.kt
│   │   ├── CategoryHeader.kt
│   │   ├── ErrorMessage.kt
│   │   ├── LoadingIndicator.kt
│   │   └── SwipeRefresh.kt
│   ├── functions/                       # Common utility functions
│   ├── network/                         # Network configuration
│   │   └── NetworkInterceptor.kt
│   ├── rout/                            # Navigation setup
│   │   ├── NavGraph.kt
│   │   ├── ProductsNavGraph.kt
│   │   └── Routes.kt
│   └── theme/                           # App theming
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
├── data/
│   ├── data_source/
│   │   └── ProductApiService.kt         # Retrofit API interface
│   ├── mapper/
│   │   └── ProductMapper.kt             # DTO to Domain model mapper
│   ├── repository/
│   │   └── ProductRepositoryImpl.kt     # Repository implementation
│   └── response/                        # API response models (DTOs)
│       ├── ProductResponseDto.kt
│       └── RatingResponseDto.kt
├── domain/
│   ├── model/                           # Domain entities
│   │   ├── Product.kt
│   │   └── Rating.kt
│   ├── repository/
│   │   └── ProductRepository.kt         # Repository interface
│   └── use_case/
│       └── GetProductsUseCase.kt        # Business logic use case
└── presentation/
    ├── components/
    │   └── ProductItem.kt               # Product list item component
    ├── screens/
    │   ├── ProductsScreen.kt            # Main products list screen
    │   └── ProductDetailScreen.kt       # Product detail screen
    └── viewmodel/
        └── ProductsViewModel.kt         # Products state management
```

👤 Author

**Mustafa Hasria**
- GitHub: [@MustafaHasria](https://github.com/MustafaHasria)

---

**Built with ❤️ using Clean Architecture and Modern Android Development practices**

