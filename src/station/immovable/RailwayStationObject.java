package station.immovable;

import java.lang.Integer;
import java.util.ArrayList;

public class RailwayStationObject {

    protected int id;
    protected RailwayStationObjectType type;

    private static ArrayList<Integer> ids = new ArrayList<>();

    public RailwayStationObject(RailwayStationObjectType type) {
        this.id = ids.isEmpty() ? 0 : ids.get(ids.size() - 1) + 1;
        ids.add(this.id);
        this.type = type;
    }

}
