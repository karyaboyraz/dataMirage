# DataMirage Providers Guide

This document provides detailed information about all provider classes, their methods, and usage examples in the DataMirage library.

## Table of Contents

1. [Introduction](#introduction)
2. [Provider Classes](#provider-classes)
   - [AbstractProvider](#abstractprovider)
   - [AddressProvider](#addressprovider)
   - [AnimalProvider](#animalprovider)
   - [AppProvider](#appprovider)
   - [ArtistProvider](#artistprovider)
   - [BookProvider](#bookprovider)
   - [BoolProvider](#boolprovider)
   - [CodeProvider](#codeprovider)
   - [ColorProvider](#colorprovider)
   - [CommerceProvider](#commerceprovider)
   - [CompanyProvider](#companyprovider)
   - [CryptoProvider](#cryptoprovider)
   - [DateProvider](#dateprovider)
   - [FilmProvider](#filmprovider)
   - [FinanceProvider](#financeprovider)
   - [FoodProvider](#foodprovider)
   - [GitProvider](#gitprovider)
   - [HelpersProvider](#helpersprovider)
   - [ImageProvider](#imageprovider)
   - [InternetProvider](#internetprovider)
   - [MusicProvider](#musicprovider)
   - [NameProvider](#nameprovider)
   - [NumberProvider](#numberprovider)
   - [PhoneNumberProvider](#phonenumberprovider)
   - [ScienceProvider](#scienceprovider)
   - [StringProvider](#stringprovider)
   - [SystemProvider](#systemprovider)
   - [VehicleProvider](#vehicleprovider)
   - [WeatherProvider](#weatherprovider)
   - [WordProvider](#wordprovider)

## Introduction

DataMirage is a comprehensive Java library that generates realistic fake data in various categories. Basic usage:

```java
// Use with default locale (Turkish)
DataMirage dataMirage = new DataMirage();

// Use with English locale
DataMirage dataMirageEN = new DataMirage(DataMirageLocale.EN_US);

// Example data generation
String name = dataMirage.name().firstName();
String address = dataMirage.address().streetAddress();
```

## Provider Classes

### AbstractProvider

Base abstract class for all provider classes.

**Methods:**

- `requireLocaleSpecificData(String category, String field)`: Checks if locale-specific data exists for a given category and field.
- `hasLocaleSpecificData(String category, String field)`: Returns a boolean indicating whether locale-specific data exists.

### AddressProvider

Generates fake data related to addresses.

**Usage:**
```java
AddressProvider addressProvider = dataMirage.address();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `city()` | Generates a random city name | "İstanbul" |
| `streetName()` | Generates a random street name | "Atatürk" |
| `streetSuffix()` | Generates a random street suffix | "Caddesi" |
| `state()` | Generates a random state/province name | "Ankara" |
| `stateAbbr()` | Generates a random state/province abbreviation | "IST" |
| `country()` | Generates a random country name | "Türkiye" |
| `countryCode()` | Generates a random country code | "TR" |
| `district()` | Generates a random district name | "Kadıköy" |
| `postalCode()` | Generates a random postal code | "34722" |
| `zipCode()` | Generates a random postal code (alias for postalCode) | "34722" |
| `streetAddress()` | Generates a random street address | "Atatürk Caddesi No: 15" |
| `fullAddress()` | Generates a complete address | "Atatürk Caddesi No: 15, Kadıköy, İstanbul, 34722, Türkiye" |
| `buildingNumber()` | Generates a random building number | "15" |
| `streetNumber()` | Generates a random street number | "42" |
| `latitude()` | Generates a random latitude value | "41.008438" |
| `longitude()` | Generates a random longitude value | "28.980175" |
| `coordinates()` | Generates random latitude and longitude values | "41.008438, 28.980175" |

### AnimalProvider

Generates fake data related to animals.

**Usage:**
```java
AnimalProvider animalProvider = dataMirage.animal();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `animal()` | Generates a random animal name | "Cat" |
| `animalType()` | Generates a random animal type | "Mammal" |
| `animalScientificName()` | Generates a random scientific name for an animal | "Felis catus" |
| `animalFamily()` | Generates a random animal family name | "Felidae" |
| `animalKingdom()` | Generates a random animal kingdom name | "Animalia" |
| `animalPhylum()` | Generates a random animal phylum name | "Chordata" |
| `animalClass()` | Generates a random animal class name | "Mammalia" |
| `animalOrder()` | Generates a random animal order name | "Carnivora" |
| `animalGenus()` | Generates a random animal genus name | "Felis" |
| `animalSpecies()` | Generates a random animal species name | "catus" |

### AppProvider

Generates fake data related to applications.

**Usage:**
```java
AppProvider appProvider = dataMirage.app();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `name()` | Generates a random application name | "InstaFoto" |
| `platform()` | Generates a random application platform | "Android" |
| `category()` | Generates a random application category | "Social Media" |
| `version()` | Generates a random version number | "2.5.3" |

### ArtistProvider

Generates fake data related to artists.

**Usage:**
```java
ArtistProvider artistProvider = dataMirage.artist();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `name()` | Generates a random artist name | "Picasso" |
| `genre()` | Generates a random artist genre | "Cubism" |
| `nationality()` | Generates a random artist nationality | "Spanish" |
| `artwork()` | Generates a random artwork name | "Guernica" |

### BookProvider

Generates fake data related to books.

**Usage:**
```java
BookProvider bookProvider = dataMirage.book();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `title()` | Generates a random book title | "The Great Gatsby" |
| `author()` | Generates a random author name | "F. Scott Fitzgerald" |
| `publisher()` | Generates a random publisher name | "Penguin Books" |
| `genre()` | Generates a random book genre | "Fiction" |
| `isbn()` | Generates a random ISBN number | "9781234567897" |

### BoolProvider

Generates fake data related to boolean values.

**Usage:**
```java
BoolProvider boolProvider = dataMirage.bool();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `bool()` | Generates a random boolean value with equal probability | true |
| `bool(double probability)` | Generates a random boolean value with the specified probability of being true | false |

### CodeProvider

Generates various types of standardized codes.

**Usage:**
```java
CodeProvider codeProvider = dataMirage.code();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `isbn()` | Generates a random ISBN (International Standard Book Number) | "9781234567897" |
| `ean()` | Generates a random EAN (European Article Number) | "5901234123457" |
| `asin()` | Generates a random ASIN (Amazon Standard Identification Number) | "B00EXAMPLE" |
| `issn()` | Generates a random ISSN (International Standard Serial Number) | "1234-5678X" |

### ColorProvider

Generates fake data related to colors.

**Usage:**
```java
ColorProvider colorProvider = dataMirage.color();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `name()` | Generates a random color name | "Red" |
| `hex()` | Generates a random HEX color code | "#FF5733" |
| `rgb()` | Generates a random RGB color value | "rgb(255, 87, 51)" |
| `rgba()` | Generates a random RGBA color value | "rgba(255, 87, 51, 0.8)" |
| `hsl()` | Generates a random HSL color value | "hsl(15, 100%, 60%)" |

### CommerceProvider

Generates fake data related to e-commerce.

**Usage:**
```java
CommerceProvider commerceProvider = dataMirage.commerce();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `productName()` | Generates a random product name | "Smartphone" |
| `department()` | Generates a random department name | "Electronics" |
| `material()` | Generates a random material name | "Cotton" |
| `promotionCode()` | Generates a random promotion code | "PROMO-ABCD-1234" |

### CompanyProvider

Generates fake data related to companies.

**Usage:**
```java
CompanyProvider companyProvider = dataMirage.company();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `name()` | Generates a random company name | "Acme" |
| `suffix()` | Generates a random company suffix | "Inc." |
| `industry()` | Generates a random industry name | "Technology" |
| `catchPhrase()` | Generates a random company catchphrase | "Building the future together" |
| `fullName()` | Generates a random full company name | "Acme Inc." |

### CryptoProvider

Generates fake data related to cryptocurrencies.

**Usage:**
```java
CryptoProvider cryptoProvider = dataMirage.crypto();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `coinName()` | Generates a random cryptocurrency name | "Bitcoin" |
| `coinSymbol()` | Generates a random cryptocurrency symbol | "BTC" |
| `blockchain()` | Generates a random blockchain name | "Ethereum" |
| `walletAddress()` | Generates a random cryptocurrency wallet address | "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa" |
| `transactionHash()` | Generates a random transaction hash | "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855" |

### DateProvider

Generates fake data related to dates and times.

**Usage:**
```java
DateProvider dateProvider = dataMirage.date();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `today()` | Returns today's date in ISO format | "2023-05-25" |
| `tomorrow()` | Returns tomorrow's date in ISO format | "2023-05-26" |
| `yesterday()` | Returns yesterday's date in ISO format | "2023-05-24" |
| `randomDateInPast()` | Generates a random date within the past year | "2023-01-15" |
| `randomDateInFuture()` | Generates a random date within the next year | "2023-08-10" |
| `randomDateBetween(LocalDate start, LocalDate end)` | Generates a random date between two specified dates | "2022-10-17" |
| `randomHours()` | Generates a random hour of the day in 24-hour format | "14:00" |
| `RandomHoursAndMinutes()` | Generates a random time with hours and minutes | "14:35" |
| `date()` | Generates a random date within a year from today | LocalDate object |
| `time()` | Generates a random time of day | LocalTime object |
| `dateTime()` | Generates a random date and time | LocalDateTime object |
| `dateString(String pattern)` | Generates a random date formatted according to the specified pattern | "2023-05-25" |
| `timeString(String pattern)` | Generates a random time formatted according to the specified pattern | "14:35:22" |
| `dateTimeString(String pattern)` | Generates a random date and time formatted according to the specified pattern | "2023-05-25 14:35:22" |

### FilmProvider

Generates fake data related to films and TV shows.

**Usage:**
```java
FilmProvider filmProvider = dataMirage.film();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `title()` | Generates a random film title | "The Godfather" |
| `director()` | Generates a random director name | "Christopher Nolan" |
| `actor()` | Generates a random actor name | "Tom Hanks" |
| `genre()` | Generates a random film genre | "Drama" |
| `character()` | Generates a random character name | "James" |
| `quote()` | Generates a random film quote | "I'm going to make him an offer he can't refuse" |
| `year()` | Generates a random film year between 1920 and current year | "2005" |
| `rating()` | Generates a random film rating between 1.0 and 10.0 | "8.5" |

### FinanceProvider

Generates fake data related to finance.

**Usage:**
```java
FinanceProvider financeProvider = dataMirage.finance();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `creditCardNumber(String prefix, int length)` | Generates a random credit card number with the specified prefix and length | "4532756812453894" |
| `visaCardNumber()` | Generates a random Visa card number | "4532756812453894" |
| `masterCardNumber()` | Generates a random MasterCard number | "5234567891234567" |
| `amexCardNumber()` | Generates a random American Express card number | "341234567890123" |
| `creditCardExpiryDate()` | Generates a random credit card expiration date | "05/26" |
| `cvv(int digits)` | Generates a random CVV number with specified number of digits | "123" |
| `bankAccountNumber()` | Generates a random bank account number | "1234567890" |
| `ibanBuilder()` | Generates a random IBAN | "TR330006100519786457841326" |
| `amount(double min, double max)` | Generates a random amount of money within a specified range | "856.42" |

### FoodProvider

Generates fake data related to food and drinks.

**Usage:**
```java
FoodProvider foodProvider = dataMirage.food();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `dish()` | Generates a random dish name | "Spaghetti" |
| `ingredient()` | Generates a random ingredient name | "Tomato" |
| `spice()` | Generates a random spice name | "Oregano" |
| `measurement()` | Generates a random cooking measurement | "Cup" |

### GitProvider

Generates fake data related to Git.

**Usage:**
```java
GitProvider gitProvider = dataMirage.git();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `branch()` | Generates a random Git branch name | "feature-123" |
| `commitSha()` | Generates a random Git commit SHA hash | "a1b2c3d4e5f6..." (40 character hexadecimal) |
| `commitMessage()` | Generates a random Git commit message | "Fix typo in README" |
| `commitDate()` | Generates a formatted Git commit date | "Tue Jun 15 10:30:45 2023 +0300" |
| `commitEntry()` | Generates a complete Git commit entry | "commit a1b2c3... \| Author: John Doe <john@example.com> \| Date: Tue Jun 15..." |

### HelpersProvider

Provides utility methods for random data generation.

**Usage:**
```java
HelpersProvider helpersProvider = dataMirage.helpers();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `randomElement(List<T> list)` | Returns a random element from a list | "apple" (from a list of fruits) |
| `randomElement(T[] array)` | Returns a random element from an array | "dog" (from an array of animals) |
| `randomKey(Map<K, V> map)` | Returns a random key from a map | "one" (from a map with keys) |
| `randomValue(Map<K, V> map)` | Returns a random value from a map | 2 (from a map with values) |
| `randomize(String pattern)` | Randomizes a pattern with special characters | "42-A7-3B" (from "##-##-##") |
| `numerify(String pattern)` | Replaces # with random numbers | "42-57-36" (from "##-##-##") |
| `letterify(String pattern)` | Replaces ? with random letters | "ab-cd-ef" (from "??-??-??") |
| `bothify(String pattern)` | Replaces both # and ? characters | "4a-7b-3c" (from "#?-#?-#?") |

### ImageProvider

Generates fake data related to images.

**Usage:**
```java
ImageProvider imageProvider = dataMirage.image();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `imageUrl(int width, int height)` | Generates a random image URL with specified dimensions | "https://picsum.photos/800/600" |
| `imageUrl(int width, int height, String category)` | Generates a random image URL with category | "https://picsum.photos/800/600?category=nature" |
| `imageUrl(int width, int height, String category, String search)` | Generates a random image URL with category and search | "https://picsum.photos/800/600?category=nature&search=mountain" |
| `imageUrl(int width, int height, String category, String search, String seed)` | Generates a random image URL with additional parameters | "https://picsum.photos/seed/sunset/800/600?category=nature&search=mountain" |
| `avatarUrl(int size)` | Generates a random avatar image URL | "https://i.pravatar.cc/150" |
| `placeholderUrl(int width, int height)` | Generates a placeholder image URL | "https://via.placeholder.com/800x600" |

### InternetProvider

Generates fake data related to the internet.

**Usage:**
```java
InternetProvider internetProvider = dataMirage.internet();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `email()` | Generates a random email address | "john.doe123@example.com" |
| `username()` | Generates a random username | "johndoe42" |
| `domainName()` | Generates a random domain name | "example.com" |
| `url()` | Generates a random URL | "https://www.example.com" |
| `ipv4()` | Generates a random IPv4 address | "192.168.1.1" |
| `ipv6()` | Generates a random IPv6 address | "2001:0db8:85a3:0000:0000:8a2e:0370:7334" |
| `macAddress()` | Generates a random MAC address | "00:1A:2B:3C:4D:5E" |
| `password()` | Generates a random password (10 characters) | "aB3cD5eF7g" |
| `password(int length)` | Generates a random password with specified length | "aB3cD5eF7g9H" |
| `passwordWithRules(int length, boolean includeUpper, boolean includeDigits, boolean includeSymbols)` | Generates a random password with custom rules | "ab3cd5ef7g9" |
| `randomPort()` | Generates a random port number | 8080 |
| `randomEmoji()` | Generates a random emoji | "😀" |

### MusicProvider

Generates fake data related to music.

**Usage:**
```java
MusicProvider musicProvider = dataMirage.music();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `genre()` | Generates a random music genre | "Rock" |
| `artist()` | Generates a random artist name | "The Beatles" |
| `album()` | Generates a random album name | "Dark Side of the Moon" |
| `song()` | Generates a random song title | "Bohemian Rhapsody" |
| `instrument()` | Generates a random musical instrument name | "Guitar" |
| `key()` | Generates a random musical key | "C Major" |
| `chord()` | Generates a random chord | "G7" |
| `note()` | Generates a random musical note | "A" |
| `scale()` | Generates a random musical scale | "Minor" |
| `tempo()` | Generates a random tempo marking | "Allegro" |
| `dynamic()` | Generates a random dynamic marking | "Forte" |
| `timeSignature()` | Generates a random time signature | "4/4" |
| `chordType()` | Generates a random chord type | "maj7" |
| `chordProgression()` | Generates a random chord progression | "I - IV - V - I" |
| `musicalPhrase()` | Generates a random musical phrase | "C D E G A G E C" |

### NameProvider

Generates fake data related to names.

**Usage:**
```java
NameProvider nameProvider = dataMirage.name();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `firstName()` | Generates a random first name | "John" |
| `lastName()` | Generates a random last name | "Smith" |
| `fullName()` | Generates a random full name | "John Smith" |
| `prefix()` | Generates a random name prefix | "Mr." |
| `suffix()` | Generates a random name suffix | "Jr." |
| `title()` | Generates a random title | "Professor" |
| `gender()` | Generates a random gender | "Male" |
| `jobTitle()` | Generates a random job title (alias for title) | "Doctor" |
| `username()` | Generates a random username | "john.smith42" |
| `turkeyGovIDN()` | Generates a valid Turkish government ID number | "12345678901" |

### NumberProvider

Generates fake data related to numbers.

**Usage:**
```java
NumberProvider numberProvider = dataMirage.number();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `number(int min, int max)` | Generates a random integer within range | 42 |
| `decimal(double min, double max)` | Generates a random decimal within range | 42.5678 |
| `decimal(double min, double max, int scale)` | Generates a random decimal with specified scale | "42.57" |
| `digit()` | Generates a random single digit | "7" |
| `digits(int count)` | Generates random digits with specified length | "12345" |
| `hex(int count)` | Generates random hexadecimal digits | "1a2b3" |
| `binary(int count)` | Generates random binary digits | "10101" |
| `octal(int count)` | Generates random octal digits | "12345" |

### PhoneNumberProvider

Generates fake data related to phone numbers.

**Usage:**
```java
PhoneNumberProvider phoneNumberProvider = dataMirage.phoneNumber();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `phoneNumber()` | Generates a random phone number | "+90 532 123 45 67" |
| `landline()` | Generates a random landline number | "0212 123 45 67" |
| `cellPhone()` | Generates a random cell phone number | "+90 532 123 45 67" |
| `internationalPhoneFormat()` | Generates a random international phone number | "+90 532 123 45 67" |

### ScienceProvider

Generates fake data related to science.

**Usage:**
```java
ScienceProvider scienceProvider = dataMirage.science();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `chemicalElement()` | Generates a random chemical element name | "Hydrogen" |
| `chemicalSymbol()` | Generates a random chemical element symbol | "H" |
| `chemicalFormula()` | Generates a random chemical formula | "H2O" |
| `unit()` | Generates a random unit of measurement | "meter" |
| `unitSymbol()` | Generates a random unit symbol | "m" |
| `unitPrefix()` | Generates a random unit prefix | "kilo" |
| `unitPrefixSymbol()` | Generates a random unit prefix symbol | "k" |
| `unitWithPrefix()` | Generates a random unit with prefix | "kilometer" |
| `unitSymbolWithPrefix()` | Generates a random unit symbol with prefix | "km" |

### StringProvider

Generates fake data related to strings.

**Usage:**
```java
StringProvider stringProvider = dataMirage.string();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `alpha(int count)` | Generates a string of alphabetic characters | "abcDEF" |
| `alphaNumeric(int count)` | Generates a string of alphanumeric characters | "a1B2c3" |
| `numeric(int count)` | Generates a string of numeric characters | "123456" |
| `sample(int count)` | Generates a string of sample characters | "!@#$%^" |
| `hex(int count)` | Generates a string of hexadecimal characters | "a1b2c3" |
| `binary(int count)` | Generates a string of binary characters | "101010" |
| `octal(int count)` | Generates a string of octal characters | "123456" |
| `uuid()` | Generates a random UUID | "123e4567-e89b-12d3-a456-426614174000" |
| `randomString(int length)` | Generates a random alphanumeric string | "a1B2c3D4" |
| `randomAlphabetic(int length)` | Generates a random alphabetic string | "abcDEF" |
| `randomNumeric(int length)` | Generates a random numeric string | "123456" |
| `randomWithSpecialChars(int length)` | Generates a random string with special characters | "a1B@c3!" |

### SystemProvider

Generates fake data related to system files and paths.

**Usage:**
```java
SystemProvider systemProvider = dataMirage.system();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `fileName()` | Generates a random file name | "document" |
| `fileExtension()` | Generates a random file extension | "pdf" |
| `fileNameWithExtension()` | Generates a random filename with extension | "document.pdf" |
| `directoryPath()` | Generates a random directory path | "/usr/local/bin" |
| `filePath()` | Generates a random file path | "/usr/local/bin/document.pdf" |
| `mimeType()` | Generates a random MIME type | "application/pdf" |
| `commonFileType()` | Generates a random common file type | "Document" |
| `commonFileExtension()` | Generates a random common file extension | ".doc" |
| `commonFileName()` | Generates a random common file name | "readme" |
| `commonFileNameWithExtension()` | Generates a random common file name with extension | "readme.txt" |

### VehicleProvider

Generates fake data related to vehicles.

**Usage:**
```java
VehicleProvider vehicleProvider = dataMirage.vehicle();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `vehicle()` | Generates a random vehicle name | "Toyota Corolla" |
| `manufacturer()` | Generates a random vehicle manufacturer | "Toyota" |
| `make()` | Generates a random vehicle make (alias for manufacturer) | "Honda" |
| `model()` | Generates a random vehicle model | "Civic" |
| `type()` | Generates a random vehicle type | "Sedan" |
| `fuel()` | Generates a random fuel type | "Gasoline" |
| `color()` | Generates a random vehicle color | "Blue" |
| `vin()` | Generates a random Vehicle Identification Number | "1HGCM82633A123456" |
| `licensePlate()` | Generates a random license plate | "ABC-123" |
| `year()` | Generates a random vehicle year | 2020 |

### WeatherProvider

Generates fake data related to weather.

**Usage:**
```java
WeatherProvider weatherProvider = dataMirage.weather();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `getDescription()` | Generates a random weather description | "Partly Cloudy" |
| `temperatureCelsius()` | Generates a random temperature in Celsius | "25 °C" |
| `temperatureCelsius(int min, int max)` | Generates a random temperature in Celsius within range | "18 °C" |
| `temperatureFahrenheit()` | Generates a random temperature in Fahrenheit | "77 °F" |
| `temperatureFahrenheit(int min, int max)` | Generates a random temperature in Fahrenheit within range | "68 °F" |
| `getWindDirection()` | Generates a random wind direction | "North" |
| `windSpeedKm()` | Generates a random wind speed in km/h | "15 km/h" |
| `windSpeedKm(int min, int max)` | Generates a random wind speed in km/h within range | "10 km/h" |
| `windSpeedMph()` | Generates a random wind speed in mph | "10 mph" |
| `windSpeedMph(int min, int max)` | Generates a random wind speed in mph within range | "5 mph" |
| `getRelativeHumidity()` | Generates a random relative humidity percentage | "65%" |
| `getAbsoluteHumidity()` | Generates a random absolute humidity | "250 g/m³" |
| `humidity()` | Generates a random humidity percentage | "75%" |
| `weatherReport()` | Generates a complete weather report | "Temperature: 22 °C, Wind: 12 km/h North, Humidity: 68%, Description: Sunny" |

### WordProvider

Generates fake data related to words and text.

**Usage:**
```java
WordProvider wordProvider = dataMirage.word();
```

**Methods:**

| Method | Description | Example Output |
|--------|-------------|----------------|
| `word()` | Generates a random word | "lorem" |
| `words(int count)` | Generates a specific number of random words | "lorem ipsum dolor sit amet" |
| `sentence(int wordCount)` | Generates a random sentence with specified word count | "Lorem ipsum dolor sit amet." |
| `sentences(int count)` | Generates a specific number of random sentences | "Lorem ipsum dolor sit amet. Consectetur adipiscing elit." |
| `paragraph(int sentenceCount)` | Generates a random paragraph with specified sentence count | "Lorem ipsum dolor sit amet. Consectetur adipiscing elit. Sed do eiusmod tempor incididunt." |
| `paragraphs(int count)` | Generates a specific number of random paragraphs | "Lorem ipsum dolor sit amet. Consectetur adipiscing elit.\n\nSed do eiusmod tempor incididunt. Ut labore et dolore magna aliqua." |
| `text(int maxCharacterCount)` | Generates random text with a maximum character count | "Lorem ipsum dolor sit amet. Consectetur adipiscing elit." |
| `wordStartingWith(char letter)` | Generates a random word starting with a specific letter | "lorem" |
| `wordEndingWith(char letter)` | Generates a random word ending with a specific letter | "ipsum" |