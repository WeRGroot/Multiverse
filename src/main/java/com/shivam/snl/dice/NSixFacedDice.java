package com.shivam.snl.dice;

import com.shivam.snl.GameUtils;

public class NSixFacedDice implements Dice {

    int numberOfDice;
    int min;
    int max;

    public NSixFacedDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
        min = 1;
        max = 7; // max value is exclusive
    }

    @Override
    public int roll() {
        return GameUtils.getARandomNumberInTheRange(min, max) * numberOfDice;
    }
}
