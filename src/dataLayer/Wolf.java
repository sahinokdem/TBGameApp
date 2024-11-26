package dataLayer;

import dataLayer.Enums.OpponentType;

public class Wolf extends AbstractOpponent {

    public Wolf(String opponentId) {
        super(opponentId, OpponentType.Wolf);
    }

    public Wolf(Wolf original) {
        super(original);
    }

    public Wolf callFriend() {
        System.out.println("Wolf called his friend, now there are two identical wolves.");
        return new Wolf(this);
    }

    @Override
    public Wolf clone() throws CloneNotSupportedException {
        return new Wolf(this);
    }

    @Override
    public String toString() {
        return String.format("Wolf: %s Points: %d, Attack Stat: %d Speed %d ", getIdOrName(), getPoints(), getAttack(),
                getSpeed());
    }
    
    /**
     *     TEST   (ALL METHODS ARE CHECKED)
     */
    /*
    public static void main(String[] args) {
        Wolf wolf = new Wolf(1);
        testWolf(wolf);
    }

    private static void testWolf(Wolf wolf) {
        // Display initial information
        System.out.println("Initial Wolf Information:");
        System.out.println(wolf);

        // Testing setId and getId methods
        wolf.setId(2);
        System.out.println("Updated Wolf Id: " + wolf.getId());

        // Testing callFriend method
        System.out.println("Calling a Friend:");
        Wolf friendWolf = wolf.callFriend();
        System.out.println("Friend Wolf Information:");
        System.out.println(friendWolf);

        // Testing clone method
        try {
            Wolf clonedWolf = wolf.clone();
            System.out.println("Cloned Wolf Information:");
            System.out.println(clonedWolf);
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported");
        }

        // Displaying final information
        System.out.println("Final Wolf Information:");
        System.out.println(wolf);
    }*/
}
