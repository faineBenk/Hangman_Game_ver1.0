package com.fbenk.hangman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.fbenk.hangman.GameFrame.getWrongGuesses;


/**
 * Class HangmanImageViewer sets pictures of gallow and hanged man
 * if word from text file doesn`t coincide with player`s guessed letter.
 */

class HangmanImageViewer extends JFrame {

        private BufferedImage img;

        private Image drawOneImage(String path) {
            try {
                img  = ImageIO.read(new File(path));
            }
            catch (IOException ioe) { System.exit(0);}

            return img;
        }


    /**
     * Method sets pictures on left panel.
     * @param g holds change of pictures.
     */

    public void drawImage(Graphics g) {

        repaint();

        if (getWrongGuesses() >= 1) {
            img = (BufferedImage) drawOneImage(HangmanImageViewer.class.getResource("h0.png").getPath());
            g.drawImage(img, 0, 0, GameFrame.getBelowPanel());

        }

        if (getWrongGuesses() >= 2) {
            img = (BufferedImage) drawOneImage(HangmanImageViewer.class.getResource("h1.png").getPath());
            g.drawImage(img,0, 0, setBelowPanel());
        }

        if (getWrongGuesses() >= 3) {
            img = (BufferedImage) drawOneImage(HangmanImageViewer.class.getResource("h2.png").getPath());
            g.drawImage(img,0, 0, setBelowPanel());
        }

        if (getWrongGuesses() >= 4) {
            img = (BufferedImage) drawOneImage(HangmanImageViewer.class.getResource("h3.png").getPath());
            g.drawImage(img,0, 0, setBelowPanel());
        }

        if (getWrongGuesses() >= 5) {
            img = (BufferedImage) drawOneImage(HangmanImageViewer.class.getResource("h4.png").getPath());
            g.drawImage(img,0, 0, setBelowPanel());
        }

        if (getWrongGuesses() >= 6) {
            img = (BufferedImage) drawOneImage(HangmanImageViewer.class.getResource("h5.png").getPath());
            g.drawImage(img,0, 0, setBelowPanel());
        }

        if (getWrongGuesses() >= 7) {
            img = (BufferedImage) drawOneImage(HangmanImageViewer.class.getResource("h6.png").getPath());
            g.drawImage(img,0, 0, setBelowPanel());
            JOptionPane.showMessageDialog(null, "You lose!"+
                    "\nTo play again choose NEW GAME button." +
                    "\nTo quit choose EXIT button.");
            setBottomPanel().setVisible(false);
            setBelowPanel().setVisible(true);
        }
        repaint();
    }

    private Component setBottomPanel() {
        return GameFrame.getBottomPanel();
    }

    private Component setBelowPanel() {
        return GameFrame.getBelowPanel();
    }


}
