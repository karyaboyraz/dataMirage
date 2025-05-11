package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class WordProviderTest {
    private WordProvider wordProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        wordProvider = new WordProvider(new RandomService());
    }

    @RepeatedTest(20)
    void word_ShouldReturnValidWord() {
        String word = wordProvider.word();
        assertNotNull(word);
        assertFalse(word.isEmpty());
    }

    @RepeatedTest(20)
    void words_ShouldReturnValidWords() {
        String words = wordProvider.words(5);
        assertNotNull(words);
        assertFalse(words.isEmpty());
        assertEquals(4, words.chars().filter(ch -> ch == ' ').count());
    }

    @RepeatedTest(20)
    void sentence_ShouldReturnValidSentence() {
        String sentence = wordProvider.sentence(5);
        assertNotNull(sentence);
        assertFalse(sentence.isEmpty());
        assertTrue(Character.isUpperCase(sentence.charAt(0)));
        assertTrue(sentence.endsWith("."));
        assertEquals(4, sentence.chars().filter(ch -> ch == ' ').count());
    }

    @RepeatedTest(20)
    void sentences_ShouldReturnValidSentences() {
        String sentences = wordProvider.sentences(2);
        assertNotNull(sentences);
        assertFalse(sentences.isEmpty());
        assertTrue(Character.isUpperCase(sentences.charAt(0)));
        assertTrue(sentences.contains(". "));
        assertEquals(2, sentences.chars().filter(ch -> ch == '.').count());
    }

    @RepeatedTest(20)
    void paragraph_ShouldReturnValidParagraph() {
        String paragraph = wordProvider.paragraph(2);
        assertNotNull(paragraph);
        assertFalse(paragraph.isEmpty());
        assertTrue(Character.isUpperCase(paragraph.charAt(0)));
        assertTrue(paragraph.contains(". "));
        assertEquals(2, paragraph.chars().filter(ch -> ch == '.').count());
    }

    @RepeatedTest(20)
    void paragraphs_ShouldReturnValidParagraphs() {
        String paragraphs = wordProvider.paragraphs(3);
        assertNotNull(paragraphs);
        assertFalse(paragraphs.isEmpty());
        assertTrue(Character.isUpperCase(paragraphs.charAt(0)));
        assertTrue(paragraphs.contains("\n\n"));
        assertEquals(2, paragraphs.chars().filter(ch -> ch == '\n').count() / 2);
    }

    @RepeatedTest(20)
    void text_ShouldReturnValidText() {
        int characterCount = 100;
        String text = wordProvider.text(characterCount);
        assertNotNull(text);
        assertFalse(text.isEmpty());
        assertTrue(Character.isUpperCase(text.charAt(0)));
        assertTrue(text.length() <= characterCount);
    }
} 