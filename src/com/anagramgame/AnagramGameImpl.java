package com.anagramgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AnagramGameImpl implements AnagramGame {

	private final static int MAX_SCORES_STORED = 10;
	
	private final WordDictionary dictionary;
	private final HashMap<Character, Integer> letters;
	
	private final ArrayList<User> usersScore = new ArrayList<User>();
	
	public AnagramGameImpl(String word, WordDictionary dictionary) {
		super();
		
		this.dictionary = dictionary;
		
		/* initialize the letters map */
		this.letters = new HashMap<Character, Integer>();
		Integer count;
		for (int i = 0; i < word.length(); i++) {
			count = letters.get(word.charAt(i));
			if ( count == null ){ 
				/* insert character and set counter to 1 */
				letters.put(word.charAt(i), 1);
			}else{
				/* insert character with incremented counter */
				count++;
				letters.put(word.charAt(i), count);
			}
		}
		
	}

	@Override
	public int submitWord(String username, String word) {
		int score = 0;
		Integer count;
		
		/* Use a temporary deep copy of letters map */
		HashMap<Character, Integer> lettersTmp = new HashMap<Character, Integer>(letters);
		
		if ( dictionary.contains(word) ){
			for (int i = 0; i < word.length(); i++) {
				count = lettersTmp.get(word.charAt(i));
				if ( count != null && count > 0 ){
					/* solution can be accepted so far: decrement counter and increment score*/
					count--;
					score++;
					lettersTmp.put(word.charAt(i), count);
				}else{
					return 0;
				}
			}
		}
		
		if ( score > 0 ){
			/* Thread safe: protect usersScore with the object lock */
			synchronized (this) {
				/* add user score */
				usersScore.add(new User(username, score,word));
				Collections.sort(usersScore);
				if ( usersScore.size() > MAX_SCORES_STORED ){
					/* remove last element to keep list to size 10 */
					usersScore.remove(MAX_SCORES_STORED);
				}
			}
		}
		
		return score;
	}

	@Override
	public String getUserNameAtPosition(int position) {
		String user = null;
		
		if ( usersScore.size() > position ){ 
			user = usersScore.get(position).user;
		}
		
		return user;
	}

	@Override
	public String getWordEntryAtPosition(int position) {
		String word = null;
		
		if ( usersScore.size() > position ){ 
			word = usersScore.get(position).word;
		}
		
		return word;
	}

	@Override
	public Integer getScoreAtPosition(int position) {
		Integer score = null;
		
		if ( usersScore.size() > position ){ 
			score = usersScore.get(position).score;
		}
		
		return score;
	}

	/* Private User class to store the info about the best scores */
	private class User implements Comparable<User> {
		final String user;
		final Integer score;
		final String word;
		
		public User(String user, Integer score, String word) {
			super();
			this.user = user;
			this.score = score;
			this.word = word;
		}

		@Override
		public int compareTo(User user) {
			return user.score - this.score;
		}
		
	}
	
}
