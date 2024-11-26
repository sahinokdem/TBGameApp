package businessLayer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Scanner;

import dataLayer.Turn;
import exceptionsLayer.NotAUniqueNameException;

public class TBGameStart {
    ArrayList<Turn> opponents;
    ArrayList<Turn> characters;

    public TBGameStart(Scanner keyboard) {

        startingGame(keyboard);
        System.out.println("The battle starts!");
    }

    private void startingGame(Scanner keyboard) {
        OpponentCreator opponentCreator = new OpponentCreator();
        opponents = opponentCreator.getOpponents();

        System.out.println("Welcome to TBGame");
        System.out.println("These opponents appeared in front of you:");

        for (Turn opponentPlayer : opponents) {
            System.out.println(opponentPlayer.toString());
        }

        System.out.println("Please enter the number of characters to create: ");
        int numberOfCharacters = keyboard.nextInt();
        while (numberOfCharacters > 3) {
            System.out.println("Max valid character number is 3, Please reenter:");
            numberOfCharacters = keyboard.nextInt();
        }
        keyboard.nextLine();
        String[] nameOfCharacters = new String[numberOfCharacters];
        for (int i = 0; i < numberOfCharacters; i++) {
            boolean uniqueName = false;
            while (!uniqueName) {
                try {
                    System.out.println("Please name your " + (i + 1) + ". character");
                    String newName = keyboard.nextLine();
                    for (int j = 0; j < i; j++) {
                        if (nameOfCharacters[j].equals(newName)) {
                            throw new NotAUniqueNameException("Characters must have unique names!");
                        }
                    }

                    nameOfCharacters[i] = newName;
                    uniqueName = true;
                } catch (NotAUniqueNameException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        CharacterCreator characterCreator = new CharacterCreator(numberOfCharacters, nameOfCharacters);
        characters = characterCreator.getCharacters();
        for (int i = 0; i < numberOfCharacters; i++) {
            System.out.println("The stats of " + (i + 1) + ". Character: ");
            System.out.println(characters.get(i).toString());
        }

    }
    // keep track deque
    public Deque<Turn> createTurnOrder() {
        PriorityQueue<Turn> setOrderQueue = new PriorityQueue<Turn>(Comparator.reverseOrder());
        setOrderQueue.addAll(opponents);
        setOrderQueue.addAll(characters);
        Deque<Turn> turnOrder = new ArrayDeque<Turn>();

        while (!setOrderQueue.isEmpty()) {
            turnOrder.add(setOrderQueue.poll());
        }
        return turnOrder;
    }
}
