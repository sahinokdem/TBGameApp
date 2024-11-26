package dataLayer;

public interface Opponent extends ITurn {

    public void attack(Turn toPlayer);

    public void guard();
}
