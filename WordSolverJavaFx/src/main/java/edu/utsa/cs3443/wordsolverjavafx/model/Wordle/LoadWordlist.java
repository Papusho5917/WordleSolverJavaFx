package edu.utsa.cs3443.wordsolverjavafx.model.Wordle;

/** This is the class that loads and deals with the wordlist
 *
 */



import java.util.*;
import java.io.*;

public class LoadWordlist{

    private ArrayList<String> wordlist;

    private static LoadWordlist instance;

    private String wordlistFileName = "data/valid-wordle-words.txt";








    private LoadWordlist(){ wordlist = new ArrayList<String>(); }


    public static LoadWordlist getInstance() throws IOException {
        if(instance == null)
        {
            instance = new LoadWordlist();
            instance.loadWords();
        }
        return instance;
    }



    public void loadWords() throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(wordlistFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordlist.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    public ArrayList<String> getWordlist(){
        return wordlist;
    }

    public void add(String s){
        wordlist.add(s);
    }

    public void remove(String s) {wordlist.remove(s);}

    public void addWord(String s)
    {
        add(s);
        Collections.sort(wordlist);
        saveDataToFile();
    }

    public boolean deleteWord(String s){

        for(String wordInList : wordlist)
        {
            if(wordInList.equals(s))
            {
                wordlist.remove(wordInList);
                Collections.sort(wordlist);
                saveDataToFile();
                return true;
            }
        }
        return false;

    }



    public String findWord(String word)
    {
        for(String wordInList : wordlist)
        {
            if(wordInList.equals(word))
            {
                return wordInList;
            }
        }
        return null;
    }


    public boolean isWordExists(String word)
    {
        return findWord(word) != null;
    }



    private void saveDataToFile()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(wordlistFileName));
            for(String word : wordlist){
                bw.write(word);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error updating words data to file: " + e.getMessage());
        }
    }

    public String getWordlistFileName() { return wordlistFileName; }
}