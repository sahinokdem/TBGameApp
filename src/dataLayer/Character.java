package dataLayer;

import exceptionsLayer.InsufficientStaminaException;
import exceptionsLayer.SpearThrownException;
import exceptionsLayer.SpecialAlreadyUsedException;

public interface Character<W> extends ITurn {

    public void setStamina(int stamina);

    public int getStamina();

    public void punch(Turn player) throws InsufficientStaminaException;

    public void attackWithWeapon(Turn toPlayer, int weaponActionNumber) throws InsufficientStaminaException, SpearThrownException;

    public void guard();

    public void run();

    public void setW(W weapon);

    public W getW();

    public boolean checkRunned();

    public void setSpecialUsed(boolean specialUsed);

    public boolean checkIfSpecialUsed();

    public void setAttackModifier(int attackModifier);

    public int getAttackModifier();

    public default void specialAction(Turn toPlayer) throws UnsupportedOperationException, SpecialAlreadyUsedException {
        throw new UnsupportedOperationException();
    }

}
