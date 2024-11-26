package businessLayer;

import java.util.Deque;
import java.util.InputMismatchException;
import java.util.Scanner;

import dataLayer.*;
import dataLayer.Character;
import exceptionsLayer.InsufficientStaminaException;
import exceptionsLayer.SpearThrownException;
import exceptionsLayer.SpecialAlreadyUsedException;

public class CharacterTurn {

    Deque<Turn> turnOrder;
    Turn[] playerArray;

    @SuppressWarnings("unchecked")
    public CharacterTurn(Deque<Turn> turnOrder) {
        this.turnOrder = turnOrder;
        createPlayerArray();
        System.out.println("It is the turn of " + ((Character<Weapon>) turnOrder.peek()).getIdOrName());
    }
    // Create player array
    private void createPlayerArray() {
        this.playerArray = new Turn[turnOrder.size()];
        Object[] playerArray = turnOrder.toArray();
        for (int i = 0; i < playerArray.length; i++) {
            this.playerArray[i] = (Turn) playerArray[i];
        }
    }
    // Displays the menu of options 
    public boolean displayOptions(Character<Weapon> character, Scanner keyboard) {
        boolean validChoice = false;
        do {
            try {
                System.out.println("[1] Punch");
                System.out.println("[2] Attack with weapon");
                System.out.println("[3] Guard");
                System.out.println("[4] Special Action");
                System.out.println("[5] Run");
                System.out.print("Please select an option: ");
                int menuChoice = keyboard.nextInt();
                switch (menuChoice) {
                    case 1:
                        Turn selectedOpponent = selectOpponent(keyboard);
                        character.punch(selectedOpponent);
                        validChoice = true;
                        break;
                    case 2:
                        attackWithWeaponOption(character, keyboard);
                        validChoice = true;
                        break;
                    case 3:
                        character.guard();
                        validChoice = true;
                        break;
                    case 4:
                        specialActionOption(character, keyboard);
                        validChoice = true;
                        break;
                    case 5:
                        character.run();
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        System.out.println("Please enter a number corresponding to the options above.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number of any option above.");
                keyboard.next();
            } catch (InsufficientStaminaException e) {
                System.out.println(e.getMessage());
                System.out.println("You can select guard for increasing stamina");
                keyboard.next();
            } catch (UnsupportedOperationException e) {
                System.out.println("Villager has no special action, select another");
                keyboard.next();
            } catch (SpecialAlreadyUsedException e) {
                System.out.println("You already used special, please choose another");
                keyboard.next();
            } catch (SpearThrownException e) {
                System.out.println("You already thrown the spear, please choose another");
                keyboard.next();
            }
        } while (!validChoice);
        return character.checkRunned();
    }
    // Displays the menu of options for a character's turn with two turns
    public boolean displayOptionsTwoTurn(Character<Weapon> character, Scanner keyboard) {
        int turn = 1;
        boolean validChoice = false;
        do {
            try {
                System.out.println("[1] Punch");
                System.out.println("[2] Attack with weapon");
                System.out.println("[3] Guard");
                System.out.println("[4] Special Action");
                System.out.println("[5] Run");
                System.out.print("Please select an option: ");
                int menuChoice = keyboard.nextInt();
                switch (menuChoice) {
                    case 1:
                        Turn selectedOpponent = selectOpponent(keyboard);
                        character.punch(selectedOpponent);
                        validChoice = true;
                        break;
                    case 2:
                        attackWithWeaponOption(character, keyboard);
                        validChoice = true;
                        break;
                    case 3:
                        character.guard();
                        validChoice = true;
                        break;
                    case 4:
                        specialActionOption(character, keyboard);
                        validChoice = true;
                        break;
                    case 5:
                        character.run();
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        System.out.println("Please enter a number corresponding to the options above.");
                }
                turn++;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number of any option above.");
                keyboard.next();
            } catch (InsufficientStaminaException e) {
                System.out.println(e.getMessage());
                System.out.println("You can select guard for increasing stamina");
                keyboard.next();
            } catch (UnsupportedOperationException e) {
                System.out.println("Villager has no special action, select another");
                keyboard.next();
            } catch (SpecialAlreadyUsedException e) {
                System.out.println("You already used special, please choose another");
                keyboard.next();
            } catch (SpearThrownException e) {
                System.out.println("You already thrown the spear, please choose another");
                keyboard.next();
            }
        } while (!validChoice || turn <= 2);
        return character.checkRunned();
    }
    // Selects opponent for the character to interact 
    private Turn selectOpponent(Scanner keyboard) {
        keyboard.nextLine();
        System.out.println("Please enter an opponent id: ");
        Turn selectedOpponent = null;
        boolean opponentFound = false;
    
        while (!opponentFound) {
            selectedOpponent = findOpponent(keyboard);
            if (selectedOpponent != null) {
                opponentFound = true;
            }
        }
        return selectedOpponent;
    }
    // Find opponent based on the user input
    private Turn findOpponent(Scanner keyboard) {
        Turn selectedOpponent = null;
        boolean opponentFound = false;
    
        while (!opponentFound) {
            String opponentId = keyboard.nextLine();
            for (Turn player : playerArray) {
                if (player.getIdOrName().equals(opponentId) && player instanceof Opponent) {
                    selectedOpponent = player;
                    opponentFound = true;
                    break;
                }
            }
            if (!opponentFound) {
                System.out.println("Invalid opponent id. Please reenter: ");
            }
        }
        return selectedOpponent;
    }         

    private void specialActionOption(Character<Weapon> character, Scanner keyboard)
            throws UnsupportedOperationException, SpecialAlreadyUsedException {
        Turn selectedOpponent = selectOpponent(keyboard);
        character.specialAction(selectedOpponent);
    }

    private void attackWithWeaponOption(Character<Weapon> character, Scanner keyboard)
            throws InsufficientStaminaException, SpearThrownException {
        if (character.getW() instanceof Bow) {
            System.out.println("Please enter the action number ([1]Shoot with one arrow [2]Shoot with two arrow)");
        } else if (character.getW() instanceof Sword) {
            System.out.println("Please enter the action number ([1]Slash [2]Stab)");
        } else {
            System.out.println("Please enter the action number ([1]Stab [2]Throw)");
        }
        int menuChoice = keyboard.nextInt();
        Turn selectedOpponent = selectOpponent(keyboard);
        character.attackWithWeapon(selectedOpponent, menuChoice);
    }

    public Deque<Turn> removeDiedOpponents() {
        for (Turn opponent : turnOrder) {
            if (opponent.getPoints() <= 0) {
                turnOrder.remove(opponent);
            }
        }
        return turnOrder;
    }

}
