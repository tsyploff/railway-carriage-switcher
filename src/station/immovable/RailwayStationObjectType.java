package station.immovable;

public enum RailwayStationObjectType {

    RAILWAY_TRACK(0, "Железнодорожный путь"),
    SPARE_RAILWAY_TRACK(1, "Запасной железнодорожный путь"),
    RAILWAY_ARROWS(2, "Желехнодорожная стрелка"),
    WORKSHOP(3, "Цех"),
    TRUCK_SCALES(4, "Весы");

    int typeId;
    String title;

    RailwayStationObjectType(int typeId, String title) {
        this.typeId = typeId;
        this.title = title;
    }

}
