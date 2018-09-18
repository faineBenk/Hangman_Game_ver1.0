package com.fbenk.hangman;

/**
 * Class GameLauncher starts the game, when player interacts with
 * graphic elements.
 */

public class GameLauncher {

    public static void main(String[] args) throws Exception {
        GameFrame newGame = new GameFrame();
        newGame.setVisible(true);
        WordState.loadClass();

    }

}
