package dataLayer;

import exceptionsLayer.InsufficientStaminaException;
import exceptionsLayer.SpearThrownException;

public abstract class Weapon {

    private int additionalAttack;
    private int reducingStamina;
    private String actionInfo;

    public Weapon() {
        additionalAttack = createAdditionalAttack();
    }

    public Weapon(Weapon original) {
        additionalAttack = original.additionalAttack;
    }

    public int createAdditionalAttack() {
        return (int) (Math.random() * 11) + 10;
    }

    public int getAdditionalAttack() {
        return additionalAttack;
    }

    public void setReducingStamina(int reducingStamina) {
        this.reducingStamina = reducingStamina;
    }

    public int reduceStaminaOfOwner() {
        return reducingStamina;
    }

    public String getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(String actionInfo) {
        this.actionInfo = actionInfo;
    }

    public abstract double useWeapon(int attack, int actionNumber, int stamina) throws InsufficientStaminaException, SpearThrownException;

    @Override
    public abstract Weapon clone() throws CloneNotSupportedException;
}
