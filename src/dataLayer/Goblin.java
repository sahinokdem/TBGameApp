package dataLayer;

import dataLayer.Enums.OpponentType;

public class Goblin extends AbstractOpponent {

    public Goblin(String opponentId) {
        super(opponentId, OpponentType.Goblin);
    }

    public Goblin(Goblin original) {
        super(original);
    }

    public void rushingAttack(Turn toPlayer) {
    	if (toPlayer.checkGuarded()) {
    	    double damageDealt = getAttack() * 0.7 * 0.25;
    	    toPlayer.getDamage(damageDealt);
    	    System.out.println(String.format("Opponent %s Id: %s rush attacked %s damaged %.2f",
    	            type.toString(), getIdOrName(), toPlayer.getIdOrName(), damageDealt));
    	} else {
    	    double damageDealt = getAttack() * 0.7;
    	    toPlayer.getDamage(damageDealt);
    	    System.out.println(String.format("Opponent %s Id: %s rush attacked %s damaged %.2f",
    	            type.toString(), getIdOrName(), toPlayer.getIdOrName(), damageDealt));
    	}

    }

    @Override
    public Goblin clone() throws CloneNotSupportedException {
        return new Goblin(this);
    }

    @Override
    public String toString() {
        return String.format("Goblin: %s Points: %d, Attack Stat: %d Speed %d ", getIdOrName(), getPoints(),
                getAttack(), getSpeed());
    }
    
    /**
     *            TEST   (ALL METHODS ARE CHECKED)
     */
    
    /*
    public static void main(String[] args) {
        Goblin goblin = new Goblin(1);
        testGoblin(goblin);
    }

    private static void testGoblin(Goblin goblin) {
        // Display initial information
        System.out.println("Initial Goblin Information:");
        System.out.println(goblin);

        // Testing setId and getId methods
        goblin.setId(2);
        System.out.println("Updated Goblin Id: " + goblin.getId());

        // guarded player instance for testing rushingAttack method
        Player guardedPlayer = new Player(100, 30, 70);
        guardedPlayer.setGuarded(true);

        // Testing rushingAttack method with guarded player
        System.out.println("Performing Rushing Attack on guarded player:");
        goblin.rushingAttack(guardedPlayer);
        System.out.println("Guarded Player's Points after attack: " + guardedPlayer.getPoints());   // getAttack * 0.7 * 0.25

        // non-guarded player instance for testing rushingAttack method
        Player nonGuardedPlayer = new Player(100, 30, 70);

        // Testing rushingAttack method with non-guarded player
        System.out.println("Performing Rushing Attack on non-guarded player:");
        goblin.rushingAttack(nonGuardedPlayer);
        System.out.println("Non-guarded Player's Points after attack: " + nonGuardedPlayer.getPoints());   // getAttack * 0.7 

        // Testing clone method
        try {
            Goblin clonedGoblin = goblin.clone();
            System.out.println("Cloned Goblin Information:");
            System.out.println(clonedGoblin);
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported");
        }

        // Display information
        System.out.println("Final Goblin Information:");
        System.out.println(goblin);
    }*/

}
