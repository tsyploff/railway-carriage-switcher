package actions;

public class UncoupleLocomotive extends Action {

    int locomotiveId;

    public UncoupleLocomotive(int time, int locomotiveId) {
        super(ActionType.UNCOUPLE_LOCOMOTIVE, time);
        this.locomotiveId = locomotiveId;
    }

    public int getLocomotiveId() {
        return this.locomotiveId;
    }

}
