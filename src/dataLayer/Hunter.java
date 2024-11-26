package dataLayer;

import exceptionsLayer.SpecialAlreadyUsedException;

public class Hunter<W> extends Human<W> {

    private boolean specialUsedLastTurn = false;

    public Hunter(String name, W w) {
        super(name, w);
    }

    public Hunter() {
        super();
    }

    public Hunter(Hunter<W> original) {
        super(original);
    }

    public void setSpecialUsedLastTurn(boolean specialUsedLastTurn) {
        this.specialUsedLastTurn = specialUsedLastTurn;
    }

    public boolean checkSpecialUsedLastTurn() {
        return specialUsedLastTurn;
    }

    @Override
    public void specialAction(Turn toPlayer) throws SpecialAlreadyUsedException {
        if (checkIfSpecialUsed()) {
            throw new SpecialAlreadyUsedException();
        }
        specialUsedLastTurn = true;
        setSpecialUsed(true);
        System.out.println(
                "You used special action. You are going to deal x0.5 damage this turn and make 2 turns next turn.");
        if (toPlayer.checkGuarded()) {
            double damageDealt = getAttack() * 0.5;
            toPlayer.getDamage(damageDealt);
            System.out.println(
                    String.format("%s attacked Opponent %s deals %.2f damage", getIdOrName(), toPlayer.getIdOrName(), damageDealt));
        } else {
            double damageDealt = getAttack();
            toPlayer.getDamage(damageDealt);
            System.out.println(
                    String.format("%s attacked Opponent %s deals %.2f damage", getIdOrName(), toPlayer.getIdOrName(), damageDealt));
        }

    }

    @Override
    public Hunter<W> clone() throws CloneNotSupportedException {
        return new Hunter<W>(this);
    }

    @Override
    public String toString() {
        return String.format("Hunter: Name: %s, Stamina: %d, Points: %d, Attack Stat: %d Speed %d", getIdOrName(),
                getStamina(), getPoints(), getAttack(), getSpeed());
    }

}
