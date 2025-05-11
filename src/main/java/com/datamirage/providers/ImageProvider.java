package com.datamirage.providers;

public class ImageProvider {

    /**
     * Generates a random image URL with the specified dimensions.
     *
     * @param width The width of the image in pixels
     * @param height The height of the image in pixels
     * @return A URL pointing to a random image with the specified dimensions
     */
    public String imageUrl(int width, int height) {
        return String.format("https://picsum.photos/%d/%d", width, height);
    }

    public String imageUrl(int width, int height, String category) {
        return String.format("https://picsum.photos/%d/%d?category=%s", width, height, category);
    }

    public String imageUrl(int width, int height, String category, String search) {
        return String.format("https://picsum.photos/%d/%d?category=%s&search=%s", width, height, category, search);
    }

    public String imageUrl(int width, int height, String category, String search, String seed) {
        return String.format("https://picsum.photos/seed/%s/%d/%d?category=%s&search=%s", seed, width, height, category, search);
    }

    /**
     * Generates a random avatar image URL.
     * The image is a square with the specified size.
     *
     * @param size The size of the avatar in pixels
     * @return A URL pointing to a random avatar image
     */
    public String avatarUrl(int size) {
        return String.format("https://i.pravatar.cc/%d", size);
    }

    /**
     * Generates a random placeholder image URL with the specified dimensions.
     * The image will contain placeholder text showing the dimensions.
     *
     * @param width The width of the image in pixels
     * @param height The height of the image in pixels
     * @return A URL pointing to a placeholder image
     */
    public String placeholderUrl(int width, int height) {
        return String.format("https://via.placeholder.com/%dx%d", width, height);
    }

    /**
     * Main method for testing the functionality of ImageProvider.
     * This method demonstrates the usage of various image URL generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        ImageProvider imageProvider = new ImageProvider();
        System.out.println(imageProvider.imageUrl(800, 600));
        System.out.println(imageProvider.imageUrl(800, 600, "nature"));
        System.out.println(imageProvider.imageUrl(800, 600, "nature", "mountain"));
        System.out.println(imageProvider.imageUrl(800, 600, "nature", "mountain", "sunset"));
    }
}