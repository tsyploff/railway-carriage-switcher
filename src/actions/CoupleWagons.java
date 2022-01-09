package actions;

public class CoupleWagons extends Action {

    private final int leftId;
    private final int rightId;

    public CoupleWagons(int time, int leftId, int rightId) {
        super(ActionType.COUPLE_WAGONS, time);
        this.leftId = leftId;
        this.rightId = rightId;
    }

    public int getLeftId() {
        return leftId;
    }

    public int getRightId() {
        return rightId;
    }

}
