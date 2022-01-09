package station.immovable;

import java.util.HashMap;

public class RailwayTrack {

    final private static HashMap<String, RailwayTrack> trackNames = new HashMap<>(); // список всех путей

    private final String trackName;
    private final String switchStart;
    private final String switchEnd;
    private final int lengthMeters;
    private final int wagonesCapacity;

    public RailwayTrack(String trackName, String switchStartName, String switchEndName, int lengthMeters) {
        this.trackName = trackName;
        this.switchStart = switchStartName;
        this.switchEnd = switchEndName;
        this.lengthMeters = lengthMeters;
        this.wagonesCapacity = 0;
        trackNames.put(trackName, this);
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

    public String getTrackName() {
        return this.trackName;
    }

    public String getSwitchStart() {
        return this.switchStart;
    }

    public String getSwitchEnd() {
        return this.switchEnd;
    }

    public int getWagonesCapacity() {
        return this.wagonesCapacity;
    }

    public int getLengthMeters() {
        return this.lengthMeters;
    }

    public static RailwayTrack get(String trackName) {
        return trackNames.get(trackName);
    }

    public boolean isWaitingTrack() {
        return this.wagonesCapacity > 0;
    }

}
