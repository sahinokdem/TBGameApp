package businessLayer;

import java.util.ArrayList;

import dataLayer.Enums.OpponentType;
import dataLayer.Goblin;
import dataLayer.Orc;
import dataLayer.Turn;
import dataLayer.Slime;
import dataLayer.Wolf;

public class OpponentCreator {

    private ArrayList<Turn> opponents;

    public OpponentCreator() {
        opponents = createOpponents();
    }

    // Checked

    private ArrayList<Turn> createOpponents() {
        int numberOfOpponents = (int) (Math.random() * 3) + 1;
        ArrayList<Turn> opponents = new ArrayList<Turn>(numberOfOpponents);
        for (int i = 0; i < numberOfOpponents; i++) {
            opponents.add(getRandomOpponent(Integer.toString(i + 1)));
        }
        return opponents;
    }

    // Checked

    private Turn getRandomOpponent(String opponentId) {
        int randomIndex = (int) (Math.random() * 4);
        OpponentType randomOpponentType = OpponentType.values()[randomIndex];
        switch (randomOpponentType) {
            case Slime:
                return new Slime(opponentId);
            case Goblin:
                return new Goblin(opponentId);
            case Orc:
                return new Orc(opponentId);
            case Wolf:
                return new Wolf(opponentId);
            default:
                throw new IllegalArgumentException("No Opponent type at index: " + randomIndex);
        }
    }

    // Checked

    public ArrayList<Turn> getOpponents() {
        ArrayList<Turn> returnArray = new ArrayList<>();
        for (Turn player : opponents) {
            try {
                returnArray.add(player.clone());
            } catch (CloneNotSupportedException e) {
                System.out.println("player" + player.toString() + "is not clonable.");
                e.printStackTrace();
            }
        }
        return returnArray;
    }

}
