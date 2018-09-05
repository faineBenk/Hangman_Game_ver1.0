package com.fbenk.hangman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.fbenk.hangman.GameLauncher.belowPanel;
import static com.fbenk.hangman.GameLauncher.bottomPanel;
import static com.fbenk.hangman.GameLauncher.wrongGuesses;


/**
 * Class HangmanImageViewer sets pictures of gallow and hanged man
 * if word from text file doesn`t coincide with player`s guessed letter.
 */

class HangmanImageViewer extends JFrame  {

    private BufferedImage img, img1, img2, img3, img4, img5, img6;
    private JPanel jp;
    private JLabel j1;

    /**
     * Method sets pictures on left panel.
     * @param g holds change of pictures.
     */

    public void drawImage(Graphics g) {

        j1 = new JLabel();
        jp = new JPanel();

        if (wrongGuesses >= 1) {
            try {
                img = ImageIO.read(new File("src\\resources\\main\\h0.png"));
            }
            catch (IOException ioe) { System.exit(0); }

            j1 = new JLabel(new ImageIcon(img));
            jp.setLayout(new BorderLayout());
            jp.add(j1, BorderLayout.WEST);
            g.drawImage(img, 10, 100, jp);


            if (wrongGuesses >= 2) {
                try {
                    img1 = ImageIO.read(new File("src\\resources\\main\\h1.png"));
                    g.drawImage(img1, 10, 100, jp) ;
                }
                catch (IOException ioe) { System.exit(0);}
            }

            if (wrongGuesses >= 3) {
                try {
                    img2 = ImageIO.read(new File("src\\resources\\main\\h2.png"));
                    g.drawImage(img2, 10, 100, jp) ;
                }
                catch (IOException ioe) { System.exit(0);}
            }

            if (wrongGuesses >= 4) {
                try {
                    img3 = ImageIO.read(new File("src\\resources\\main\\h3.png"));
                    g.drawImage(img3, 10, 100, jp) ;
                }
                catch (IOException ioe) { System.exit(0);}
            }

            if (wrongGuesses >= 5) {
                try {
                    img4 = ImageIO.read(new File("src\\resources\\main\\h4.png"));
                    g.drawImage(img4, 10, 100, jp) ;
                }
                catch (IOException ioe) {System.exit(0);}
            }

            if (wrongGuesses >= 6) {
                try {
                    img5 = ImageIO.read(new File("src\\resources\\main\\h5.png"));
                    g.drawImage(img5, 10, 100, jp) ;
                }
                catch (IOException ioe) {System.exit(0);}
            }

            if (wrongGuesses >= 7) {
                try {
                    img6 = ImageIO.read(new File("src\\resources\\main\\h6.png"));
                    g.drawImage(img5, 10, 100, jp);
                    JOptionPane.showMessageDialog(null, "You lose!"+
                            "\nTo play again choose NEW GAME button." +
                            "\nTo quit choose EXIT button.");
                    bottomPanel.setVisible(false);
                    belowPanel.setVisible(true);
                }
                catch (IOException ioe) { ioe.printStackTrace(); }
            }
        }
    }
}