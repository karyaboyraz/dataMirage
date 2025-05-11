package com.datamirage;

import com.datamirage.locale.DataMirageLocale;
import com.datamirage.providers.*;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Bu sınıf tüm provider'lardaki metodları belirli bir dil için çalıştırıp
 * sonuçları loglar, böylece her metodun düzgün çalıştığı görsel olarak doğrulanabilir.
 */
public class ProviderTester {

    private final DataMirageLocale locale;
    private final DataMirage dataMirage;

    public ProviderTester(DataMirageLocale locale) {
        this.locale = locale;
        this.dataMirage = new DataMirage(locale);
    }

    public void testAllProviders() {
        System.out.println("===== Testing all providers with locale: " + locale + " =====\n");

        testAddressProvider();
        testNameProvider();
        testPhoneNumberProvider();
        testCompanyProvider();
        testFoodProvider();
        testVehicleProvider();
        testBookProvider();
        testColorProvider();
        testDateProvider();
        testMusicProvider();
        testFilmProvider();
        testWeatherProvider();
        testSystemProvider();
        testInternetProvider();
        testAnimalProvider();
        testScienceProvider();
        testGitProvider();
        testCryptoProvider();
        testCommerceProvider();
        testCodeProvider();
        testFinanceProvider();
        testArtistProvider();
        testAppProvider();
        testHelpersProvider();
        testBoolProvider();
        testImageProvider();
        testNumberProvider();
        testStringProvider();
        testWordProvider();

        System.out.println("\n===== Testing completed =====");
    }

    private void testAddressProvider() {
        System.out.println("--- AddressProvider ---");
        AddressProvider provider = dataMirage.address();
        
        logMethodResult(provider, "city");
        logMethodResult(provider, "streetName");
        logMethodResult(provider, "streetAddress");
        logMethodResult(provider, "streetNumber");
        logMethodResult(provider, "buildingNumber");
        logMethodResult(provider, "zipCode");
        logMethodResult(provider, "postalCode");
        logMethodResult(provider, "state");
        logMethodResult(provider, "country");
        logMethodResult(provider, "fullAddress");
        logMethodResult(provider, "latitude");
        logMethodResult(provider, "longitude");
        logMethodResult(provider, "coordinates");
        
        System.out.println();
    }

    private void testNameProvider() {
        System.out.println("--- NameProvider ---");
        NameProvider provider = dataMirage.name();
        
        logMethodResult(provider, "firstName");
        logMethodResult(provider, "lastName");
        logMethodResult(provider, "fullName");
        logMethodResult(provider, "prefix");
        logMethodResult(provider, "suffix");
        logMethodResult(provider, "title");
        logMethodResult(provider, "jobTitle");
        logMethodResult(provider, "gender");
        logMethodResult(provider, "username");
        
        System.out.println();
    }

    private void testPhoneNumberProvider() {
        System.out.println("--- PhoneNumberProvider ---");
        PhoneNumberProvider provider = dataMirage.phoneNumber();
        
        logMethodResult(provider, "phoneNumber");
        logMethodResult(provider, "cellPhone");
        logMethodResult(provider, "landline");
        logMethodResult(provider, "internationalPhoneFormat");
        
        System.out.println();
    }

    private void testCompanyProvider() {
        System.out.println("--- CompanyProvider ---");
        CompanyProvider provider = dataMirage.company();
        
        logMethodResult(provider, "name");
        logMethodResult(provider, "suffix");
        logMethodResult(provider, "industry");
        logMethodResult(provider, "catchPhrase");
        
        System.out.println();
    }

    private void testFoodProvider() {
        System.out.println("--- FoodProvider ---");
        FoodProvider provider = dataMirage.food();
        
        logMethodResult(provider, "dish");
        logMethodResult(provider, "spice");
        logMethodResult(provider, "ingredient");


        System.out.println();
    }

    private void testVehicleProvider() {
        System.out.println("--- VehicleProvider ---");
        VehicleProvider provider = dataMirage.vehicle();
        
        logMethodResult(provider, "manufacturer");
        logMethodResult(provider, "model");
        logMethodResult(provider, "type");
        logMethodResult(provider, "fuel");
        logMethodResult(provider, "color");
        logMethodResult(provider, "licensePlate");
        logMethodResult(provider, "vin");
        
        System.out.println();
    }

    private void testBookProvider() {
        System.out.println("--- BookProvider ---");
        BookProvider provider = dataMirage.book();
        
        logMethodResult(provider, "title");
        logMethodResult(provider, "author");
        logMethodResult(provider, "publisher");
        logMethodResult(provider, "genre");
        logMethodResult(provider, "isbn");
        
        System.out.println();
    }

    private void testColorProvider() {
        System.out.println("--- ColorProvider ---");
        ColorProvider provider = dataMirage.color();
        
        logMethodResult(provider, "name");
        logMethodResult(provider, "hex");
        logMethodResult(provider, "rgb");
        logMethodResult(provider, "hsl");
        
        System.out.println();
    }

