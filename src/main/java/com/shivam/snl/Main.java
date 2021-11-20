package com.shivam.snl;

import com.shivam.snl.dice.NSixFacedDice;
import com.shivam.snl.strategy.GameEndStrategy;
import com.shivam.snl.strategy.GameEndingStrategy;
import com.shivam.snl.strategy.LastPlayerLostEndStrategy;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of board");
        int sizeOfBoard = sc.nextInt();

        System.out.println("Enter the number of ladders");
        int numberOfLadders = sc.nextInt();

        System.out.println("Enter the number of snakes");
        int numberOfSnakes = sc.nextInt();

        System.out.println("Enter the number of players");
        int numberOfPlayers = sc.nextInt();
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Enter the name of player "+(i+1));
            String name = sc.next();
            players[i] = new Player(name);
        }

        sc.close();

        Game game = new Game(numberOfLadders, numberOfSnakes, players,
            new NSixFacedDice(1), sizeOfBoard, GameEndStrategy.FIRST_PLAYER_WIN);

        game.start();
    }
}
