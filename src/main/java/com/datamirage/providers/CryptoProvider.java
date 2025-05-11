package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating cryptocurrency-related data.
 * This class provides methods to generate various crypto-related information such as
 * coin names, symbols, blockchain names, wallet addresses, and transaction hashes.
 */
public class CryptoProvider {
    private final RandomService random;
    private List<String> coinNames;
    private List<String> coinSymbols;
    private List<String> blockchains;

    /**
     * Constructs a new CryptoProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public CryptoProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random cryptocurrency coin name.
     *
     * @return A random cryptocurrency coin name as a string
     */
    public String coinName() {
        coinNames = LazyLoader.load("cryptoCoinNames", () -> DataLoader.getListData("crypto", "coin_names"));
        return random.randomElement(coinNames);
    }

    /**
     * Generates a random cryptocurrency coin symbol.
     *
     * @return A random cryptocurrency coin symbol as a string
     */
    public String coinSymbol() {
        coinSymbols = LazyLoader.load("cryptoCoinSymbols", () -> DataLoader.getListData("crypto", "coin_symbols"));
        return random.randomElement(coinSymbols);
    }

    /**
     * Generates a random blockchain name.
     *
     * @return A random blockchain name as a string
     */
    public String blockchain() {
        blockchains = LazyLoader.load("cryptoBlockchains", () -> DataLoader.getListData("crypto", "blockchains"));
        return random.randomElement(blockchains);
    }

    /**
     * Generates a random cryptocurrency wallet address.
     * The address follows the standard format starting with '1' or '3' and is 34 characters long.
     *
     * @return A random cryptocurrency wallet address as a string
     */
    public String walletAddress() {
        String chars = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        
        // Cüzdan adresi her zaman '1' veya '3' ile başlar
        sb.append(random.nextBoolean() ? '1' : '3');
        
        // Kalan 33 karakter
        for (int i = 0; i < 33; i++) {
            sb.append(chars.charAt(random.nextInt(0, chars.length() - 1)));
        }
        
        return sb.toString();
    }

    /**
     * Generates a random cryptocurrency transaction hash.
     * The hash is a 64-character hexadecimal string.
     *
     * @return A random transaction hash as a string
     */
    public String transactionHash() {
        String hex = "abcdef0123456789";
        StringBuilder sb = new StringBuilder(64);
        for (int i = 0; i < 64; i++) {
            sb.append(hex.charAt(random.nextInt(0, hex.length() - 1)));
        }
        return sb.toString();
    }

    /**
     * Main method for testing the functionality of CryptoProvider.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        CryptoProvider cryptoProvider = new CryptoProvider(new RandomService());
        System.out.println("Coin Name: " + cryptoProvider.coinName());
        System.out.println("Coin Symbol: " + cryptoProvider.coinSymbol());
        System.out.println("Blockchain: " + cryptoProvider.blockchain());
        System.out.println("Wallet Address: " + cryptoProvider.walletAddress());
        System.out.println("Transaction Hash: " + cryptoProvider.transactionHash());
    }
}