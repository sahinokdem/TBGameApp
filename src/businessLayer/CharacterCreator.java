package businessLayer;

import java.util.ArrayList;

import dataLayer.*;
import dataLayer.Enums.CharacterType;
import dataLayer.Enums.WeaponType;

public class CharacterCreator {

    private ArrayList<Turn> characters;

    public CharacterCreator(int numberOfCharacters, String[] characterNames) {
        characters = createCharacters(numberOfCharacters, characterNames);
    }
    
    /**
     * Creates a list of characters with random types and weapons
     * @param numberOfCharacters
     * @param characterNames
     * @return
     */

    private ArrayList<Turn> createCharacters(int numberOfCharacters, String[] characterNames) {
        characters = new ArrayList<Turn>(numberOfCharacters);
        for (int i = 0; i < numberOfCharacters; i++) {
            characters.add(getRandomCharacter(characterNames[i], getRandomWeapon()));
        }
        return characters;
    }
    /**
     * Generates a random character based on the provided name and weapon.
     * @param <W>
     * @param characterName
     * @param weapon
     * @return
     */

    private <W> Turn getRandomCharacter(String characterName, W weapon) {
        int randomIndex = (int) (Math.random() * 4);
        CharacterType randomType = CharacterType.values()[randomIndex];
        switch (randomType) {
            case Knight:
                return new Knight<W>(characterName, weapon);
            case Villager:
                return new Villager<W>(characterName, weapon);
            case Squire:
                return new Squire<W>(characterName, weapon);
            case Hunter:
                return new Hunter<W>(characterName, weapon);
            default:
                throw new IllegalArgumentException("No Character type at index: " + randomIndex);
        }
    }



    //Checked
    /**
     * Generates a random weapon.
     * @return
     */

    private Weapon getRandomWeapon() {
        int randomIndex = (int) (Math.random() * 3);
        WeaponType randomWeaponType = WeaponType.values()[randomIndex];
        switch (randomWeaponType) {
            case Sword:
                return new Sword();
            case Bow:
                return new Bow();
            case Spear:
                return new Spear();
            default:
                throw new IllegalArgumentException("No Character type at index: " + randomIndex);
        }
    }

    public ArrayList<Turn> getCharacters() {
        ArrayList<Turn> returnArray = new ArrayList<>();
        for (Turn player : characters) {
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
