package dataLayer;

import exceptionsLayer.InsufficientStaminaException;
import exceptionsLayer.SpearThrownException;

public class Spear extends Weapon {

    private boolean thrown;

    public Spear() {
        super();
        thrown = false;
    }

    public Spear(Spear original) {
        super(original);
        thrown = original.thrown;
    }

    @Override
    public double useWeapon(int attack, int actionNumber, int stamina) throws InsufficientStaminaException, SpearThrownException {
        if (stamina < 2) {
            throw new InsufficientStaminaException();
        } else {
            setReducingStamina(2);
            if (actionNumber == 1) {
                return stab(attack);
            } else {
                return throwSpear(attack);
            }
        }
    }

    private double stab(int attack) {
        setActionInfo("stab the spear, ");
        return (attack + getAdditionalAttack()) * 1.1;
    }

    private int throwSpear(int attack) throws SpearThrownException {
        if (thrown) {
            throw new SpearThrownException();
        }
        setActionInfo("throw the spear, ");
        thrown = true;
        return (attack + getAdditionalAttack()) * 2;
    }

    public boolean checkThrown() {
        return thrown;
    }

    public void skipTurn() {
        thrown = false;
    }

    @Override
    public Spear clone() throws CloneNotSupportedException {
        return new Spear(this);
    }

}
