package dataLayer;

import exceptionsLayer.SpecialAlreadyUsedException;

public class Squire<W> extends Human<W> {

    public Squire() {
        super();
    }

    public Squire(String name, W w) {
        super(name, w);
    }

    public Squire(Squire<W> original) {
        super(original);
    }

    @Override
    public Squire<W> clone() throws CloneNotSupportedException {
        return new Squire<>(this);
    }

    @Override
    public void specialAction(Turn toPlayer) throws SpecialAlreadyUsedException {
        if (checkIfSpecialUsed()) {
            throw new SpecialAlreadyUsedException();
        }
        setSpecialUsed(true);
        double damageDealt = getAttack() * 0.5;
        toPlayer.getDamage(damageDealt);
        setStamina(10);
        String output = String.format("%s used special, dealt %.2f damage to Opponent %s.%nA weak attack but increased stamina to 10.",
                getIdOrName(), damageDealt, toPlayer.getIdOrName());

        System.out.println(output);
    }

    @Override
    public String toString() {
        return String.format("Squire: Name: %s, Stamina: %d, Points: %d, Attack Stat: %d Speed %d ", getIdOrName(),
                getStamina(), getPoints(), getAttack(), getSpeed());
    }

}