    private void testDateProvider() {
        System.out.println("--- DateProvider ---");
        DateProvider provider = dataMirage.date();
        
        logMethodResult(provider, "time");
        logMethodResult(provider, "today");
        logMethodResult(provider, "tomorrow");
        logMethodResult(provider, "yesterday");
        logMethodResult(provider, "randomDateInPast");
        logMethodResult(provider, "randomDateInFuture");
        logMethodResult(provider, "randomHours");
        logMethodResult(provider, "date");
        logMethodResult(provider, "dateTime");
        
        
        System.out.println();
    }

    private void testMusicProvider() {
        System.out.println("--- MusicProvider ---");
        MusicProvider provider = dataMirage.music();
        
        logMethodResult(provider, "genre");
        logMethodResult(provider, "instrument");
        logMethodResult(provider, "artist");
        logMethodResult(provider, "album");
        logMethodResult(provider, "song");
        
        System.out.println();
    }

    private void testFilmProvider() {
        System.out.println("--- FilmProvider ---");
        FilmProvider provider = dataMirage.film();
        
        logMethodResult(provider, "title");
        logMethodResult(provider, "genre");
        logMethodResult(provider, "director");
        logMethodResult(provider, "actor");
        
        System.out.println();
    }

    private void testWeatherProvider() {
        System.out.println("--- WeatherProvider ---");
        WeatherProvider provider = dataMirage.weather();
        
        logMethodResult(provider, "temperatureCelsius");
        logMethodResult(provider, "temperatureFahrenheit");
        logMethodResult(provider, "humidity");
        
        System.out.println();
    }

    private void testSystemProvider() {
        System.out.println("--- SystemProvider ---");
        SystemProvider provider = dataMirage.system();
        
        logMethodResult(provider, "fileName");
        logMethodResult(provider, "fileExtension");
        logMethodResult(provider, "mimeType");
        logMethodResult(provider, "directoryPath");
        logMethodResult(provider, "filePath");
        
        System.out.println();
    }

    private void testInternetProvider() {
        System.out.println("--- InternetProvider ---");
        InternetProvider provider = dataMirage.internet();
        
        logMethodResult(provider, "email");
        logMethodResult(provider, "url");
        logMethodResult(provider, "domainName");
        logMethodResult(provider, "ipv4");
        logMethodResult(provider, "ipv6");
        logMethodResult(provider, "macAddress");
        logMethodResult(provider, "username");
        logMethodResult(provider, "password");
        logMethodResult(provider, "userAgent");
        
        System.out.println();
    }

    private void testAnimalProvider() {
        System.out.println("--- AnimalProvider ---");
        AnimalProvider provider = dataMirage.animal();
        
        logMethodResult(provider, "type");
        logMethodResult(provider, "name");
        
        System.out.println();
    }

    private void testScienceProvider() {
        System.out.println("--- ScienceProvider ---");
        ScienceProvider provider = dataMirage.science();
        
        logMethodResult(provider, "element");
        logMethodResult(provider, "planet");
        
        System.out.println();
    }

    private void testGitProvider() {
        System.out.println("--- GitProvider ---");
        GitProvider provider = dataMirage.git();
        
        logMethodResult(provider, "branch");
        logMethodResult(provider, "commitSha");
        logMethodResult(provider, "commitMessage");
        logMethodResult(provider, "repositoryName");
        
        System.out.println();
    }

    private void testCryptoProvider() {
        System.out.println("--- CryptoProvider ---");
        CryptoProvider provider = dataMirage.crypto();
        
        logMethodResult(provider, "coin");
        logMethodResult(provider, "coinCode");
        logMethodResult(provider, "walletAddress");
        logMethodResult(provider, "md5");
        logMethodResult(provider, "sha1");
        logMethodResult(provider, "sha256");
        
        System.out.println();
    }

    private void testCommerceProvider() {
        System.out.println("--- CommerceProvider ---");
        CommerceProvider provider = dataMirage.commerce();
        
        logMethodResult(provider, "product");
        logMethodResult(provider, "department");
        logMethodResult(provider, "price");
        logMethodResult(provider, "category");
        logMethodResult(provider, "promotionCode");
        
        System.out.println();
    }

    private void testCodeProvider() {
        System.out.println("--- CodeProvider ---");
        CodeProvider provider = dataMirage.code();
        
        logMethodResult(provider, "language");
        logMethodResult(provider, "extension");
        
        System.out.println();
    }

    private void testFinanceProvider() {
        System.out.println("--- FinanceProvider ---");
        FinanceProvider provider = dataMirage.finance();
        
        logMethodResult(provider, "creditCardNumber");
        logMethodResult(provider, "creditCardExpiry");
        logMethodResult(provider, "creditCardType");
        logMethodResult(provider, "bitcoinAddress");
        logMethodResult(provider, "iban");
        logMethodResult(provider, "bic");
        logMethodResult(provider, "routingNumber");
        logMethodResult(provider, "accountNumber");
        logMethodResult(provider, "accountType");
        logMethodResult(provider, "currencyCode");
        logMethodResult(provider, "currencyName");
        logMethodResult(provider, "currencySymbol");
        
        System.out.println();
    }

