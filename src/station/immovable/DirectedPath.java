package station.immovable;

import java.util.ArrayList;

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
    String from;
    String to;

    ArrayList<String> intermediateTracks;

    public DirectedPath(String from, String to) {
        this.from = from;
        this.to = to;
        this.intermediateTracks = new ArrayList<>();
    }

    /**
     * Возвращает список идентификаторов (!) железнодорожных путей, начиная с from и
     * заканчивая to.
     */
    public ArrayList<String> getTrackNames() {
        ArrayList<String> path = new ArrayList<>();
        path.add(this.from);
        path.addAll(this.intermediateTracks);
        path.add(to);
        return path;
    }

    /**
     * Возвращает список железнодорожных путей (!), т. е. экземпляров класса RailwayTrack,
     * у которых можно посмотреть длину, вместимость и т. д.
     */
    public ArrayList<RailwayTrack> getTracks() {
        ArrayList<RailwayTrack> path = new ArrayList<>();
        for (String trackName: this.getTrackNames()) {
            path.add(RailwayTrack.get(trackName));
        }
        return path;
    }

    /**
     *  Добавляет промежуточные вершины в путь.
     */
    public void addIntermediateTrack(String trackName) {
        this.intermediateTracks.add(trackName);
    }

    /**
     * Путь называется простым, если не содержит промежуточных ж/д путей.
     */
    public boolean isSimple() {
        return this.intermediateTracks.isEmpty();
    }

}
