package actions;

public class SwitchMovementDirection extends Action {

    private final int locomotiveId;

    public SwitchMovementDirection(int time, int locomotiveId) {
        super(ActionType.SWITCH_MOVEMENT_DIRECTION, time);
        this.locomotiveId = locomotiveId;
    }

    public int getLocomotiveId() {
        return this.locomotiveId;
    }

}
