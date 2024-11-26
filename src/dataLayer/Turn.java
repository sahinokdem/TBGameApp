package dataLayer;

public class Turn implements ITurn {

    private String IdOrName;
    private int points;
    private int attack;
    private int speed;
    private boolean guarded;

    public Turn(String IdOrName, int points, int attack, int speed) {
        this.IdOrName = IdOrName;
        this.points = points;
        this.attack = attack;
        this.speed = speed;
        this.guarded = false;
    }

    public Turn(Turn original) {
        this.IdOrName = original.IdOrName;
        this.points = original.points;
        this.attack = original.attack;
        this.speed = original.speed;
        this.guarded = false;
    }

    @Override
    public void setIdOrName(String IdOrName) {
        this.IdOrName = IdOrName;
    }

    @Override
    public String getIdOrName() {
        return IdOrName;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void getDamage(double damage) {
        points -= damage;
    }

    @Override
    public void setGuarded(boolean guarded) {
        this.guarded = guarded;
    }

    @Override
    public boolean checkGuarded() {
        return guarded;
    }

    @Override
    public Turn clone() throws CloneNotSupportedException {
        return new Turn(this);
    }

    @Override
    public int compareTo(ITurn player) {
        if (speed > player.getSpeed()) {
            return 1;
        } else if (speed == player.getSpeed()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return String.format("Player __ Points: %d, Attack Stat: %d Speed %d", points, attack, speed);
    }

}
