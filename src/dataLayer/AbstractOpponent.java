package dataLayer;

import dataLayer.Enums.OpponentType;

public abstract class AbstractOpponent extends Turn implements Opponent {

    public OpponentType type;

    public AbstractOpponent(String opponentId, OpponentType type) {
        super(opponentId, createRandomPoints(), createRandomAttack(), createRandomSpeed());
        this.type = type;
    }

    public AbstractOpponent(AbstractOpponent original) {
        super(original);
        type = original.type;
    }

    private static int createRandomPoints() {
        int points = (int) (Math.random() * 101) + 50;
        return points;
    }

    private static int createRandomAttack() {
        int attack = (int) (Math.random() * 21) + 5;
        return attack;
    }

    private static int createRandomSpeed() {
        int speed = (int) (Math.random() * 90) + 1;
        return speed;
    }

    @Override
    public void attack(Turn toPlayer) {
        if (toPlayer.checkGuarded()) {
            toPlayer.getDamage(getAttack() * 0.25);
            System.out.println("Opponent " + type.toString() + " Id: " + getIdOrName() + " attacked "
                    + toPlayer.getIdOrName() + " damaged " + getAttack() * 0.25);
        } else {
            toPlayer.getDamage(getAttack());
            System.out.println("Opponent " + type.toString() + " Id: " + getIdOrName() + " attacked "
                    + toPlayer.getIdOrName() + " damaged " + getAttack());
        }

    }

    @Override
    public void guard() {
        System.out.println("Opponent " + type.toString() + " Id: " + getIdOrName() + " guarded");
        setGuarded(true);
    }

    @Override
    public abstract AbstractOpponent clone() throws CloneNotSupportedException;

    @Override
    public String toString() {
        return String.format("Opponent: %s Points: %d, Attack Stat: %d Speed %d", getIdOrName(), getPoints(), getAttack(),
                getSpeed());
    }
    
    /**
     *                 TEST   (ALL METHODS ARE CHECKED)
     * @param args
     */
    
    /**
    public static void main(String[] args) {
        // Create an instance for each opponent types
        Orc orc = new Orc(1);
        Goblin goblin = new Goblin(2);
        Slime slime = new Slime(3);
        Wolf wolf = new Wolf(4);

        // Test AbstractOpponent methods using opponent instances
        testAbstractOpponent(orc);
        System.out.println();
        testAbstractOpponent(goblin);
        System.out.println();
        testAbstractOpponent(slime);
        System.out.println();
        testAbstractOpponent(wolf);
    }

    private static void testAbstractOpponent(AbstractOpponent opponent) {
    	
        // Display initial opponent information
        System.out.println("Initial Opponent Information:");
        System.out.println(opponent);

        // Testing setId and getId methods
        opponent.setId(2);
        System.out.println("Updated Opponent Id: " + opponent.getId());
        
        // guarded player instance for testing attack method
        Player guardedPlayer = new Player(100, 30, 70);
        guardedPlayer.setGuarded(true);

        // Testing attack method with guarded player
        System.out.println("Attacking guarded player:");
        opponent.attack(guardedPlayer);
        System.out.println("Guarded Player's Points after attack: " + guardedPlayer.getPoints());   // getAttack * 0.25

        // non-guarded player instance for testing attack method
        Player nonGuardedPlayer = new Player(100, 30, 70);

        // Testing attack method with non-guarded player
        System.out.println("Attacking non-guarded player:");
        opponent.attack(nonGuardedPlayer);
        System.out.println("Non-guarded Player's Points after attack: " + nonGuardedPlayer.getPoints());   // getAttack

        // Testing guard method
        System.out.println("Guarding situation :");
        opponent.guard();
        System.out.println("Nonguarded player :  Is Guarded? " + nonGuardedPlayer.checkGuarded());

        // Testing clone method
        try {
            AbstractOpponent clonedOpponent = opponent.clone();
            System.out.println("Cloned Opponent Information:");
            System.out.println(clonedOpponent);
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported");
        }

        // Display information
        System.out.println("Final Opponent Information:");
        System.out.println(opponent);
    }*/

}
