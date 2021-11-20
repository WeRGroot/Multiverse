package com.shivam.snl;

public class GameUtils {

    public static int getARandomNumberInTheRange(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + 1);
    }
}
