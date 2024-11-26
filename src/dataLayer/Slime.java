package dataLayer;

import dataLayer.Enums.OpponentType;

public class Slime extends AbstractOpponent {

    public Slime(String opponentId) {
        super(opponentId, OpponentType.Slime);
    }

    public Slime(Slime original) {
        super(original);
    }

    public void absorb(Turn toPlayer) {
        if (toPlayer.checkGuarded()) {
            toPlayer.getDamage(getAttack() * 0.25);
            System.out.println("Opponent " + type.toString() + " Id: " + getIdOrName() + " absorbed "
            + toPlayer.getIdOrName() + " damaged " + getAttack() * 0.25);
        } else {
            toPlayer.getDamage(getAttack());
            System.out.println("Opponent " + type.toString() + " Id: " + getIdOrName() + " absorbed "
            + toPlayer.getIdOrName() + " damaged " + getAttack() );
        }
        if (getPoints() + getAttack() >= 150) {
            setPoints(150);
        } else {
            setPoints(getPoints() + getAttack());
        }
    }

    @Override
    public Slime clone() throws CloneNotSupportedException {
        return new Slime(this);
    }

    @Override
    public String toString() {
        return String.format("Slime: %s Points: %d, Attack Stat: %d Speed %d", getIdOrName(), getPoints(),
                getAttack(), getSpeed());
    }
    
    /**
     *      	TEST   (ALL METHODS ARE CHECKED)
     */
    
    
    /*
    public static void main(String[] args) {
        Slime slime = new Slime(1);
        testSlimeMethods(slime);
    }

    private static void testSlimeMethods(Slime slime) {
        // Display initial information
        System.out.println("Initial Slime Information:");
        System.out.println(slime);

        // testing absorb method
        Player player = new Player(100, 30, 70);
        System.out.println("Player's points before absorb : " + player.getPoints());
        slime.absorb(player);
        System.out.println("Player's points after absorb : " + player.getPoints());
        
        // Testing clone method
        try {
            Slime clonedSlime = slime.clone();
            System.out.println("Cloned Slime Information:");
            System.out.println(clonedSlime);
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported");
        }

        // Display final information
        System.out.println("Final Slime Information:");
        System.out.println(slime);
    }*/

}
