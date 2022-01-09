package actions;

public class UncoupleLocomotive extends Action {

    private final int locomotiveId;

    public UncoupleLocomotive(int time, int locomotiveId) {
        super(ActionType.UNCOUPLE_LOCOMOTIVE, time);
        this.locomotiveId = locomotiveId;
    }

    public int getLocomotiveId() {
        return this.locomotiveId;
    }

}
