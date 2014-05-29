package com.anagramgame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class WordDictionaryImpl implements WordDictionary {

    private final HashSet<String> dictionary = new HashSet<String>();

    public WordDictionaryImpl() {
    	BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("dictionary.txt"));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				dictionary.add(inputLine);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Override
    public boolean contains(String word) {
        return dictionary.contains(word);
    }

    @Override
    public int size() {
        return dictionary.size();
    }
}
