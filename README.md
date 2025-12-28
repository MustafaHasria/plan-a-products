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

## 🔑 Key Design Patterns

### 1. **Repository Pattern**
Abstracts data sources and provides a clean API for data access to the rest of the application.

### 2. **Use Case Pattern**
Encapsulates business logic in single-responsibility classes, making code more testable and maintainable.

### 3. **Dependency Injection**
Using Hilt for compile-time dependency injection, promoting loose coupling and easier testing.

### 4. **Observer Pattern**
Using Kotlin Flow and StateFlow for reactive data streams and state management.

### 5. **Mapper Pattern**
Separating DTOs from domain models, allowing flexibility in API changes without affecting business logic.

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or higher
- Android SDK with API level 36

### Installation

1. Clone the repository:
```bash
git clone https://github.com/MustafaHasria/plan-a-products.git
cd plan-a-products
```

2. Open the project in Android Studio

3. Sync Gradle dependencies

4. Run the app on an emulator or physical device

### Building the Project

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## 🧪 Testing

The project includes comprehensive unit tests for:
- **ViewModels**: Testing state management and business logic
- **Use Cases**: Testing business rules
- **Repositories**: Testing data operations with mocked dependencies

Run all tests:
```bash
./gradlew test
```

## 📦 Dependencies Management

Dependencies are managed using Gradle Version Catalogs (`libs.versions.toml`), providing:
- Centralized version management
- Type-safe accessors
- Easy dependency updates

## 🎨 UI/UX Features

- **Material Design 3**: Modern and consistent UI
- **Dark Theme Support**: Automatic theme switching
- **Responsive Layout**: Adapts to different screen sizes
- **Pull-to-Refresh**: Intuitive data refresh mechanism
- **Category Filtering**: Easy product filtering by category
- **Smooth Navigation**: Seamless transitions between screens

## 📝 Code Quality

- **Kotlin Best Practices**: Following Kotlin coding conventions
- **Clean Code Principles**: Readable, maintainable, and self-documenting code
- **SOLID Principles**: Applied throughout the architecture
- **Separation of Concerns**: Clear boundaries between layers
- **Testability**: Architecture designed for easy testing

## 🔄 Data Flow

```
User Action → ViewModel → Use Case → Repository → API Service
                ↓                                      ↓
            StateFlow ← Domain Model ← Mapper ← DTO Response
                ↓
              UI Update
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👤 Author

**Mustafa Hasria**
- GitHub: [@MustafaHasria](https://github.com/MustafaHasria)

## 🙏 Acknowledgments

- [FakeStore API](https://fakestoreapi.com/) - Product data source
- Android Jetpack Team - For excellent Android development tools
- Kotlin Team - For the amazing Kotlin language

---

**Built with ❤️ using Clean Architecture and Modern Android Development practices**

