package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Type the number of books you want to read: " );
        int books = Integer.parseInt((reader.readLine()));
        readBooks(reader, books);
    }

    private static void readBooks(BufferedReader reader, int books) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        WordsCounter wordsCounter = WordsCounter.getInstance();
        Hashtable<String, Integer> allThreadResponses = new Hashtable<String, Integer>();
        ArrayList<String>filePaths=new ArrayList<>();
        ArrayList<Callable<Hashtable<String, Integer>>> callables = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(books);
       for (int i = 0; i < books; i++) {

            for (int j = 0; j < books; j++) {
                if(filePaths.size() == books)
                    break;
                System.out.println("Type the full file pathName: ");
                filePaths.add(reader.readLine());
            }
            Callable<Hashtable<String, Integer>> callable = new ReadBook(filePaths.get(i));
            callables.add(callable);
        }

        List<Future<Hashtable<String, Integer>>>  futures = executor.invokeAll(callables);
        for (int i = 0; i < books; i++) {
            allThreadResponses.putAll(futures.get(i).get());
        }
        wordsCounter.getMostOccurringWord(allThreadResponses);

        executor.shutdown();
    }
}
