# Medicos Here - Android App

A modern Android application built with Jetpack Compose for the Medicos Here healthcare platform.

## Features

### Welcome Screen
- Beautiful gradient background with app branding
- "Welcome to Medicos Here" message
- "Your trusted healthcare companion" tagline
- Sign Up and Login buttons with smooth navigation

### Sign Up Screen
- Full name, email, and password fields
- Password confirmation field
- Form validation
- Loading state with progress indicator
- Terms and conditions notice
- Back navigation to welcome screen

### Login Screen
- Email and password fields
- Remember me checkbox
- Forgot password link
- Social login options (Google, Apple)
- Sign up link for new users
- Loading state with progress indicator
- Back navigation to welcome screen

## Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Navigation Compose
- **Material Design**: Material 3 components
- **Build System**: Gradle with Kotlin DSL
- **Minimum SDK**: API 28 (Android 9.0)
- **Target SDK**: API 35 (Android 15)

## Project Structure

```
app/src/main/java/com/wassha/medicoshere/
├── MainActivity.kt                 # Main activity with navigation setup
├── ui/
│   ├── screens/
│   │   ├── WelcomeScreen.kt       # Welcome screen with app branding
│   │   ├── SignupScreen.kt        # User registration screen
│   │   └── LoginScreen.kt         # User login screen
│   └── theme/
│       ├── Color.kt               # Color definitions
│       ├── Theme.kt               # Material 3 theme setup
│       └── Type.kt                # Typography definitions
```

## Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Build and run the app

## Build Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install on connected device
./gradlew installDebug
```

## Design Features

- **Modern UI**: Material 3 design system
- **Responsive Layout**: Adapts to different screen sizes
- **Smooth Navigation**: Seamless transitions between screens
- **Accessibility**: Proper content descriptions and keyboard navigation
- **Dark/Light Theme**: Automatic theme switching based on system preference

## Future Enhancements

- API integration for authentication
- User profile management
- Healthcare service booking
- Push notifications
- Offline support
- Biometric authentication

## Screenshots

The app features a beautiful welcome screen with:
- Gradient background using primary colors
- Medical cross emoji as app icon
- Clear call-to-action buttons
- Smooth navigation between screens

## Contributing

This is the Android companion app for the Medicos Here NextJS web platform. The app provides a native mobile experience for healthcare services.

