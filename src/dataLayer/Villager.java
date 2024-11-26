package dataLayer;

public class Villager<W> extends Human<W> {

    public Villager() {
        super();
    }

    public Villager(String name, W w) {
        super(name, w);
    }

    public Villager(Villager<W> original) {
        super(original);
    }

    @Override
    public Villager<W> clone() throws CloneNotSupportedException {
        return new Villager<>(this);
    }

    @Override
    public String toString() {
        return String.format("Villager: Name: %s, Stamina: %d, Points: %d, Attack Stat: %d Speed %d ", getIdOrName(),
                getStamina(), getPoints(), getAttack(), getSpeed());
    }


}
