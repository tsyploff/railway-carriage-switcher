package station.movable;

import java.util.ArrayList;
import station.immovable.RailwayTrack;

/**
 * Массив вагонов. Содержит полный список идентификаторов вагонов и
 * предназначен для их разовой модификации. Например, в тех
 * случаях, когда локомотив перемещает сцепку (т. е. состав)
 * вагонов с одного пути на другой.
 */
public class RailwayTrain {

    final int railcarLength = 15;

    ArrayList<Integer> railcars; // Идентификаторы вагонов
    int time;
    int offsetStart;
    int offsetEnd; // отступ в метрах от switchStart до центра последнего вагона
    RailwayTrack track;

    public RailwayTrain() {
        this.railcars = new ArrayList<>();
    }

    /**
     * Конструктор для состава из одного вагона
     * @param railcar - идентификатор вагона
     * @param time – время в минутах с момента начала моделирования
     * @param offsetStart – отступ в метрах от switchStart до центра последнего вагона
     * @param trackName - идентификатор пути, на котором находится состав
     */
    public RailwayTrain(int railcar, int time, int offsetStart, String trackName) {
        this.railcars = new ArrayList<>();
        this.railcars.add(railcar);
        this.time = time;
        this.setOffsetStart(offsetStart);
        this.setTrack(trackName);
    }

    public ArrayList<Integer> getRailcars() {
        return railcars;
    }

    public int getOffsetStart() {
        return offsetStart;
    }

    public int getOffsetEnd() {
        return offsetEnd;
    }

    public void setTrack(String trackName) {
        this.track = RailwayTrack.get(trackName);
    }

    public void addTime(int timeDelta) {
        this.time += timeDelta;
    }

    public void setOffsetStart(int offsetStart) {
        this.offsetStart = offsetStart;
        this.offsetEnd = this.track.getLengthMeters() - railcarLength * (this.railcars.size() - 1) - offsetStart;
    }

    /**
     * Присоединяет слева состав other. Это соответствует следующей ситуации на станции:
     * маневровый локомотив через стрелку switchEnd затолкал этот локомотив на путь и
     * подсоединил к нему другой стоящий на этом пути состав. Считается, что в результате
     * операции присоединяемый состав не двигается.
     */
    public void concatenateLeft(RailwayTrain other) {
        ArrayList<Integer> railcars = new ArrayList<>(this.railcars.size() + other.getRailcars().size());
        railcars.addAll(other.getRailcars());
        railcars.addAll(this.railcars);
        this.railcars = railcars;
        this.offsetStart = other.getOffsetStart();
        this.offsetEnd = this.track.getLengthMeters() - railcarLength * (this.railcars.size() - 1) - this.offsetStart;
    }

    /**
     * Присоединяет справа состав other. Это соответствует следующей ситуации на станции:
     * маневровый локомотив через стрелку switchStart затолкал этот локомотив на путь и
     * подсоединил к нему другой стоящий на этом пути состав. Считается, что в результате
     * операции присоединяемый состав не двигается.
     */
    public void concatenateRight(RailwayTrain other) {
        this.railcars.addAll(other.getRailcars());
        this.offsetEnd = other.getOffsetEnd();
        this.offsetStart = this.track.getLengthMeters() - railcarLength * (this.railcars.size() - 1) - this.offsetEnd;
    }

}