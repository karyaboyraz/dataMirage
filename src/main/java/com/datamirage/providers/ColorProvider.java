package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating fake color-related data.
 * This class provides methods to generate various color representations,
 * including color names, hex codes, RGB values, RGBA values, and HSL values.
 */
public class ColorProvider {
    private final RandomService random;
    private List<String> colorNames;

    /**
     * Constructs a new ColorProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public ColorProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random color name.
     *
     * @return A randomly selected color name
     */
    public String name() {
        colorNames = LazyLoader.load("colorNames", () -> DataLoader.getListData("color", "color_names"));
        return random.randomElement(colorNames);
    }

    /**
     * Generates a random color in hexadecimal format.
     * The format is #RRGGBB where RR, GG, and BB are hexadecimal values.
     *
     * @return A string representation of a color in hexadecimal format
     */
    public String hex() {
        return String.format("#%06x", random.nextInt(0, 0xFFFFFF));
    }

    /**
     * Generates a random color in RGB format.
     * The format is rgb(R, G, B) where R, G, and B are values between 0 and 255.
     *
     * @return A string representation of a color in RGB format
     */
    public String rgb() {
        return String.format("rgb(%d, %d, %d)",
            random.nextInt(0, 255),
            random.nextInt(0, 255),
            random.nextInt(0, 255)
        );
    }

    /**
     * Generates a random color in RGBA format.
     * The format is rgba(R, G, B, A) where R, G, and B are values between 0 and 255,
     * and A is a value between 0 and 0.99.
     *
     * @return A string representation of a color in RGBA format
     */
    public String rgba() {
        return String.format("rgba(%d, %d, %d, 0.%d)",
            random.nextInt(0, 255),
            random.nextInt(0, 255),
            random.nextInt(0, 255),
            random.nextInt(0, 99)
        );
    }

    /**
     * Generates a random color in HSL format.
     * The format is hsl(H, S%, L%) where H is a value between 0 and 360,
     * and S and L are values between 0 and 100.
     *
     * @return A string representation of a color in HSL format
     */
    public String hsl() {
        return String.format("hsl(%d, %d%%, %d%%)",
            random.nextInt(0, 360),
            random.nextInt(0, 100),
            random.nextInt(0, 100)
        );
    }

    /**
     * Main method for testing the ColorProvider functionality.
     * This method demonstrates the usage of various color generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        ColorProvider colorProvider = new ColorProvider(new RandomService());
        System.out.println("Color Name: " + colorProvider.name());
        System.out.println("Hex: " + colorProvider.hex());
        System.out.println("RGB: " + colorProvider.rgb());
        System.out.println("RGBA: " + colorProvider.rgba());
        System.out.println("HSL: " + colorProvider.hsl());
    }
} 