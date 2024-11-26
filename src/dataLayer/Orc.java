package dataLayer;

import dataLayer.Enums.OpponentType;

public class Orc extends AbstractOpponent {

    private boolean madeHeavyHit = false;

    public Orc(String opponentId) {
        super(opponentId, OpponentType.Orc);
        madeHeavyHit = false;
    }

    public Orc(Orc original) {
        super(original);
        madeHeavyHit = original.madeHeavyHit;
    }

    public void heavyHit(Turn toPlayer) {
    	if (toPlayer.checkGuarded()) {
    	    double damageDealt = getAttack();
    	    toPlayer.getDamage(damageDealt);
    	    System.out.println(String.format("Orc %s made a heavy hit to %s, deals %.2f damage.", getIdOrName(), toPlayer.toString(), damageDealt));
    	} else {
    	    double damageDealt = getAttack() * 2;
    	    toPlayer.getDamage(damageDealt);
    	    System.out.println(String.format("Orc %s made a heavy hit to %s, deals %.2f damage.", getIdOrName(), toPlayer.toString(), damageDealt));
    	}


        madeHeavyHit = true;
    }

    public void skipTurn() {
        System.out.println(toString() + "made heavy hit last turn, It is going to skip this turn");
        madeHeavyHit = false;
    }

    public boolean checkIfMadeHeavyHit() {
        return madeHeavyHit;
    }

    @Override
    public Orc clone() throws CloneNotSupportedException {
        return new Orc(this);
    }

    @Override
    public String toString() {
        return String.format("Orc: %s Points: %d, Attack Stat: %d Speed %d ", getIdOrName(), getPoints(),
                getAttack(), getSpeed());
    }
    
    /**
     *        TEST   (ALL METHODS ARE CHECKED)
     */
    
    /*
    public static void main(String[] args) {
        Orc orc = new Orc(1);
        testOrc(orc);
    }

    private static void testOrc(Orc orc) {
        // Display initial information
        System.out.println("Initial Orc Information:");
        System.out.println(orc);

        // Testing setId and getId methods
        orc.setId(2);
        System.out.println("Updated Orc Id: " + orc.getId());

        // player instance for testing heavyHit method
        Player player = new Player(100, 20, 60);

        // Testing heavyHit method
        System.out.println("Performing Heavy Hit on Player:");
        orc.heavyHit(player);
        System.out.println("Player's Points after Heavy Hit: " + player.getPoints());   // getAttack * 2

        // Testing skipTurn method
        System.out.println("Skipping Turn:");
        orc.skipTurn();                      // madeHeavyHit changed to false

        // Testing checkIfMadeHeavyHit method
        System.out.println("Check if Orc made Heavy Hit last turn: " + orc.checkIfMadeHeavyHit());        // should output false

        // Testing clone method
        try {
            Orc clonedOrc = orc.clone();
            System.out.println("Cloned Orc Information:");
            System.out.println(clonedOrc);
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported");
        }

        // Display final information
        System.out.println("Final Orc Information:");
        System.out.println(orc);
    }*/

}