    private void testArtistProvider() {
        System.out.println("--- ArtistProvider ---");
        ArtistProvider provider = dataMirage.artist();
        
        logMethodResult(provider, "name");
        logMethodResult(provider, "artForm");
        logMethodResult(provider, "artwork");
        
        System.out.println();
    }

    private void testAppProvider() {
        System.out.println("--- AppProvider ---");
        AppProvider provider = dataMirage.app();
        
        logMethodResult(provider, "name");
        logMethodResult(provider, "version");
        logMethodResult(provider, "category");
        
        System.out.println();
    }

    private void testHelpersProvider() {
        System.out.println("--- HelpersProvider ---");
        HelpersProvider provider = dataMirage.helpers();
        
        logMethodResult(provider, "shuffle", "Test string to shuffle");
        logMethodResult(provider, "slugify", "Test string to slugify");
        logMethodResult(provider, "repeatString", "abc", 3);
        logMethodResult(provider, "randomElements", Arrays.asList("a", "b", "c", "d"), 2);
        logMethodResult(provider, "randomSubset", Arrays.asList(1, 2, 3, 4, 5));
        logMethodResult(provider, "randInt", 1, 10);
        
        System.out.println();
    }

    private void testBoolProvider() {
        System.out.println("--- BoolProvider ---");
        BoolProvider provider = dataMirage.bool();
        
        logMethodResult(provider, "random");
        logMethodResult(provider, "weightedRandom", 0.7);
        
        System.out.println();
    }

    private void testImageProvider() {
        System.out.println("--- ImageProvider ---");
        ImageProvider provider = dataMirage.image();
        
        logMethodResult(provider, "placeholder", 300, 200);
        logMethodResult(provider, "avatarUrl");
        logMethodResult(provider, "imageUrl");
        logMethodResult(provider, "dataUri", 50, 50);
        logMethodResult(provider, "loremPixelUrl", 400, 300);
        
        System.out.println();
    }

    private void testNumberProvider() {
        System.out.println("--- NumberProvider ---");
        NumberProvider provider = dataMirage.number();
        
        logMethodResult(provider, "randomNumber");
        logMethodResult(provider, "randomNumber", 100);
        logMethodResult(provider, "randomNumber", 10, 20);
        logMethodResult(provider, "randomDigit");
        logMethodResult(provider, "randomDigitNotZero");
        logMethodResult(provider, "randomDouble", 2);
        logMethodResult(provider, "randomDouble", 2, 1, 10);
        logMethodResult(provider, "randomFloat");
        logMethodResult(provider, "randomFloat", 1, 10);
        
        System.out.println();
    }

    private void testStringProvider() {
        System.out.println("--- StringProvider ---");
        StringProvider provider = dataMirage.string();
        
        logMethodResult(provider, "randomString");
        logMethodResult(provider, "randomString", 20);
        logMethodResult(provider, "alphanumeric");
        logMethodResult(provider, "alphanumeric", 15);
        logMethodResult(provider, "alphabetic");
        logMethodResult(provider, "alphabetic", 10);
        
        System.out.println();
    }

    private void testWordProvider() {
        System.out.println("--- WordProvider ---");
        WordProvider provider = dataMirage.word();
        
        logMethodResult(provider, "word");
        logMethodResult(provider, "words");
        logMethodResult(provider, "words", 5);
        logMethodResult(provider, "sentence");
        logMethodResult(provider, "sentence", 8);
        logMethodResult(provider, "paragraph");
        
        System.out.println();
    }

    private void logMethodResult(Object provider, String methodName, Object... args) {
        try {
            Class<?>[] paramTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i].getClass();
            }
            
            Method method = findMethod(provider.getClass(), methodName, paramTypes);
            Object result = method.invoke(provider, args);
            System.out.println(methodName + "(" + Arrays.toString(args) + "): " + result);
        } catch (Exception e) {
            System.out.println(methodName + "(" + Arrays.toString(args) + "): ERROR - " + e.getMessage());
        }
    }

    private Method findMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) throws NoSuchMethodException {
        try {
            return clazz.getMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(methodName) && method.getParameterCount() == paramTypes.length) {
                    return method;
                }
            }
            throw new NoSuchMethodException(methodName);
        }
    }

    public static void main(String[] args) {
        DataMirageLocale locale = DataMirageLocale.TR_TR; // Varsayılan: Türkçe
        
        if (args.length > 0) {
            try {
                locale = DataMirageLocale.fromCode(args[0]);
            } catch (IllegalArgumentException e) {
                System.err.println("Warning: Invalid locale code '" + args[0] + "'. Using default locale TR_TR.");
            }
        }
        
        ProviderTester tester = new ProviderTester(locale);
        tester.testAllProviders();
    }
} 