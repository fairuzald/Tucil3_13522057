package com.tucilstima3.backend.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class Dictionary {
    private HashSet<String> dictionary = new HashSet<>();

    public Dictionary(String filepath) throws Exception {
		FileReader fileReader = new FileReader(filepath);
		BufferedReader reader = new BufferedReader(fileReader);

		String word = reader.readLine();
		while (word != null) {
			this.dictionary.add(word);
			word = reader.readLine();
		}

		fileReader.close();
	}

    public boolean isValidWord(String word) {
        return this.dictionary.contains(word);
    }

}