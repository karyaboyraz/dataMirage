package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import com.datamirage.util.RandomService;

import java.text.DecimalFormat;
import java.util.List;

/**
 * A provider class for generating address-related data.
 * This class provides methods to generate various address information such as
 * city names, street names, postal codes, and full addresses.
 */
public class AddressProvider extends AbstractProvider {
    private List<String> cities;
    private List<String> streets;
    private List<String> streetSuffixes;
    private List<String> states;
    private List<String> stateAbbrs;
    private List<String> countries;
    private List<String> countryCodes;
    private List<String> districts;
    private List<String> zipFormats;
    private List<String> streetPatterns;
    private List<String> fullPatterns;
    private List<String> buildingNumbers;
    private List<String> buildings;
    private List<String> apartments;
    private final DecimalFormat coordinateFormat = new DecimalFormat("###.######");

    /**
     * Constructs a new AddressProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public AddressProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random city name.
     *
     * @return A randomly selected city name
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String city() {
        requireLocaleSpecificData("address", "cities");
        cities = LazyLoader.load("addressCities", () -> DataLoader.getListData("address", "cities"));
        return random.randomElement(cities);
    }

    /**
     * Generates a random street name.
     *
     * @return A randomly selected street name
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String streetName() {
        requireLocaleSpecificData("address", "streets");
        streets = LazyLoader.load("addressStreets", () -> DataLoader.getListData("address", "streets"));
        return random.randomElement(streets);
    }

    /**
     * Generates a random street suffix (e.g., Avenue, Street, Road).
     *
     * @return A randomly selected street suffix
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String streetSuffix() {
        requireLocaleSpecificData("address", "street_suffixes");
        streetSuffixes = LazyLoader.load("addressStreetSuffixes", () -> DataLoader.getListData("address", "street_suffixes"));
        return random.randomElement(streetSuffixes);
    }

    /**
     * Generates a random state or province name.
     *
     * @return A randomly selected state name
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String state() {
        requireLocaleSpecificData("address", "states");
        states = LazyLoader.load("addressStates", () -> DataLoader.getListData("address", "states"));
        return random.randomElement(states);
    }

    /**
     * Generates a random state abbreviation.
     *
     * @return A randomly selected state abbreviation
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String stateAbbr() {
        if (!hasLocaleSpecificData("address", "state_abbrs")) {
            throw new IllegalStateException("State abbreviations not available for locale: " + DataLoader.getCurrentLocale());
        }
        stateAbbrs = LazyLoader.load("addressStateAbbrs", () -> DataLoader.getListData("address", "state_abbrs"));
        return random.randomElement(stateAbbrs);
    }

    /**
     * Generates a random country name.
     *
     * @return A randomly selected country name
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String country() {
        requireLocaleSpecificData("address", "countries");
        countries = LazyLoader.load("addressCountries", () -> DataLoader.getListData("address", "countries"));
        return random.randomElement(countries);
    }

    /**
     * Generates a random country code.
     *
     * @return A randomly selected country code
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String countryCode() {
        if (!hasLocaleSpecificData("address", "country_codes")) {
            throw new IllegalStateException("Country codes not available for locale: " + DataLoader.getCurrentLocale());
        }
        countryCodes = LazyLoader.load("addressCountryCodes", () -> DataLoader.getListData("address", "country_codes"));
        return random.randomElement(countryCodes);
    }

    /**
     * Generates a random district name.
     *
     * @return A randomly selected district name
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String district() {
        requireLocaleSpecificData("address", "districts");
        districts = LazyLoader.load("addressDistricts", () -> DataLoader.getListData("address", "districts"));
        return random.randomElement(districts);
    }

    /**
     * Generates a random postal code based on locale-specific format.
     *
     * @return A randomly generated postal code
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String postalCode() {
        return zipCode();
    }

    /**
     * Generates a random zip code based on locale-specific format.
     *
     * @return A randomly generated zip code
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String zipCode() {
        zipFormats = LazyLoader.load("addressZipFormats", () -> DataLoader.getListData("address", "postal_codes"));
        String format = random.randomElement(zipFormats);
        return random.randomize(format);
    }

    /**
     * Generates a random street address.
     *
     * @return A randomly generated street address
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String streetAddress() {
        requireLocaleSpecificData("address", "street_patterns");
        streetPatterns = LazyLoader.load("addressStreetPatterns", () -> DataLoader.getListData("address", "street_patterns"));
        buildingNumbers = LazyLoader.load("addressBuildingNumbers", () -> DataLoader.getListData("address", "building_number"));
        buildings = LazyLoader.load("addressBuildings", () -> DataLoader.getListData("address", "building"));
        apartments = LazyLoader.load("addressApartments", () -> DataLoader.getListData("address", "apartment"));
        
        String pattern = random.randomElement(streetPatterns);
        pattern = pattern.replace("{{streets}}", streetName());
        pattern = pattern.replace("{{street_suffixes}}", streetSuffix());
        pattern = pattern.replace("{{building_number}}", random.randomElement(buildingNumbers));
        pattern = pattern.replace("{{building}}", random.randomElement(buildings));
        pattern = pattern.replace("{{apartment}}", random.randomElement(apartments));
        
        return pattern;
    }

    /**
     * Generates a random full address including street, city, state, and postal code.
     *
     * @return A randomly generated full address
     * @throws IllegalStateException if the locale-specific data is missing
     */
    public String fullAddress() {
        requireLocaleSpecificData("address", "full_patterns");
        fullPatterns = LazyLoader.load("addressFullPatterns", () -> DataLoader.getListData("address", "full_patterns"));
        
        String pattern = random.randomElement(fullPatterns);
        pattern = pattern.replace("{{cities}}", city());
        pattern = pattern.replace("{{street_patterns}}", streetAddress());
        pattern = pattern.replace("{{postal_codes}}", zipCode());
        pattern = pattern.replace("{{countries}}", country());
        pattern = pattern.replace("{{states}}", state());
        pattern = pattern.replace("{{districts}}", district());
        
        return pattern;
    }

    /**
     * Generates a random building number.
     *
     * @return A randomly generated building number
     */
    public String buildingNumber() {
        buildingNumbers = LazyLoader.load("addressBuildingNumbers", () -> DataLoader.getListData("address", "building_number"));
        return random.randomElement(buildingNumbers);
    }

    /**
     * Generates a random street number.
     *
     * @return A randomly generated street number
     */
    public String streetNumber() {
        return String.valueOf(random.nextInt(1, 1000));
    }

    /**
     * Generates a random latitude.
     *
     * @return A randomly generated latitude
     */
    public String latitude() {
        double lat = random.nextDouble(-90.0, 90.0);
        return coordinateFormat.format(lat);
    }

    /**
     * Generates a random longitude.
     *
     * @return A randomly generated longitude
     */
    public String longitude() {
        double lon = random.nextDouble(-180.0, 180.0);
        return coordinateFormat.format(lon);
    }

    /**
     * Generates random geographic coordinates (latitude, longitude).
     *
     * @return Randomly generated coordinates in the format "latitude, longitude"
     */
    public String coordinates() {
        return latitude() + ", " + longitude();
    }
}
