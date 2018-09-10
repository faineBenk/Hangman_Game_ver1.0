package com.fbenk.hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GameFrame extends JFrame implements ActionListener {

    private static final String START_GAME = "CHOOSE FILE";
    private static final String EXIT_GAME = "EXIT";
    private static final String NEW_GAME = "NEW GAME";
    private static JPanel mainPanel, rightPanel, leftPanel, bottomPanel, belowPanel;
    private static int stateOfGame = 0;
    private static int wrongGuesses = 0;


    public static Component getBottomPanel() {
        return bottomPanel;
    }

    public static Component getBelowPanel() {
        return belowPanel;
    }

    public static int getStateOfGame() {
        return stateOfGame;
    }

    public static int getWrongGuesses(){
        return wrongGuesses;
    }

    private WordTransformer wordTransformer = new WordTransformer();

    /**
     * Constructor opens the main frame, where panels were set,
     * adds buttons to the bottom and menu below.
     */
    public GameFrame(WordTransformer wtf) {
        super("HANGMAN");
        setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        rightPanel = new JPanel();
        leftPanel = new JPanel();
        bottomPanel = new JPanel();
        belowPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 0));
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        bottomPanel.setLayout(new GridLayout(4, 4));
        add(mainPanel);
        mainPanel.add(bottomPanel);
        add(belowPanel, BorderLayout.SOUTH);
        belowPanel.setVisible(true);
        createReplayButtons(belowPanel);
        createButtons(bottomPanel);
        createMenu();
        mainPanel.add(new HiddenWordViewer(wtf));
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
     * Method overrides super method from interface ActionListener and
     * processes player`s actions.
     * @param e holds player`s press.
     */

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        wordTransformer.getWordFromTextFile();
        if (cmd.equals(START_GAME)) {
            stateOfGame = 1;
            bottomPanel.setVisible(true);
            wordTransformer.convertStringToChar();
            repaint();
        }
        else if (cmd.length() == 1 && stateOfGame == 1) {
            if (wordTransformer.getWordFromTextFile().contains(cmd.toLowerCase())) {
                for (int i = 0; i < WordTransformer.getRandomWord().length; i++) {
                    if (cmd.toLowerCase().charAt(0) ==WordTransformer.getRandomWord()[i]) {
                        WordTransformer.getRightGuesses()[i] = cmd.toLowerCase().charAt(0);
                    }
                    if (Arrays.equals(WordTransformer.getRightGuesses(), WordTransformer.getRandomWord())
                            && wrongGuesses < 7) {
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



