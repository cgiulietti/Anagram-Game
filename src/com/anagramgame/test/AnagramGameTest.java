package com.anagramgame.test;

import org.junit.Before;
import org.junit.Test;

import com.anagramgame.AnagramGame;
import com.anagramgame.AnagramGameImpl;
import com.anagramgame.WordDictionary;
import com.anagramgame.WordDictionaryImpl;

import static org.junit.Assert.assertEquals;

public class AnagramGameTest {
    WordDictionary dictionary;
    AnagramGame service;

    public AnagramGameTest() {
        dictionary = new WordDictionaryImpl();
    }

    @Before
    public void setUp() throws Exception {
        service = new AnagramGameImpl("areallylongword", dictionary);
    }

    @Test
    public void testSubmission() throws Exception {
        assertEquals(2, service.submitWord("1", "no"));
        assertEquals(4, service.submitWord("2", "grow"));
        assertEquals(0, service.submitWord("3", "bold"));
        assertEquals(0, service.submitWord("4", "glly"));
        assertEquals(6, service.submitWord("5", "woolly"));
        assertEquals(0, service.submitWord("6", "adder"));
    }
    
    
    @Test
    public void testSubmissionAndGetUser() throws Exception {
        assertEquals(2, service.submitWord("1", "no"));
        assertEquals(4, service.submitWord("2", "grow"));
        assertEquals(0, service.submitWord("3", "bold"));
        assertEquals(0, service.submitWord("4", "glly"));
        assertEquals(null, service.getUserNameAtPosition(5));
        assertEquals(6, service.submitWord("5", "woolly"));
        assertEquals(0, service.submitWord("6", "adder"));
        assertEquals(4, service.submitWord("7", "grow"));
        assertEquals(4, service.submitWord("8", "grow"));
        assertEquals(4, service.submitWord("9", "grow"));
        assertEquals(2, service.submitWord("10", "no"));
        assertEquals(2, service.submitWord("11", "no"));
        assertEquals(2, service.submitWord("12", "no"));
        assertEquals(2, service.submitWord("13", "no"));
        assertEquals(6, service.submitWord("14", "woolly"));
        assertEquals(6, service.submitWord("15", "woolly"));
        assertEquals("5", service.getUserNameAtPosition(0));
        assertEquals("woolly", service.getWordEntryAtPosition(0));
        assertEquals(new Integer(6), service.getScoreAtPosition(0));
        assertEquals("15", service.getUserNameAtPosition(2));
        assertEquals("woolly", service.getWordEntryAtPosition(2));
        assertEquals(new Integer(6), service.getScoreAtPosition(2));
        assertEquals("11", service.getUserNameAtPosition(9));
        assertEquals("no", service.getWordEntryAtPosition(9));
        assertEquals(new Integer(2), service.getScoreAtPosition(9));
    }
}
