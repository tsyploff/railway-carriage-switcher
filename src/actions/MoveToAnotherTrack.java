package actions;

import station.immovable.RailwaySwitch;

public class MoveToAnotherTrack extends Action {

    private final int locomotiveId;
    private final int offsetStart;
    private final int speed;
    private final RailwaySwitch railwaySwitch;

    public MoveToAnotherTrack(int time, int locomotiveId, RailwaySwitch railwaySwitch, int offsetStart, int speed) {
        super(ActionType.MOVE_TO_ANOTHER_TRACK, time);
        this.locomotiveId = locomotiveId;
        this.railwaySwitch = railwaySwitch;
        this.offsetStart = offsetStart;
        this.speed = speed;
    }

    public int getLocomotiveId() {
        return this.locomotiveId;
    }

    public int getOffsetStart() {
        return this.offsetStart;
    }

    public RailwaySwitch getRailwaySwitch() {
        return this.railwaySwitch;
    }

    public int getSpeed() {
        return this.speed;
    }

}
