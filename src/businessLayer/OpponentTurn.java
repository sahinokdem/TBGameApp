package businessLayer;

import java.util.Deque;
import java.util.Random;

import dataLayer.Turn;
import dataLayer.Slime;
import dataLayer.Goblin;
import dataLayer.Opponent;
import dataLayer.Orc;
import dataLayer.Wolf;

public class OpponentTurn {
    Deque<Turn> turnOrder;
    Turn[] playerArray;
    Opponent opponent;

    public OpponentTurn(Deque<Turn> turnOrder) {
        this.turnOrder = turnOrder;
        createPlayerArray();
        opponent = (Opponent) turnOrder.peek();
        System.out.println("It is the turn of " + opponent.toString());
    }

    private void createPlayerArray() {
        this.playerArray = new Turn[turnOrder.size()];
        Object[] playerArray = turnOrder.toArray();
        for (int i = 0; i < playerArray.length; i++) {
            this.playerArray[i] = (Turn) playerArray[i];
        }
    }

    public void doRandomAction() {
        if (opponent instanceof Orc && ((Orc) opponent).checkIfMadeHeavyHit()) {
            ((Orc) opponent).skipTurn();
        } else {
            double randomAction = Math.random() * 3;
            if (randomAction < 1) {
                opponent.attack(attackRandomCharacter());
            } else if (randomAction < 2) {
                opponent.guard();
            } else {
                speacialAction(opponent);
            }
        }
    }

    private Turn attackRandomCharacter() {
        Random random = new Random();
        int randomIndex = random.nextInt(turnOrder.size());
        while (playerArray[randomIndex] instanceof Opponent) {
            randomIndex = random.nextInt(turnOrder.size());
        }
        return playerArray[randomIndex];
    }

    private void speacialAction(Opponent opponent) {
        if (opponent instanceof Orc) {
            ((Orc) opponent).heavyHit(attackRandomCharacter());
        } else if (opponent instanceof Goblin) {
            ((Goblin) opponent).rushingAttack(attackRandomCharacter());
        } else if (opponent instanceof Slime) {
            ((Slime) opponent).absorb(attackRandomCharacter());
        } else {
            Wolf wolfFriend = ((Wolf) opponent).callFriend();
            wolfFriend.setIdOrName(String.valueOf(turnOrder.size() + 1));
            turnOrder.add(wolfFriend);
        }
    }

    public Deque<Turn> updateTurnOrder() {
        for (Turn player : turnOrder) {
            if (player.getPoints() <= 0) {
                turnOrder.remove(player);
            }
        }
        return turnOrder;
    }

}
