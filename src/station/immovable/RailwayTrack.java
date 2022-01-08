package station.immovable;

import java.util.HashMap;

public class RailwayTrack {

    final private static HashMap<String, RailwayTrack> trackNames = new HashMap<>(); // список всех путей

    private String trackName;
    private String switchStart;
    private String switchEnd;
    private int lengthMeters;
    private int wagonesCapacity;

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

    public int getLengthMeters() {
        return lengthMeters;
    }

    public static RailwayTrack get(String trackName) {
        return trackNames.get(trackName);
    }

    public boolean isWaitingTrack() {
        return this.wagonesCapacity > 0;
    }

}
