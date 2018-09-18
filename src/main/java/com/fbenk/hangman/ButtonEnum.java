package com.fbenk.hangman;


public enum ButtonEnum {

    START_GAME("CHOOSE FILE"), EXIT_GAME("EXIT"), NEW_GAME("NEW GAME");

    private final String val;

    ButtonEnum(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}

