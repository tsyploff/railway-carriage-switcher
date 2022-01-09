package station.immovable;

/**
 * DirectedPath это специальный класс, экземпляры которого представляют пары рёбер в графе
 * железнодорожных путей на станции. А имеено, если с пути A можно переехать на путь B, то
 * в графе будет содержаться ориентированный путь A, B.
 *
 * DirectedPath так же может быть использован для хранения маршрутов поездов, т. к. может
 * содержать последовательность путей железнодорожного графа.
 *
 * Поскольку запуск программы и инициализация объектов происходит через Wolfram, выгоднее
 * импользовать идентификаторы (т. е. примитивные типы) в аргументах.
 */
public class RailwaySwitch {

    private final String from;
    private final String to;
    private final TrackConnectionType type;

    public RailwaySwitch(String from, String to, String typeName) {
        this.from = from;
        this.to = to;
        switch (typeName) {
            case "START_TO_START" -> this.type = TrackConnectionType.START_TO_START;
            case "START_TO_END" -> this.type = TrackConnectionType.START_TO_END;
            case "END_TO_END" -> this.type = TrackConnectionType.END_TO_END;
            default -> this.type = TrackConnectionType.END_TO_START;
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public TrackConnectionType getType() {
        return type;
    }

}
