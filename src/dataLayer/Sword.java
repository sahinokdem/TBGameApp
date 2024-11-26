package dataLayer;

import exceptionsLayer.InsufficientStaminaException;

public class Sword extends Weapon {

    public Sword() {
        super();
    }

    public Sword(Sword original) {
        super(original);
    }

    @Override
    public double useWeapon(int attack, int actionNumber, int stamina) throws InsufficientStaminaException {
        if (stamina < 2) {
            throw new InsufficientStaminaException();
        } else {
            setReducingStamina(2);
            if (actionNumber == 1) {
                return slash(attack);
            } else {
                return stab(attack);
            }
        }
    }

    private int slash(int attack) {
        setActionInfo("slashed with sword ");
        return attack + getAdditionalAttack();
    }

    private int stab(int attack) {
        if (succeed()) {
            setActionInfo(
                    "stabbed succesfully with sword ");
            return (attack + getAdditionalAttack()) * 2;
        } else {
            setActionInfo("Stab failed, damaged 0");
            return 0;
        }
    }

    private boolean succeed() {
        double randomValue = Math.random();
        return randomValue > 0.25;
    }

    @Override
    public Sword clone() throws CloneNotSupportedException {
        return new Sword(this);
    }
}
