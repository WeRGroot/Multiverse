package com.shivam.snl.strategy;

import com.shivam.snl.Player;
import java.util.Queue;

public class FirstPlayerWinStrategy implements GameEndingStrategy{
    private int initialSize;
    private Queue<Player> playerQueue;

    public FirstPlayerWinStrategy(Queue<Player> playerQueue) {
        this.playerQueue = playerQueue;
        this.initialSize = playerQueue.size();
    }

    @Override
    public boolean isGameEnded() {
        return playerQueue.size()!=initialSize;
    }
}
