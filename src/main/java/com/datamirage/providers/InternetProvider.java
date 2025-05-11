package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating fake internet-related data.
 * This class provides methods to generate various components of internet information,
 * including email addresses, usernames, domain names, URLs, IP addresses, and more.
 */
public class InternetProvider {
    private final RandomService random;
    private List<String> domainNames;

    /**
     * Constructs a new InternetProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public InternetProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random email address using a username and domain name.
     *
     * @return A randomly generated email address
     */
    public String email() {
        return new NameProvider(random).username() + "@" + domainName();
    }

    /**
     * Generates a random username.
     *
     * @return A randomly generated username
     */
    public String username() {
        return new NameProvider(random).username();
    }

    /**
     * Generates a random domain name.
     * Ensures the generated domain name follows the standard format.
     *
     * @return A randomly selected domain name
     */
    public String domainName() {
        domainNames = LazyLoader.load("internetDomainNames", () -> DataLoader.getListData("internet", "domainNames"));
        String domain = random.randomElement(domainNames);
        // Eğer domain adı regex formatına uymuyorsa, geçerli bir domain adı oluştur
        if (!domain.matches("^[\\w-]+\\.[a-zA-Z]{2,}$")) {
            String[] parts = domain.split("\\.");
            if (parts.length >= 2) {
                domain = parts[0] + "." + parts[1];
            }
        }
        return domain;
    }

    /**
     * Generates a random URL with either http or https protocol.
     *
     * @return A randomly generated URL
     */
    public String url() {
        String protocol = random.nextBoolean() ? "https" : "http";
        String domain = domainName();
        return protocol + "://www." + domain;
    }

    /**
     * Generates a random IPv4 address.
     *
     * @return A string representation of a random IPv4 address
     */
    public String ipv4() {
        return random.nextInt(0, 255) + "." +
               random.nextInt(0, 255) + "." +
               random.nextInt(0, 255) + "." +
               random.nextInt(0, 255);
    }

    /**
     * Generates a random IPv6 address.
     *
     * @return A string representation of a random IPv6 address
     */
    public String ipv6() {
        StringBuilder ipv6 = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (i > 0) {
                ipv6.append(":");
            }
            ipv6.append(String.format("%04x", random.nextInt(0, 65535)));
        }
        return ipv6.toString();
    }

    /**
     * Generates a random MAC address.
     *
     * @return A string representation of a random MAC address
     */
    public String macAddress() {
        StringBuilder mac = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i > 0) {
                mac.append(":");
            }
            mac.append(String.format("%02x", random.nextInt(0, 255)));
        }
        return mac.toString();
    }

    /**
     * Generates a random password with default length of 10 characters.
     * The password includes uppercase letters and digits.
     *
     * @return A randomly generated password
     */
    public String password() {
        return random.randomString(10, true, true, false, false);
    }

    /**
     * Generates a random password with the specified length.
     * The password includes uppercase letters and digits.
     *
     * @param length The desired length of the password
     * @return A randomly generated password with the specified length
     */
    public String password(int length) {
        return random.randomString(length, true, true, false, false);
    }

    /**
     * Generates a random password with custom rules.
     *
     * @param length The desired length of the password
     * @param includeUpper Whether to include uppercase letters
     * @param includeDigits Whether to include digits
     * @param includeSymbols Whether to include special symbols
     * @return A randomly generated password following the specified rules
     */
    public String passwordWithRules(int length, boolean includeUpper, boolean includeDigits, boolean includeSymbols) {
        return random.randomString(length, includeDigits, includeUpper, includeUpper, includeSymbols);
    }

    /**
     * Generates a random port number between 1 and 65535.
     *
     * @return A random port number
     */
    public int randomPort() {
        return random.nextInt(1, 65536);
    }

    /**
     * Generates a random emoji from a predefined set.
     *
     * @return A randomly selected emoji
     */
    public String randomEmoji() {
        String[] emojis = {
            "😀","😂","🥰","😎","🤖","🎉","🚀","🍕","🌟","🔥",
            "🐱","🐶","🦄","🍀","🎶","⚽","🏀","🏆","🎲","🧩"
        };
        return emojis[random.nextInt(0, emojis.length - 1)];
    }

    /**
     * Main method for testing the InternetProvider functionality.
     * This method demonstrates the usage of various internet-related data generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        InternetProvider internetProvider = new InternetProvider(new RandomService());
        System.out.println("Email: " + internetProvider.email());
        System.out.println("Username: " + internetProvider.username());
        System.out.println("Domain Name: " + internetProvider.domainName());
        System.out.println("URL: " + internetProvider.url());
        System.out.println("IPv4: " + internetProvider.ipv4());
        System.out.println("IPv6: " + internetProvider.ipv6());
        System.out.println("MAC Address: " + internetProvider.macAddress());
        System.out.println("Password: " + internetProvider.password());
        System.out.println("Password (custom length): " + internetProvider.password(16));
        System.out.println("Password with rules: " + internetProvider.passwordWithRules(11, false, true, false));
        System.out.println("Random Port: " + internetProvider.randomPort());
        System.out.println("Random Emoji: " + internetProvider.randomEmoji());
    }
}
