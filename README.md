# Android User Management Application

A clean architecture Android application that allows users to input their personal information and displays all saved users from a local database.

## Features

- **Input Screen**: Add new users with name, age, job title, and gender
- **Display Screen**: View all users saved in the local database

## Architecture

This project is built using Clean Architecture principles, structured into the following layers:

- **App**: Main application module
- **Core**: Core utilities and base classes
- **Data**: Data sources, repositories implementation, database
- **DI**: Dependency injection with Hilt
- **Domain**: Business logic, use cases, models
- **Presentation**: UI components, ViewModels, screens
- **SystemDesign**: Design system components, themes, styles

## Tech Stack

- **Kotlin**: Primary programming language
- **Coroutines & Flow**: For asynchronous operations
- **Hilt**: For dependency injection
- **Room**: Local database for storing user information
- **JUnit & Mockito**: Testing framework
- **MVVM + MVI**: Architecture pattern with unidirectional data flow

## Project Structure

```
app/
├── com.mahmoud.usermanagement/
│   ├── MainActivity
│   └── UserManagementApp
│
core/
├── base/
│   ├── BaseViewModel
│   └── ...
├── utils/
│   ├── FieldValidator
│   └── ...
│
data/
├── local/
│   ├── AppDatabase
│   ├── UserDao
│   └── ...
├── repository/
│   └── UserRepositoryImpl
│
di/
├── modules/
│   ├── DatabaseModule
│   ├── RepositoryModule
│   └── UseCaseModule
│
domain/
├── enums/
│   └── Gender
├── models/
│   └── User
├── repository/
│   └── UserRepository
├── usecases/
│   ├── GetAllUsersUseCase
│   └── InsertUserUseCase
│
presentation/
├── screens/
│   ├── addUser/
│   │   ├── AddUserContract
│   │   ├── AddUserScreen
│   │   └── AddUserViewModel
│   ├── display/
│   │   ├── DisplayContract
│   │   ├── DisplayScreen
│   │   └── DisplayViewModel
│
systemDesign/
├── components/
├── theme/
└── styles/
```

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 21+
- Kotlin 1.6.0+

### Installation

1. Clone the repository
```bash
git clone https://github.com/mahmoud947/DataCollectorApp.git
```

2. Open the project in Android Studio

3. Build and run the application

## Testing

The project includes unit tests for ViewModels, use cases, and repositories using JUnit and Mockito.

To run the tests:
```bash
./gradlew test
```

## Development Time

This task was completed in approximately 4.5 hours:
- 1 hour - Application architecture setup
- 2 hours - Core logic implementation (repositories, database, use cases)
- 1 hour - UI development
- 30 minutes - Testing

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

Mahmoud Kamal El-Din

## Repository

[https://github.com/mahmoud947/DataCollectorApp](https://github.com/mahmoud947/DataCollectorApp)

## Screens

![WhatsApp Image 2025-04-08 at 21 33 06](https://github.com/user-attachments/assets/8bb82820-e5d2-43fe-9ccd-4740c8a45be9)

![WhatsApp Image 2025-04-08 at 21 33 06 (1)](https://github.com/user-attachments/assets/1df205b2-9bc1-451c-ab43-225aa3539e25)
