package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.Callable;

public class ReadBook implements Callable<Hashtable<String, Integer>> {

    String file;
    String line= "";
    WordsHolder wordsHolder = WordsHolder.getInstance();

    public ReadBook(String file) {
        this.file = file;
    }

    @Override
    public Hashtable<String, Integer> call() throws Exception {
        try {
            FileReader fr = new FileReader(this.file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String string[] = line.toLowerCase().split("[ .,]+");
                for (String s : string) {
                    wordsHolder.addWord(s);
                }

            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsHolder.getAllWords();
    }
}
