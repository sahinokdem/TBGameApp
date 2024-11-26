package dataLayer;

import java.util.Random;

import exceptionsLayer.InsufficientStaminaException;
import exceptionsLayer.SpearThrownException;

public abstract class Human<W> extends Turn implements Character<W> {

    private int stamina;
    private W w;
    private boolean specialUsed = false;
    private boolean runned = false;
    private int attackModifier = 1;

    public Human() {
        super("Anonymous" + new Random().nextInt(999999), createRandomPoints(), createRandomAttack(),
                createRandomSpeed());
        stamina = 10;
    }

    public Human(String IdOrName, W w) {
        super(IdOrName, createRandomPoints(), createRandomAttack(), createRandomSpeed());
        this.w = w;
        stamina = 10;
    }

    public Human(Human<W> original) {
        super(original);
        w = original.w;
        stamina = original.stamina;
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
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    @Override
    public int getStamina() {
        return stamina;
    }

    @Override
    public void setW(W w) {
        this.w = w;
    }

    @Override
    public W getW() {
        return w;
    }

    @Override
    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    @Override
    public int getAttackModifier() {
        return attackModifier;
    }

    @Override
    public void punch(Turn toPlayer) throws InsufficientStaminaException {
        if (stamina < 1) {
            throw new InsufficientStaminaException();
        }
        if (toPlayer.checkGuarded()) {
            toPlayer.getDamage(getAttack() * 0.4 * attackModifier);
            System.out.println(
                    getIdOrName() + " punched " + toPlayer.toString() + ", deals " + getAttack() * 0.4 * attackModifier
                            + " damage.");
        } else {
            toPlayer.getDamage(getAttack() * 0.8 * attackModifier);
            System.out.println(
                    getIdOrName() + " punched " + toPlayer.toString() + ", deals " + getAttack() * 0.8 * attackModifier
                            + " damage.");
        }
        stamina--;
    }

    @Override
    public void attackWithWeapon(Turn toPlayer, int weaponActionNumber) throws InsufficientStaminaException, SpearThrownException {
        try {
            Weapon weapon = (Weapon) w;
            double totalAttack;
            if (weapon instanceof Bow) {
                totalAttack = ((Bow) weapon).useWeapon(getAttack(), weaponActionNumber, stamina);
                stamina -= weapon.reduceStaminaOfOwner();
            } else if (weapon instanceof Sword) {
                totalAttack = ((Sword) weapon).useWeapon(getAttack(), weaponActionNumber, stamina);
                stamina -= weapon.reduceStaminaOfOwner();
            } else {
                totalAttack = ((Spear) weapon).useWeapon(getAttack(), weaponActionNumber, stamina);
                stamina -= weapon.reduceStaminaOfOwner();
            }
            if (toPlayer.checkGuarded()) {
                totalAttack *= 0.5 * attackModifier;
            } else {
                totalAttack *= attackModifier;
            }
            toPlayer.getDamage(totalAttack);
            System.out.println(getIdOrName() + " " + weapon.getActionInfo() + "damaged " + totalAttack + " to Opponent "
                    + toPlayer.getIdOrName());
        } catch (ClassCastException e) {
            System.out.println("W is not type of Weapon, therefore this method is not available");
        }
    }

    @Override
    public void guard() {
        setGuarded(true);
        System.out.println(getIdOrName() + "taking guard for next turn!");
    }

    @Override
    public void run() {
        runned = true;
    }

    @Override
    public boolean checkRunned() {
        return runned;
    }

    public void setSpecialUsed(boolean specialUsed) {
        this.specialUsed = specialUsed;
    }

    @Override
    public boolean checkIfSpecialUsed() {
        return specialUsed;
    }

    @Override
    public abstract Human<W> clone() throws CloneNotSupportedException;

    @Override
    public String toString() {
        return String.format("Character: {Name: %s, Stamina: %d, Points: %d, Attack Stat: %d Speed %d}", getIdOrName(),
                getStamina(), getPoints(), getAttack(), getSpeed());
    }

}
