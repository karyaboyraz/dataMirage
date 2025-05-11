package com.datamirage.util;

import java.util.Random;

public class WordGenerator {
    private static final String VOWELS = "aeiou";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
    private final Random random;

    public WordGenerator(Random random) {
        this.random = random;
    }

    public String generateWord() {
        int length = random.nextInt(3, 8);
        StringBuilder word = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                word.append(getRandomChar(CONSONANTS));
            } else {
                word.append(getRandomChar(VOWELS));
            }
        }
        
        return word.toString();
    }

    private char getRandomChar(String source) {
        return source.charAt(random.nextInt(source.length()));
    }
} 