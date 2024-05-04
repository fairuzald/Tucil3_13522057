package com.tucilstima3.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    private static Map<Integer, List<String>> dictionary = new LinkedHashMap<>();

    // Initialize the dictionary by loading the words from the text file
    static {
        loadDictionary("dictionary.txt");
    }
    
    // Load the dictionary from the text file
    public static void loadDictionary(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                int length = word.length();
                dictionary.computeIfAbsent(length, k -> new ArrayList<>()).add(word);
            }
        } catch (IOException e) {
            System.err.println("Error reading text file: " + e.getMessage());
        }
    }

    // Check if a word is a valid English word
    public static boolean isValidWord(String word) {
        List<String> words = dictionary.get(word.length());
        return words != null && words.contains(word);
    }

    // Get all words of a specific length
    public static List<String> getWordsByLength(int length) {
        return dictionary.getOrDefault(length, new ArrayList<>());
    }

    // Get all words in the dictionary
    public static List<String> getAllWords() {
        List<String> allWords = new ArrayList<>();
        dictionary.values().forEach(allWords::addAll);
        return allWords;
    }
}
