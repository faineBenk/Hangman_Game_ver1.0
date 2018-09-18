package com.fbenk.hangman;

import javax.swing.*;
import java.awt.*;

import static com.fbenk.hangman.WordState.showResult;

public class HiddenWordViewer extends JComponent {

    private HangmanImageViewer hangmanImageViewer = new HangmanImageViewer();

    /**
     * Method overrides super method from class Window and
     * shows player his guesses on right panel.
     * @param g draws string of underscores.
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (GameFrame.getIsGameStarted()) {
            g.drawString(showResult(), 20, 175);
            hangmanImageViewer.drawImage(g);
        }
    }

}
