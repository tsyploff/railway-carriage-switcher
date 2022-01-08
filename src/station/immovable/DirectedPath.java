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
public class DirectedPath {

    private final String from;
    private final String to;
    private final TrackConnectionType type;

    public DirectedPath(String from, String to, TrackConnectionType type) {
        this.from = from;
        this.to = to;
        this.type = type;
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
