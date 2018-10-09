package com.toksaitov.dice;

import java.util.Random;

public class Dice {

    private Random random = new Random();

    private int firstDieIndex;
    private int secondDieIndex;

    Dice() {
        roll();
    }

    int getFirstDieIndex() {
        return firstDieIndex;
    }

    int getSecondDieIndex() {
        return secondDieIndex;
    }

    public void setFirstDieIndex(int firstDieIndex) {
        this.firstDieIndex = firstDieIndex;
    }

    public void setSecondDieIndex(int secondDieIndex) {
        this.secondDieIndex = secondDieIndex;
    }

    final void roll() {
        firstDieIndex = random.nextInt(6);
        secondDieIndex = random.nextInt(6);
    }

}
