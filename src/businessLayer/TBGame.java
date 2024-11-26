package businessLayer;

import java.util.Deque;
import java.util.Scanner;

import dataLayer.Character;
import dataLayer.Hunter;
import dataLayer.Opponent;
import dataLayer.Turn;
import dataLayer.Weapon;

public class TBGame {

    private Deque<Turn> turnOrder;
    private static Scanner keyboard;

    public TBGame() {
        keyboard = new Scanner(System.in);
    }

    public class Initializer {

        public void initializeGame() {
            TBGameStart tbGameStart = new TBGameStart(keyboard);
            turnOrder = tbGameStart.createTurnOrder();

            System.out.println("***Turn order: ");
            for (Turn player : turnOrder) {
                System.out.println(player.toString());
            }
            System.out.println("***************************************");
        }

    }

    public class Menu {

        @SuppressWarnings("unchecked")
        public void startBattle() {
            boolean opponentWinsCondition = false;
            boolean humansWinsCondition = false;

            while (!opponentWinsCondition || !humansWinsCondition) {     // check win situations
                Turn turn = turnOrder.peek();
                if (turn instanceof Character) {
                    boolean characterRunned = false;
                    CharacterTurn characterTurn = new CharacterTurn(turnOrder);
                    if ((turn instanceof Hunter) && ((Hunter<Weapon>) turn).checkSpecialUsedLastTurn()) {
                        ((Hunter<Weapon>) turn).setSpecialUsedLastTurn(false);
                        characterRunned = characterTurn.displayOptionsTwoTurn((Character<Weapon>) turnOrder.peek(),
                                keyboard);
                    } else {
                        characterRunned = characterTurn.displayOptions((Character<Weapon>) turnOrder.peek(),
                                keyboard);
                    }
                    if (characterRunned) {
                        System.out.println(turn.getIdOrName() + " runned. Battle ended");
                        break;
                    }
                    turnOrder = characterTurn.removeDiedOpponents();
                    turnOrder.addLast(turnOrder.poll());
                } else {
                    OpponentTurn opponentTurn = new OpponentTurn(turnOrder);
                    opponentTurn.doRandomAction();
                    turnOrder = opponentTurn.updateTurnOrder();
                    turnOrder.addLast(turnOrder.poll());
                }

                checkIfHumansWin(humansWinsCondition);
                checkIfOpponentsWin(opponentWinsCondition);

            }

        }

    }

    private void checkIfHumansWin(boolean humansWinsCondition) {
        for (Turn player : turnOrder) {
            if (player instanceof Opponent) {
                humansWinsCondition = false;
                break;
            } else {
                humansWinsCondition = true;
            }
        }
        if (humansWinsCondition) {
            System.out.println("The battle ended. Opponents won!");
        }
    }

    private void checkIfOpponentsWin(boolean opponentWinsCondition) {
        for (Turn player : turnOrder) {
            if (player instanceof Opponent) {
                opponentWinsCondition = false;
                break;
            } else {
                opponentWinsCondition = true;
            }
        }
        if (opponentWinsCondition) {
            System.out.println("The battle ended. Opponents won!");
        }
    }

}
