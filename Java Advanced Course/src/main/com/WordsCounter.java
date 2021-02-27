package com;
import java.util.*;

public class WordsCounter {

    private static WordsCounter single_instance = null;

    private WordsCounter() {}

    public static WordsCounter getInstance(){
        if(single_instance == null)
            single_instance = new WordsCounter();

        return single_instance;
    }

    public void getMostOccurringWord(Hashtable<String, Integer> words){
        ArrayList<Map.Entry<String, Integer>> values = new ArrayList<>(words.entrySet());
        values.sort(new WordComparator());
        System.out.print("The most occurring word in the books is the word --> { " +values.get(0).getKey().toUpperCase() + " } ");
        System.out.println("occurring exactly ("+values.get(0).getValue() + ") times! :)");
    }

    static class WordComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }
    }
}