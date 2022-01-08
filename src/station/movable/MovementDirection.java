package station.movable;

public enum MovementDirection {

    FORWARD(1),
    BACKWARD(-1);

    int speedCoefficient;

    MovementDirection(int speedCoefficient) {
        this.speedCoefficient = speedCoefficient;
    }

    public int getSpeedCoefficient() {
        return this.speedCoefficient;
    }

}
