# Logo Options for Medicos Here App

I've created three different logo designs for your app. You can easily switch between them by changing one line in the code.

## Available Logo Options:

### 1. **ic_medicos_logo.xml** - Medical Cross with Heart
- Features: Medical cross with a heart symbol
- Colors: Blue cross (#2196F3) with pink heart (#FF4081)
- Style: Detailed and colorful

### 2. **ic_medicos_logo_simple.xml** - Simple Medical Cross with Heart
- Features: Medical cross with a smaller heart accent
- Colors: Dark blue cross (#1976D2) with pink heart (#E91E63)
- Style: Clean and professional

### 3. **ic_medicos_logo_minimal.xml** - Minimalist Medical Cross
- Features: Simple medical cross only
- Colors: Blue cross (#2196F3)
- Style: Minimalist and clean

### 4. **ic_medicos_logo_custom.xml** - Your Custom Logo ⭐ **CURRENTLY ACTIVE**
- Features: Vector representation of your Medicos Here logo
- Style: Professional healthcare branding with medical cross design
- Format: Vector drawable (scalable and lightweight)

## How to Switch Logos:

To change the logo, simply edit this line in `app/src/main/java/com/wassha/medicoshere/ui/screens/WelcomeScreen.kt`:

```kotlin
// Current (your custom logo):
painter = painterResource(id = R.drawable.ic_medicos_logo_custom)

// To use the minimalist logo:
painter = painterResource(id = R.drawable.ic_medicos_logo_minimal)

// To use the detailed logo:
painter = painterResource(id = R.drawable.ic_medicos_logo)

// To use the simple logo:
painter = painterResource(id = R.drawable.ic_medicos_logo_simple)
```

## Adding Your Own Logo:

If you want to use your own logo:

1. **Add your logo file** to `app/src/main/res/drawable/`
2. **Name it** something like `ic_medicos_logo_custom.xml` (for vector) or `ic_medicos_logo_custom.png` (for bitmap)
3. **Update the WelcomeScreen** to use your logo:
   ```kotlin
   painter = painterResource(id = R.drawable.ic_medicos_logo_custom)
   ```

## Logo File Locations:

All logo files are stored in:
```
app/src/main/res/drawable/
├── ic_medicos_logo.xml              # Detailed logo
├── ic_medicos_logo_simple.xml       # Simple logo  
├── ic_medicos_logo_minimal.xml      # Minimalist logo
└── ic_medicos_logo_custom.xml       # Your custom logo (current)
```

## Recommendations:

- **For your brand**: Use `ic_medicos_logo_custom.xml` (current) - Your custom logo
- **For a professional look**: Use `ic_medicos_logo_minimal.xml`
- **For a friendly look**: Use `ic_medicos_logo_simple.xml`
- **For a detailed look**: Use `ic_medicos_logo.xml`

Your custom logo is currently active and displays a professional medical cross design that represents your Medicos Here branding.
