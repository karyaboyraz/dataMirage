package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating vehicle-related data.
 * This class provides methods to generate various vehicle information such as
 * vehicle names, manufacturers, models, types, fuel types, VIN numbers, colors, and license plates.
 */
public class VehicleProvider extends AbstractProvider {
    private static final String VIN_CHARS = "0123456789ABCDEFGHJKLMNPRSTUVWXYZ";
    private static final String[] WMI_CODES = {"1HG", "2HG", "3HG", "1G1", "2G1", "3G1", "1GC", "2GC", "3GC"};

    /**
     * Constructs a new VehicleProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public VehicleProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new VehicleProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #VehicleProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public VehicleProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random vehicle name.
     *
     * @return A random vehicle name as a string
     */
    public String vehicle() {
        return randomFromLocaleList("vehicleVehicles", "vehicle", "vehicles");
    }

    /**
     * Generates a random vehicle manufacturer name.
     *
     * @return A random vehicle manufacturer name as a string
     */
    public String manufacturer() {
        return randomFromLocaleList("vehicleManufacturers", "vehicle", "manufacturers");
    }

    /**
     * Generates a random vehicle model name.
     *
     * @return A random vehicle model name as a string
     */
    public String model() {
        return randomFromLocaleList("vehicleModels", "vehicle", "models");
    }

    /**
     * Generates a random vehicle type.
     *
     * @return A random vehicle type as a string
     */
    public String type() {
        return randomFromLocaleList("vehicleTypes", "vehicle", "types");
    }

    /**
     * Generates a random fuel type.
     *
     * @return A random fuel type as a string
     */
    public String fuel() {
        return randomFromLocaleList("vehicleFuels", "vehicle", "fuels");
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
        return randomFromLocaleList("vehicleColors", "color", "color_names");
    }

    /**
     * Generates a random license plate number.
     *
     * @return A random license plate number as a string
     */
    public String licensePlate() {
        String format = randomFromLocaleList("vehicleLicensePlateFormats", "vehicle", "license_plate_formats");
        return random.randomize(format);
    }

    /**
     * Generates a random vehicle make (manufacturer).
     *
     * @return A randomly selected vehicle make
     */
    public String make() {
        return randomFromLocaleList("vehicleManufacturers", "vehicle", "manufacturers");
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
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        VehicleProvider vehicleProvider = new VehicleProvider(new RandomService());
        System.out.println("Make: " + vehicleProvider.make());
        System.out.println("Model: " + vehicleProvider.model());
        System.out.println("Type: " + vehicleProvider.type());
        System.out.println("Color: " + vehicleProvider.color());
        System.out.println("Year: " + vehicleProvider.year());
        System.out.println("License Plate: " + vehicleProvider.licensePlate());
        System.out.println("VIN: " + vehicleProvider.vin());
        System.out.println("Fuel: " + vehicleProvider.fuel());
        System.out.println("Vehicle: " + vehicleProvider.vehicle());
        System.out.println("Manufacturer: " + vehicleProvider.manufacturer());
        System.out.println("Vehicle Name: " + vehicleProvider.vehicle());
        System.out.println("Vehicle Model: " + vehicleProvider.model());
        System.out.println("Vehicle Type: " + vehicleProvider.type());
        System.out.println("Vehicle Fuel: " + vehicleProvider.fuel());
        System.out.println("Vehicle VIN: " + vehicleProvider.licensePlate());

    }
}
