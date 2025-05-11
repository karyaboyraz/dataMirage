package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating vehicle-related data.
 * This class provides methods to generate various vehicle information such as
 * vehicle names, manufacturers, models, types, fuel types, VIN numbers, colors, and license plates.
 */
public class VehicleProvider {
    private final RandomService random;
    private List<String> vehicles;
    private List<String> manufacturers;
    private List<String> models;
    private List<String> types;
    private List<String> fuels;
    private List<String> colors;
    private List<String> licensePlateFormats;
    private static final String VIN_CHARS = "0123456789ABCDEFGHJKLMNPRSTUVWXYZ";
    private static final String[] WMI_CODES = {"1HG", "2HG", "3HG", "1G1", "2G1", "3G1", "1GC", "2GC", "3GC"};

    /**
     * Constructs a new VehicleProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public VehicleProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random vehicle name.
     *
     * @return A random vehicle name as a string
     */
    public String vehicle() {
        vehicles = LazyLoader.load("vehicleVehicles", () -> DataLoader.getListData("vehicle", "vehicles"));
        return random.randomElement(vehicles);
    }

    /**
     * Generates a random vehicle manufacturer name.
     *
     * @return A random vehicle manufacturer name as a string
     */
    public String manufacturer() {
        manufacturers = LazyLoader.load("vehicleManufacturers", () -> DataLoader.getListData("vehicle", "manufacturers"));
        return random.randomElement(manufacturers);
    }

    /**
     * Generates a random vehicle model name.
     *
     * @return A random vehicle model name as a string
     */
    public String model() {
        models = LazyLoader.load("vehicleModels", () -> DataLoader.getListData("vehicle", "models"));
        return random.randomElement(models);
    }

    /**
     * Generates a random vehicle type.
     *
     * @return A random vehicle type as a string
     */
    public String type() {
        types = LazyLoader.load("vehicleTypes", () -> DataLoader.getListData("vehicle", "types"));
        return random.randomElement(types);
    }

    /**
     * Generates a random fuel type.
     *
     * @return A random fuel type as a string
     */
    public String fuel() {
        fuels = LazyLoader.load("vehicleFuels", () -> DataLoader.getListData("vehicle", "fuels"));
        return random.randomElement(fuels);
    }

    /**
     * Generates a random Vehicle Identification Number (VIN).
     * The VIN includes a valid check digit and follows standard VIN format.
     *
     * @return A 17-character VIN as a string
     */
    public String vin() {
        StringBuilder vinBuilder = new StringBuilder();
        vinBuilder.append(random.randomElement(WMI_CODES));
        
        vinBuilder.append(random.randomString(5, true, true, false, false));
        
        String partialVin = vinBuilder.toString();
        char checkDigit = calculateCheckDigit(partialVin);
        vinBuilder.append(checkDigit);
        
        vinBuilder.append(random.randomString(8, true, true, false, false));
        
        return vinBuilder.toString();
    }

    /**
     * Calculates the check digit for a partial VIN.
     *
     * @param partialVin The partial VIN to calculate the check digit for
     * @return The calculated check digit as a character
     */
    private char calculateCheckDigit(String partialVin) {
        int[] weights = {8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;
        
        for (int i = 0; i < partialVin.length(); i++) {
            char c = partialVin.charAt(i);
            int value = VIN_CHARS.indexOf(c);
            sum += value * weights[i];
        }
        
        int checkDigit = sum % 11;
        return checkDigit == 10 ? 'X' : VIN_CHARS.charAt(checkDigit);
    }

    /**
     * Generates a random vehicle color.
     *
     * @return A random vehicle color as a string
     */
    public String color() {
        colors = LazyLoader.load("vehicleColors", () -> DataLoader.getListData("color", "color_names"));
        return random.randomElement(colors);
    }

    /**
     * Generates a random license plate number.
     *
     * @return A random license plate number as a string
     */
    public String licensePlate() {
        licensePlateFormats = LazyLoader.load("vehicleLicensePlateFormats", () -> DataLoader.getListData("vehicle", "license_plate_formats"));
        return random.randomize(random.randomElement(licensePlateFormats));
    }

    /**
     * Generates a random vehicle make (manufacturer).
     *
     * @return A randomly selected vehicle make
     */
    public String make() {
        manufacturers = LazyLoader.load("vehicleManufacturers", () -> DataLoader.getListData("vehicle", "manufacturers"));
        return random.randomElement(manufacturers);
    }

    /**
     * Generates a random vehicle year between 1990 and the current year.
     *
     * @return A random vehicle year
     */
    public int year() {
        return random.nextInt(1990, java.time.Year.now().getValue());
    }

    /**
     * Main method for testing the functionality of VehicleProvider.
     * This method demonstrates the usage of various vehicle-related data generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        VehicleProvider vehicleProvider = new VehicleProvider(new RandomService());
        System.out.println("Make: " + vehicleProvider.make());
        System.out.println("Model: " + vehicleProvider.model());
        System.out.println("Type: " + vehicleProvider.type());
        System.out.println("Color: " + vehicleProvider.color());
        System.out.println("Year: " + vehicleProvider.year());
        System.out.println("License Plate: " + vehicleProvider.licensePlate());
        System.out.println("VIN: " + vehicleProvider.vin());
    }
} 