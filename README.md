# DataMirage

[![Java Version](https://img.shields.io/badge/java-17+-blue.svg)](https://www.oracle.com/java/technologies/downloads/)
[![Maven Central](https://img.shields.io/maven-central/v/com.datamirage/datamirage.svg)](https://search.maven.org/artifact/com.datamirage/datamirage)
[![JitPack](https://jitpack.io/v/karyaboyraz/datamirage.svg)](https://jitpack.io/#karyaboyraz/datamirage)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

DataMirage is a comprehensive Java library for generating realistic fake data. It provides a wide range of data providers to generate various types of fake data for testing, development, and demonstration purposes.

## Table of Contents

- [Features](#features)
- [Supported Locales](#supported-locales)
- [Requirements](#requirements)
- [Installation](#installation)
- [Import](#import)
- [Usage](#usage)
- [Available Providers](#available-providers)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## Features

- **Multi-language Support**: Supports multiple locales for localized data generation
- **Extensive Data Providers**: Includes providers for various data types:
  - Addresses
  - Names
  - Companies
  - Internet-related data
  - Books
  - Colors
  - Food
  - Music
  - Phone numbers
  - Weather
  - Films
  - Animals
  - Vehicles
  - Science
  - System information
  - Git-related data
  - Cryptocurrency
  - Commerce
  - Code
  - Dates
  - Finance
  - Artists
  - Apps
  - Helpers
  - Boolean values
  - Images
  - Numbers
  - Strings
  - Words

## Supported Locales

DataMirage currently supports the following locales:

- **TR_TR**: Turkish (Turkey)
- **EN_US**: English (United States)
- **DE_DE**: German (Germany)
- **FR_FR**: French (France)
- **ES_ES**: Spanish (Spain)
- **IT_IT**: Italian (Italy)
- **RU_RU**: Russian (Russia)

## Requirements

- Java 17 or higher
- Maven 3.6 or higher (for Maven projects)
- Gradle 7.0 or higher (for Gradle projects)

## Installation

### Maven

#### Option 1: From Maven Central
Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.datamirage</groupId>
    <artifactId>datamirage</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Option 2: From GitHub Packages
Add the GitHub Packages repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub Packages</name>
        <url>https://maven.pkg.github.com/karyaboyraz/datamirage</url>
    </repository>
</repositories>
```

Then add the dependency:

```xml
<dependency>
    <groupId>com.datamirage</groupId>
    <artifactId>datamirage</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Option 3: From JitPack
Add the JitPack repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Then add the dependency:

```xml
<dependency>
    <groupId>com.github.karyaboyraz</groupId>
    <artifactId>datamirage</artifactId>
    <version>v1.0.0</version>
</dependency>
```

### Gradle

#### Option 1: From Maven Central
Add the following dependency to your `build.gradle`:

```gradle
implementation 'com.datamirage:datamirage:1.0.0'
```

#### Option 2: From GitHub Packages
Add the GitHub Packages repository to your `build.gradle`:

```gradle
repositories {
    // ... other repositories
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/karyaboyraz/datamirage")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
        }
    }
}
```

Then add the dependency:

```gradle
implementation 'com.datamirage:datamirage:1.0.0'
```

#### Option 3: From JitPack
Add the JitPack repository to your `build.gradle`:

```gradle
repositories {
    // ... other repositories
    maven { url 'https://jitpack.io' }
}
```

Then add the dependency:

```gradle
implementation 'com.github.karyaboyraz:datamirage:v1.0.0'
```

## Import

To use DataMirage in your Java code, you need to import the required classes:

### Basic Import
```java
// Import the main DataMirage class
import com.datamirage.DataMirage;
```

### Locale Import
If you want to specify a locale:
```java
// Import DataMirage and DataMirageLocale
import com.datamirage.DataMirage;
import com.datamirage.locale.DataMirageLocale;
```

### Provider Imports
If you need to use specific provider types (optional):
```java
// Import specific providers if needed
import com.datamirage.providers.AddressProvider;
import com.datamirage.providers.NameProvider;
import com.datamirage.providers.InternetProvider;
// ... other providers as needed
```

### Complete Example
```java
// Complete import example
import com.datamirage.DataMirage;
import com.datamirage.locale.DataMirageLocale;

public class Example {
    public static void main(String[] args) {
        // Create a DataMirage instance with US English locale
        DataMirage dataMirage = new DataMirage(DataMirageLocale.EN_US);
        
        // Generate fake data
        String name = dataMirage.name().fullName();
        String email = dataMirage.internet().email();
        
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
}
```

## Usage

### Basic Usage

```java
// Create a new instance with default locale (Turkish)
DataMirage dataMirage = new DataMirage();

// Generate a random first name
String firstName = dataMirage.name().firstName();

// Generate a random street address
String address = dataMirage.address().streetAddress();

// Generate a random email
String email = dataMirage.internet().email();
```

### Using Different Locales

```java
// Create a new instance with a specific locale
DataMirage dataMirage = new DataMirage(DataMirageLocale.EN_US);

// Generate localized data
String localizedName = dataMirage.name().firstName();
```

### Testing All Providers

DataMirage provides a handy `ProviderTester` class that allows you to test all available providers with a specific locale:

```java
import com.datamirage.ProviderTester;
import com.datamirage.locale.DataMirageLocale;

public class TestAllProviders {
    public static void main(String[] args) {
        // Create a provider tester with a specific locale
        ProviderTester tester = new ProviderTester(DataMirageLocale.EN_US);
        
        // Test all providers and print the results
        tester.testAllProviders();
    }
}
```

This is particularly useful for:
- Verifying that all providers work correctly with a specific locale
- Seeing examples of all the data that can be generated
- Debugging locale-specific issues

## Available Providers

For a detailed explanation of all available providers and their methods, please see [DataMirage Providers Guide](DataMirage-Providers-Guide.md).

### Address Provider
```java
String streetAddress = dataMirage.address().streetAddress();
String city = dataMirage.address().city();
String country = dataMirage.address().country();
String postalCode = dataMirage.address().postalCode();
String state = dataMirage.address().state();
```

### Name Provider
```java
String firstName = dataMirage.name().firstName();
String lastName = dataMirage.name().lastName();
String fullName = dataMirage.name().fullName();
String prefix = dataMirage.name().prefix();
String suffix = dataMirage.name().suffix();
```

### Internet Provider
```java
String email = dataMirage.internet().email();
String url = dataMirage.internet().url();
String ipAddress = dataMirage.internet().ipAddress();
String username = dataMirage.internet().username();
String domainName = dataMirage.internet().domainName();
```

### Company Provider
```java
String companyName = dataMirage.company().name();
String industry = dataMirage.company().industry();
String catchPhrase = dataMirage.company().catchPhrase();
String buzzword = dataMirage.company().buzzword();
String bs = dataMirage.company().bs();
```

### Finance Provider
```java
String creditCardNumber = dataMirage.finance().creditCardNumber();
String currency = dataMirage.finance().currency();
String bankName = dataMirage.finance().bankName();
String iban = dataMirage.finance().iban();
String bic = dataMirage.finance().bic();
```

## Troubleshooting

### Common Issues

1. **Locale Not Found**
   - Ensure you're using a supported locale
   - Check the spelling of the locale constant

2. **NullPointerException**
   - Make sure you've initialized the DataMirage instance
   - Check if the provider you're trying to use exists

3. **Memory Issues**
   - If generating large amounts of data, consider using streams
   - Release resources when done with large data sets

## Contributing

We welcome contributions! Here's how you can help:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

Please make sure to:
- Follow the existing code style
- Add tests for new features
- Update documentation as needed
- Keep commits clean and focused

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

- Karya Boyraz
