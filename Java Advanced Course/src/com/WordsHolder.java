package com;

import java.util.Hashtable;
import java.util.Map;

public class WordsHolder {

    private static WordsHolder single_instance = null;

    Map<String, Integer> words = new Hashtable<>();

    private WordsHolder(){

    }

    public static WordsHolder getInstance(){
        if(single_instance == null)
            single_instance = new WordsHolder();
        return single_instance;
    }

    protected void addWord(String word) {
        if (words.containsKey(word))
            words.compute(word, (k, v) -> v + 1);
        else
            words.put(word, 1);
    }

    public Hashtable<String, Integer> getAllWords(){
        return (Hashtable<String, Integer>) words;
    }
}
