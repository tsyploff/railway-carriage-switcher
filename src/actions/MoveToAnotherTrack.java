package actions;

import station.immovable.RailwaySwitch;

public class MoveToAnotherTrack extends Action {

    private final int locomotiveId;
    private final RailwaySwitch railwaySwitch;
    private final int  offsetStart;

    public MoveToAnotherTrack(int time, int locomotiveId, RailwaySwitch railwaySwitch, int offsetStart) {
        super(ActionType.MOVE_TO_ANOTHER_TRACK, time);
        this.locomotiveId = locomotiveId;
        this.railwaySwitch = railwaySwitch;
        this.offsetStart = offsetStart;
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

}
