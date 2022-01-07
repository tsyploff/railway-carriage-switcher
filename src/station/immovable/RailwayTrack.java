package station.immovable;

import java.util.HashMap;

public class RailwayTrack {

    String trackName;
    String switchStart;
    String switchEnd;
    int lengthMeters;
    int wagonesCapacity;

    final private static HashMap<String, RailwayTrack> trackNames = new HashMap<>(); // список всех путей

    public RailwayTrack(String trackName, String switchStartName, String switchEndName, int lengthMeters) {
        new RailwayTrack(trackName, switchStartName, switchEndName, lengthMeters, 0);
    }

    public RailwayTrack(
            String trackName,
            String switchStartName,
            String switchEndName,
            int lengthMeters,
            int wagonesCapacity
    ) {
        this.trackName = trackName;
        this.switchStart = switchStartName;
        this.switchEnd = switchEndName;
        this.lengthMeters = lengthMeters;
        this.wagonesCapacity = wagonesCapacity;
        trackNames.put(trackName, this);
    }

    public static RailwayTrack get(String trackName) {
        return trackNames.get(trackName);
    }

}
