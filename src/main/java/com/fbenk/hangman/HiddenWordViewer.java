package com.fbenk.hangman;

import javax.swing.*;
import java.awt.*;

public class HiddenWordViewer extends JComponent {

    private WordTransformer wtf;
    private HangmanImageViewer hangmanImageViewer = new HangmanImageViewer();

    public HiddenWordViewer(WordTransformer wtf) {
        this.wtf = wtf;
    }


    /**
     * Method overrides super method from class Window and
     * shows player his guesses on right panel.
     * @param g draws string of underscores.
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (GameFrame.getStateOfGame()) {
            String result = "";
            for (int i = 0; i < WordTransformer.getRightGuesses().length; i++) {
                result += WordTransformer.getRightGuesses()[i] + " ";
            }
            g.drawString(result, 20, 175);
            hangmanImageViewer.drawImage(g);
        }
    }

}
