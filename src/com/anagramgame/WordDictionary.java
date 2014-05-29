package com.anagramgame;

/**
 * Implementations of this interface to be threadsafe.
 */
public interface WordDictionary {

    /**
     * @param word
     * @return true if the dictionary contains the word
     */
    public boolean contains(String word);

    public int size();
}
