package dataLayer;

public interface ITurn extends Comparable<ITurn> {

    public void setIdOrName(String IdOrName);

    public String getIdOrName();

    public void setPoints(int points);

    public int getPoints();

    public void setAttack(int attack);

    public int getAttack();

    public void setSpeed(int speed);

    public int getSpeed();

    public void getDamage(double damage);

    public void setGuarded(boolean guarded);

    public boolean checkGuarded();

}
