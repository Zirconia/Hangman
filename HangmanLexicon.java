/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file reads words from a .txt file into
 * an ArrayList. The ArrayList is used for
 * selecting a random secret word.
 */

import java.util.ArrayList;
import java.io.*;

public class HangmanLexicon {
    BufferedReader lexReader;
    ArrayList<String> lexList;
    
    //constructor reads words from .txt file into ArrayList
    public HangmanLexicon(){
        lexList = new ArrayList<String>();
        
        try{
            File file = new File("HangmanLexicon.txt");
            lexReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = lexReader.readLine()) != null){
                lexList.add(line);   
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                lexReader.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    /** Returns the number of words in the lexicon. */
    public int getWordCount() {
    
        return lexList.size();
    }
    
    /** Returns the word at the specified index. */
    public String getWord(int index) {
        String word = lexList.get(index);
        
        return word;    
    }
}

