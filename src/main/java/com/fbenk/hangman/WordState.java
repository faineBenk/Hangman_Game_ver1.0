package com.fbenk.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Class WordTransformer interacts with text file
 * to send randomly chosen word to the main class GameLauncher
 * when player presses a button.
 */

class WordState {

    private static char[] randomWord;
    private static char[] rightGuesses;

    public static char[] getRandomWord() { return randomWord; }

    public static char[] getRightGuesses() { return rightGuesses; }

    /**
     * @return random word from text file.
     */


    public static String getWordFromTextFile() {
        int a = 0;
        int b = 1000;
        String fileName = WordState.class.getResource("words_eng.txt").getPath();
        LinkedList<String> list = new LinkedList<>();
        String str;
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {

            while((str =in.readLine())!=null) { list.add(str); }
            } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arrayOfWords = list.toArray(new String[0]);
        int random_number = a + (int) (Math.random() * b);
        return arrayOfWords[random_number];
    }

    /**
     * Method converts random word, taken from the text file,
     * to underscores, in aim to hide letters from player.
     */

    // change '_' to letter if user guessed any letter
    public static void convertStringToChar() {
        randomWord  = getWordFromTextFile().toCharArray();
        rightGuesses = new char[randomWord.length];
        for (int i = 0; i < rightGuesses.length; i++) {
            rightGuesses[i] = '_';
        }
    }

    public static String showResult() {
        String result = "";
        for (int i = 0; i < rightGuesses.length; i++) {
            result += rightGuesses[i] + " ";
        }
        return result;
    }
}
