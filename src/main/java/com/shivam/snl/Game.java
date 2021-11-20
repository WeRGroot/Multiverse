package com.shivam.snl;

import com.shivam.snl.dice.Dice;
import com.shivam.snl.strategy.FirstPlayerWinStrategy;
import com.shivam.snl.strategy.GameEndStrategy;
import com.shivam.snl.strategy.GameEndingStrategy;
import com.shivam.snl.strategy.LastPlayerLostEndStrategy;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Game {

    private int numberOfLadders;
    private int numberOfSnakes;

    private ArrayDeque<Player> playerQueue;
    private HashMap<Integer, Integer> snakesAndLadders;
    private Dice dice;
    private Board board;
    private GameEndingStrategy endingStrategy;

    public Game(int numberOfLadders, int numberOfSnakes, Player[] players,
        Dice dice, int size, GameEndStrategy endStrategy) {
        playerQueue = new ArrayDeque<>();

        this.numberOfLadders = numberOfLadders;
        this.numberOfSnakes = numberOfSnakes;
        this.dice = dice;
        this.board = new Board(size);
        this.snakesAndLadders = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            playerQueue.addLast(players[i]);
        }

        switch (endStrategy){
            case FIRST_PLAYER_WIN:
                this.endingStrategy = new FirstPlayerWinStrategy(playerQueue);
                break;

            default:
                this.endingStrategy = new LastPlayerLostEndStrategy(playerQueue);
        }



        initSnakesAndLadders();
    }

    public void start() {
        while (!endingStrategy.isGameEnded()) {
            Player player = playerQueue.removeFirst();
            int number = dice.roll();
            String log = "Dice roll: "+number;
            int nextPosition = player.getPosition() + number;
            log += " -->  " + player.getName() + " moved to " + nextPosition;
            if (nextPosition <= board.getEnd()) {
                if (snakesAndLadders.containsKey(nextPosition)) {
                    int newPosition = snakesAndLadders.get(nextPosition);
                    if (nextPosition < newPosition) {
                        log += " -->  " + player.getName() + " climbed the ladder";
                    } else {
                        log +=" -->  " + player.getName() + " bitten by the snake";
                    }
                    nextPosition = newPosition;
                    log += " -->  " + player.getName() + " moved to " + nextPosition;
                }
                player.setPosition(nextPosition);
            }
            System.out.println(log);
            if (player.getPosition() == board.getEnd()) {
                System.out.println(player.getName() + " won");
                player.setWon(true);
            }else{
                playerQueue.addLast(player);
            }
        }

        for (Player player : playerQueue) {
            System.out.println(player.getName() + " lost");
        }
    }

    private void initSnakesAndLadders() {
        for (int i = 0; i < numberOfSnakes; i++) {
            int[] snake = getMeASnake();
            snakesAndLadders.put(snake[0], snake[1]);
        }

        for (int i = 0; i < numberOfLadders; i++) {
            int[] ladder = getMeALadder();
            snakesAndLadders.put(ladder[0], ladder[1]);
        }
    }

    private int[] getMeALadder() {
        int start = board.getStart();
        int end = board.getEnd();
        do {
            start = GameUtils.getARandomNumberInTheRange(start + 1, end + 1);
        } while (snakesAndLadders.containsKey(start));

        end = GameUtils.getARandomNumberInTheRange(start + 1, end);

        int[] pair = new int[2];
        pair[0] = start;
        pair[1] = end;

        return pair;
    }

    private int[] getMeASnake() {
        int start = board.getStart();
        int end = board.getEnd();
        do {
            start = GameUtils.getARandomNumberInTheRange(start+1, end);
        } while (snakesAndLadders.containsKey(start));

        end = GameUtils.getARandomNumberInTheRange(1, start);

        int[] pair = new int[2];
        pair[0] = start;
        pair[1] = end;

        return pair;
    }

}
