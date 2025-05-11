package com.datamirage.util;

public class StringUtils {
    
    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String sanitizeAscii(String input) {
        String normalized = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD);
        String ascii = normalized.replaceAll("\\p{M}", "");
        ascii = ascii.replace("ı", "i")
                     .replace("İ", "I")
                     .replace("ş", "s")
                     .replace("Ş", "S")
                     .replace("ğ", "g")
                     .replace("Ğ", "G")
                     .replace("ü", "u")
                     .replace("Ü", "U")
                     .replace("ö", "o")
                     .replace("Ö", "O")
                     .replace("ç", "c")
                     .replace("Ç", "C");
        return ascii;
    }
} 