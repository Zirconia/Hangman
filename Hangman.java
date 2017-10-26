/*
 * File: Hangman.java
 * ------------------------
 * This file houses the gameplay loop and logic. 
 * 
 */

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import acm.util.*;
import acm.program.*;

public class Hangman extends ConsoleProgram {
    private static HangmanLexicon hl;           //list of secret words
    private static String secret = "";          //chosen secret word
    private static String guess = "";           //player's guess
    private static int numGuesses = 8;          //player's number of guesses remaining
    private static boolean isRunning = true;    //flag to control game loop
    private static String currWord = "";        /*letters the player has guessed correctly
                                                 and hyphens for letters in the secret word                                                
                                                 yet to be guessed
                                                */
    private static String incorrectGuesses = "";//player's incorrect guesses
    private HangmanCanvas canvas;              //stores the graphics display
    
    //creates a canvas for drawing
    public void init(){
        canvas = new HangmanCanvas();
        add(canvas);
    }
    
    //houses gameplay loop and gameplay logic
    public void run(){
        initGame();
        canvas.removeAll();
        canvas.reset();
        canvas.displayWord(currWord);
        Scanner scan = new Scanner(System.in);
        
        //gameplay loop
        while(isRunning){
            guess = scan.nextLine();
            
            //Prompts player to guess again if they have made an illegal guess
            if(guess.length() > 1){
                System.out.println("Your guess is illegal. Please enter only 1 upper or lower case letter.");
                continue;          
            }
            
            //Prompts player to guess something else if they have guessed the same correct letter again
            if(currWord.indexOf(guess.toUpperCase()) != -1){
                System.out.println("You have already correctly guessed the letter " + guess + "." + " Guess something else.");
                continue;
                
            }
            else if(isCorrectGuess()){
                System.out.println("Your guess: " + guess);
                System.out.println("That guess is correct.");
                updateCurrWord();
                canvas.displayWord(currWord);
                
                if(hasWon()){
                    System.out.println("You guessed the word: " + secret);
                    System.out.println("You win.");
                    break;
                }
            }
            else{
                System.out.println("Your guess: " + guess);
                --numGuesses;
                incorrectGuesses += guess;
                canvas.noteIncorrectGuess(incorrectGuesses);
                System.out.println("There are no " + guess + "'s in the word.");
                
                if(hasLost()){
                    System.out.println("You're completely hung.");
                    System.out.println("The word was: " + secret);
                    System.out.println("You lose.");
                    break;
                }                    
            }
            
            System.out.println("The word now looks like this: " + currWord); 
            
            /*makes it so that the prompt for remaining number of guesses is gramatically
              correct if only 1 guess is remaining*/
            if(numGuesses != 1) 
                System.out.println("You have " + numGuesses + " guesses left.");
            else
                System.out.println("You have " + numGuesses + " guess left.");    
        }
    }
    
    //determines whether or not the player has won the game
    private static boolean hasWon(){
        boolean won = false;
        if(currWord.indexOf("-") == -1)
        won = true;
        
        return won;
    }
    
    //determines whether or not the player has lost the game
    private static boolean hasLost(){
        boolean lost = false;
        if(currWord.indexOf("-") != -1 && numGuesses == 0){
            lost = true;
        }
        
        return lost;
    }
    
    //updates the secret word to show letters the player has guessed correctly
    private static void updateCurrWord(){
        char[] ch;
        char[] cmp;
        String tmp;
        
        for(int i = 0; i < currWord.length(); ++i){
            tmp = Character.toString(secret.charAt(i));
            
            //checks the player's guess against each character in the secret word
            if(tmp.equalsIgnoreCase(guess)){
                ch = currWord.toCharArray();
                cmp = guess.toCharArray();
                
                //replace hyphen in currWord with correctly guessed letter
                ch[i] = cmp[0];
                currWord = String.valueOf(ch).toUpperCase();
            }   
        } 
    }
    
    //determines whether or not the player's guess is correct
    private static boolean isCorrectGuess(){
        boolean isCorrect = false;
        String tmp;
        
        //search secret word for instances of the player's guessed character
        for(int i = 0; i < secret.length(); ++i){
            tmp = Character.toString(secret.charAt(i));
            if(guess.equalsIgnoreCase(tmp)){
                isCorrect = true;
            } 
        }
        
        return isCorrect;
    } 
    
    /*gets a random word from the list of secret words and initializes currWord
      to be all hyphens*/
    public static void initGame(){
        hl = new HangmanLexicon();
        int randomIndex = ThreadLocalRandom.current().nextInt(0,hl.getWordCount());
        secret = hl.getWord(randomIndex);
        System.out.print("The word now looks like this: ");
        for(int i = 0; i < secret.length();++i){
            currWord += "-";
        }
        System.out.println(currWord);
        System.out.println("You have " + numGuesses + " guesses left.");
    }
    
    public static void main(String[] args){
        System.out.println("Welcome to Hangman!");
        new Hangman().start(args); 
    }
}

