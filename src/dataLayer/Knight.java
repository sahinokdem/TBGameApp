package dataLayer;

import exceptionsLayer.SpecialAlreadyUsedException;

public class Knight<W> extends Human<W> {

    boolean specialUsedLastTurn = false;

    public Knight(String name, W w) {
        super(name, w);
    }

    public Knight() {
        super();
    }

    public Knight(Knight<W> original) {
        super(original);
    }

    @Override
    public void specialAction(Turn toPlayer) throws SpecialAlreadyUsedException {
        if (checkIfSpecialUsed()) {
            throw new SpecialAlreadyUsedException();
        }
        specialUsedLastTurn = true;
        setSpecialUsed(true);
        System.out.println("You used special action. You are going to skip this turn and deal x3 damage next turn.");
        setAttackModifier(3);
    }

    @Override
    public Knight<W> clone() throws CloneNotSupportedException {
        return new Knight<>(this);
    }

    @Override
    public String toString() {
        return String.format("Knight: Name: %s, Stamina: %d, Points: %d, Attack Stat: %d Speed %d ", getIdOrName(),
                getStamina(), getPoints(), getAttack(), getSpeed());
    }

}
