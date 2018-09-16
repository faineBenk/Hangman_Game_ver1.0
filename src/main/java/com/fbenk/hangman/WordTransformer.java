package com.fbenk.hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Class WordTransformer interacts with text file
 * to send randomly chosen word to the main class GameLauncher
 * when player presses a button.
 */

class WordTransformer {

    private static char[] randomWord;
    private static char[] rightGuesses;

    public static char[] getRandomWord() {
        return randomWord;
    }

    public static char[] getRightGuesses() {
        return rightGuesses;
    }

    /**
     * @return random word from text file.
     */

    public String getWordFromTextFile() {
        String path = new File("src\\main\\resources\\com\\fbenk\\hangman").getAbsolutePath();
        LinkedList<String> list = new LinkedList<String>();
        String str;
        BufferedReader in = null;
        try {
            FileReader wordList = new FileReader(path + "\\words_eng.txt");
            in = new BufferedReader(wordList);

            while((str = in.readLine())!= null) {
                list.add(str);
            }
        }
        catch (IOException exc) { exc.printStackTrace(); }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(-1);
            }
        }
        String[] arrayOfWords = list.toArray(new String[0]);
        int a = 0;
        int b = 1000;
        int random_number = a + (int) (Math.random() * b);
        return arrayOfWords[random_number];
    }

    /**
     * Method converts random word, taken from the text file,
     * to underscores, in aim to hide letters from player.
     */

    // change '_' to letter if user guessed any letter
    public void convertStringToChar() {
        String takenRandomWord = getWordFromTextFile();
        randomWord = takenRandomWord.toCharArray();
        rightGuesses = new char[randomWord.length];
        for (int i = 0; i < rightGuesses.length; i++) {
            rightGuesses[i] = '_';
        }
    }
}
