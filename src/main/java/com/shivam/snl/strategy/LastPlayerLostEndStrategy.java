package com.shivam.snl.strategy;

import com.shivam.snl.Player;
import java.util.Queue;

public class LastPlayerLostEndStrategy implements GameEndingStrategy{

    private Queue<Player> playerQueue;

    public LastPlayerLostEndStrategy(Queue<Player> playerQueue) {
        this.playerQueue = playerQueue;
    }

    @Override
    public boolean isGameEnded() {
        return playerQueue.size()<2;
    }
}
