package com.fbenk.hangman;

import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.EventListener;


import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static com.fbenk.hangman.WordTransformer.randomWord;


/**
 * Class GameLauncher starts the game, when player interacts with
 * graphic elements.
 */

public class GameLauncher extends JFrame implements ActionListener {

    private static final String START_GAME = "CHOOSE FILE";
    private static final String EXIT_GAME = "EXIT";
    private static final String NEW_GAME = "NEW GAME";
    private static JPanel mainPanel, rightPanel, leftPanel;
    private int stateOfGame = 0;
    static JPanel bottomPanel;
    static JPanel belowPanel;
    static int wrongGuesses = 0;
    static char[] rightGuesses;
    WordTransformer wordTransformer = new WordTransformer();
    HangmanImageViewer hangmanImageViewer = new HangmanImageViewer();

    public static void main(String[] args) {
        GameLauncher newGame = new GameLauncher();
        newGame.setVisible(true);
        newGame.setResizable(false);
    }

    /**
     * Constructor opens the main frame, where panels were set,
     * adds buttons to the bottom and menu below.
     */
    public GameLauncher() {
        super("HANGMAN");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 0));
        rightPanel = new JPanel();
        leftPanel = new JPanel();
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4, 4));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        belowPanel = new JPanel();
        add(mainPanel);
        add(belowPanel, BorderLayout.AFTER_LAST_LINE);
        belowPanel.setVisible(true);
        createMenu();
        createButtons(bottomPanel);
        createReplayButtons(belowPanel);
    }

    /**
     * Method makes NEW GAME and EXIT buttons active,
     * when player loses the game at 1st time.
     * @param belowPanel
     */

    private void createReplayButtons (JPanel belowPanel) {
        JButton playAgain = new JButton(NEW_GAME);
        playAgain.setSize(100, 100);
        playAgain.setActionCommand(NEW_GAME);
        playAgain.addActionListener(this);
        JButton exit = new JButton(EXIT_GAME);
        exit.setActionCommand(EXIT_GAME);
        exit.addActionListener(this);
        exit.setSize(100, 100);
        belowPanel.add(playAgain);
        belowPanel.add(exit);
    }

    private void createButtons(JPanel bottomPanel) {
        JButton[] buttons = new JButton[26];
        String[] alph = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(alph[i]);
            buttons[i].setSize(40, 40);
            buttons[i].setActionCommand(alph[i]);
            buttons[i].addActionListener(this);
            bottomPanel.add(buttons[i]);
        }
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("PLAY");
        menuBar.add(gameMenu);
        addItemToMenu(gameMenu,START_GAME);
        addItemToMenu(gameMenu,EXIT_GAME);
    }

    private void addItemToMenu(JMenu menu, String itemName) {
        JMenuItem menuItem = new JMenuItem(itemName);
        menuItem.addActionListener(this);
        menu.add(menuItem);
    }

    /**
     * Method overrides super method from class Window and
     * shows player his guesses on right panel.
     * @param g draws string of underscores.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (stateOfGame == 1) {
            String result = "";
            for (int i = 0; i < rightGuesses.length; i++) {
                result += rightGuesses[i] + " ";
            }
            g.drawString(result, 400, 175);
            hangmanImageViewer.drawImage(g);
        }
    }


    /**
     * Method overrides super method from interface ActionListener and
     * processes player`s actions.
     * @param e holds player`s press.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        wordTransformer.getWordFromTextFile();
        if (cmd.equals(START_GAME)) {
            stateOfGame = 1;
            wordTransformer.convertStringToChar();
            repaint();
        }
        else if (cmd.length() == 1 && stateOfGame == 1) {
            if (wordTransformer.getWordFromTextFile().contains(cmd.toLowerCase())) {
                for (int i = 0; i < randomWord.length; i++) {
                    if (cmd.toLowerCase().charAt(0) == randomWord[i]) {
                        rightGuesses[i] = cmd.toLowerCase().charAt(0);
                    }
                    if (Arrays.equals(rightGuesses, randomWord) && wrongGuesses < 7) {
                        bottomPanel.setVisible(false);
                        belowPanel.setVisible(true);
                        // increase of parts of the body if letter`s wrong
                        JOptionPane.showMessageDialog(null,
                                "You won! " + "\nTo play again choose NEW GAME button.");
                    }
                }
            }
        else if (!wordTransformer.getWordFromTextFile().contains(cmd.toLowerCase())) {
                JOptionPane.showMessageDialog(null, "Sorry, " + cmd
                 + " is not part of the word.");
                wrongGuesses++;
        }
            repaint();
        }

        //replay
        else if (cmd.equals(NEW_GAME) && stateOfGame == 1) {

            wrongGuesses = 0;
            bottomPanel.setVisible(true);
            wordTransformer.convertStringToChar();
            repaint();

        } else if (cmd.equals(EXIT_GAME)) {
            stateOfGame = 2;
            System.exit(0);
        }
    }
}