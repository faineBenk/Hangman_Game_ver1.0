package com.fbenk.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import static com.fbenk.hangman.GameLauncher.rightGuesses;

/**
 * Class WordTransformer interacts with text file
 * to send randomly chosen word to the main class GameLauncher
 * when player presses a button.
 */

class WordTransformer {

    private String[] arrayOfWords;
    private  String takenRandomWord;
    private int a = 0;
    private int b = 1000;
    static char[] randomWord;

    /**
     * @return random word from text file.
     */

    public String getWordFromTextFile() {
        LinkedList<String> list = new LinkedList<String>();
        String str;
        BufferedReader in = null;
        try {
            FileReader wordList = new FileReader(
                    "resources\\main\\words_eng.txt");
            in = new BufferedReader(wordList);

            while((str = in.readLine())!= null) {
                list.add(str);
            }
        }
        catch (IOException exc) { exc.printStackTrace(); }
        finally {
            try { in.close(); }
            catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(-1);
            }
        }
        arrayOfWords = list.toArray(new String[list.size()]);
        int random_number = a + (int) (Math.random() * b);
        String word = arrayOfWords[random_number];
        return word;
    }

    /**
     * Method converts random word, taken from the text file,
     * to underscores, in aim to hide letters from player.
     */

    // change '_' to letter if user guessed any letter
    public void convertStringToChar() {
        takenRandomWord = getWordFromTextFile();
        randomWord = takenRandomWord.toCharArray();
        rightGuesses = new char[randomWord.length];
        for (int i = 0; i < rightGuesses.length; i++) {
            rightGuesses[i] = '_';
        }
    }

}
