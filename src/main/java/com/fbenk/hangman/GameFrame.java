package com.fbenk.hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static com.fbenk.hangman.GameFrame.ButtonValues.EXIT_GAME;

public class GameFrame extends JFrame implements ActionListener {

    private static JPanel belowPanel, bottomPanel;
    private static boolean stateOfGame = false;
    private static int wrongGuesses = 0;

    public enum ButtonValues {

        START_GAME {
            public String toString() {
                return "CHOOSE FILE";
            }
        },

        EXIT_GAME {
            public String toString() {
                return "EXIT";
            }
        },

        NEW_GAME {
            public String toString() {
                return "NEW GAME";
            }
        }
    }

    public static Component getBottomPanel() { return bottomPanel; }

    public static Component getBelowPanel() { return belowPanel; }

    public static boolean getStateOfGame() { return stateOfGame; }

    public static int getWrongGuesses(){ return wrongGuesses; }

    private WordTransformer wordTransformer = new WordTransformer();

    /**
     * Constructor opens the main frame, where panels were set,
     * adds buttons to the bottom and menu below.
     */
    public GameFrame(WordTransformer wtf) {
        super("HANGMAN");
        setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();
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
        JButton playAgain = new JButton(String.valueOf(ButtonValues.NEW_GAME));
        playAgain.setSize(100, 100);
        playAgain.setActionCommand(String.valueOf(ButtonValues.NEW_GAME));
        playAgain.addActionListener(this);
        JButton exit = new JButton(String.valueOf(EXIT_GAME));
        exit.setActionCommand(String.valueOf(EXIT_GAME));
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
        addItemToMenu(gameMenu,String.valueOf(ButtonValues.START_GAME));
        addItemToMenu(gameMenu,String.valueOf(EXIT_GAME));
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
        if (cmd.equals(String.valueOf(ButtonValues.START_GAME))) {
            stateOfGame = true;
            bottomPanel.setVisible(true);
            wordTransformer.convertStringToChar();
            repaint();
        }
        else if (cmd.length() == 1 && stateOfGame) {
            if (wordTransformer.getWordFromTextFile().contains(cmd.toLowerCase())) {
                for (int i = 0; i < WordTransformer.getRandomWord().length; i++) {
                    if (cmd.toLowerCase().charAt(0) == WordTransformer.getRandomWord()[i]) {
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
        else if (cmd.equals(String.valueOf(ButtonValues.NEW_GAME)) && stateOfGame) {
            wrongGuesses = 0;
            bottomPanel.setVisible(true);
            wordTransformer.convertStringToChar();
            repaint();

        } else if (cmd.equals(String.valueOf(ButtonValues.EXIT_GAME))) {
            stateOfGame = false;
            System.exit(0);
        }
    }
}



