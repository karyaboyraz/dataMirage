# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

DataMirage is a Java 17+ library for generating realistic fake data with multi-language support. It provides 30+ data providers (name, address, finance, internet, etc.) with locale-specific data loaded from YAML files.

## Build & Test Commands

```bash
# Build the project
mvn clean compile

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=DataMirageTest

# Run a specific test method
mvn test -Dtest=NameProviderTest#firstName_ShouldReturnNonNullValue

# Package (creates JAR with dependencies)
mvn package

# Install to local repository
mvn install
```

## Architecture

### Entry Point
`DataMirage` class (`src/main/java/com/datamirage/DataMirage.java`) is the main facade. It initializes all providers and provides accessor methods like `dataMirage.name()`, `dataMirage.address()`, etc.

### Provider Pattern
All providers extend `AbstractProvider` which provides:
- `RandomService` instance for random value generation
- `requireLocaleSpecificData()` / `hasLocaleSpecificData()` for locale data checks

Providers are located in `com.datamirage.providers.*` package.

### Data Loading System
`DataLoader` (`src/main/java/com/datamirage/util/DataLoader.java`) handles YAML data loading with:
- **Two-tier data structure**: Common data (`data/common/`) merged with locale-specific data (`data/{locale_code}/`)
- **Locale codes**: `tr_TR`, `en_US`, `de_DE`, `fr_FR`, `es_ES`, `it_IT`, `ru_RU`
- **Caching**: Data is cached in `ConcurrentHashMap` per locale
- **Deep merge**: Locale-specific data overrides common data for conflicting keys

### Resource Files
YAML data files are in `src/main/resources/data/`:
- `common/` - Shared data across all locales (e.g., crypto, git, science)
- `{locale}/` - Locale-specific data (e.g., names, addresses, phone formats)

### Key Utilities
- `RandomService` - Centralized random number/element generation
- `DataLoader` - YAML loading and caching with locale support
- `HelpersProvider` - Pattern randomization (`numerify`, `letterify`, `bothify`)

## Adding New Locale Data

1. Create directory: `src/main/resources/data/{locale_code}/`
2. Add YAML files matching existing category names (e.g., `name.yaml`, `address.yaml`)
3. Register locale in `DataMirageLocale` enum
4. Ensure required files exist: `address`, `name`, `company`, `food`, `animal`, `phone`

## Testing Patterns

Tests use JUnit 5 with:
- `@ParameterizedTest` with `@EnumSource(DataMirageLocale.class)` for testing all locales
- `@RepeatedTest` for randomness verification
- Mockito for unit testing providers in isolation
