package com.tucilstima3.backend.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class Dictionary {
    private HashSet<String> dictionary = new HashSet<>();

    public Dictionary(String filepath) throws Exception {
		FileReader fr= new FileReader(filepath);
		BufferedReader reader = new BufferedReader(fr);

		String word = reader.readLine();
		while (word != null) {
			this.dictionary.add(word);
			word = reader.readLine();
		}

		fr.close();
	}

    public boolean isValidWord(String word) {
        return this.dictionary.contains(word);
    }

}