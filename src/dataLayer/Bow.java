package dataLayer;

import exceptionsLayer.InsufficientStaminaException;

public class Bow extends Weapon {

    public Bow() {
        super();
    }

    public Bow(Bow original) {
        super(original);
    }

    @Override
    public double useWeapon(int attack, int actionNumber, int stamina) throws InsufficientStaminaException {
        if (actionNumber == 1) {
            if (stamina < 1) {
                throw new InsufficientStaminaException();
            } else {
                setReducingStamina(1);
                return shootWithOneArrow(actionNumber);
            }
        } else {
            if (stamina < 3) {
                throw new InsufficientStaminaException();
            } else {
                setReducingStamina(3);
                return shootWithTwoArrow(attack);
            }
        }
    }

    private double shootWithOneArrow(int attack) {
        setActionInfo("shooted with one arrow ");
        return (attack + getAdditionalAttack()) * 0.8;
    }

    private double shootWithTwoArrow(int attack) {
        setActionInfo("shooted with one arrow ");
        return (attack + getAdditionalAttack()) * 2.5;
    }

    @Override
    public Bow clone() throws CloneNotSupportedException {
        return new Bow(this);
    }

}
