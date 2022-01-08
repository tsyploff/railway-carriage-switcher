package station.movable;

import java.util.ArrayList;
import java.util.Collections;

import station.immovable.RailwayTrack;

/**
 * Массив вагонов. Содержит полный список идентификаторов вагонов и
 * предназначен для их разовой модификации. Например, в тех
 * случаях, когда локомотив перемещает сцепку (т. е. состав)
 * вагонов с одного пути на другой.
 */
public class RailwayTrain {

    final static int railcarLength = 15;

    ArrayList<Integer> railcars; // Идентификаторы вагонов
    boolean reverseOrientation;
    int time;
    int offsetStart;
    RailwayTrack track;

    /**
     * Конструктор для состава из одного вагона
     * @param railcar - идентификатор вагона
     * @param time – время в минутах с момента начала моделирования
     * @param offsetStart – отступ в метрах от switchStart до центра ближайшего к нему вагона
     * @param trackName - идентификатор пути, на котором находится состав
     * @param reverseOrientation - ориентация списка вагонов относительно пути: если
     * false, то offsetStart считается по this.railcars.get(0), если true, то по offsetStart
     * считается по this.railcars.get(this.railcars.size() - 1)
     */
    public RailwayTrain(int railcar, int time, int offsetStart, String trackName, boolean reverseOrientation) {
        this.railcars = new ArrayList<>();
        this.railcars.add(railcar);
        this.time = time;
        this.offsetStart = offsetStart;
        this.setTrack(trackName);
        this.reverseOrientation = reverseOrientation;
    }

    /**
     * Конструктор для копирования
     * @param railcars - список вагонов
     */
    public RailwayTrain(
            ArrayList<Integer> railcars,
            int time,
            int offsetStart,
            RailwayTrack track,
            boolean reverseOrientation
    ) {
        this.railcars = railcars;
        this.time = time;
        this.offsetStart = offsetStart;
        this.track = track;
        this.reverseOrientation = reverseOrientation;
    }

    public ArrayList<Integer> getRailcars() {
        return railcars;
    }

    public int getOffsetStart() {
        return offsetStart;
    }

    public static int getRailcarLength() {
        return railcarLength;
    }

    public boolean getReverseOrientation() {
        return reverseOrientation;
    }

    public void setTrack(String trackName) {
        this.track = RailwayTrack.get(trackName);
    }

    public void changeReverseOrientation() {
        this.reverseOrientation = !this.reverseOrientation;
    }

    public void addTime(int timeDelta) {
        this.time += timeDelta;
    }

    public void setOffsetStart(int offsetStart) {
        this.offsetStart = offsetStart;
    }

    /**
     * Присоединяет состав слева. Будем говорить, что состав other расположен слева относительно
     * состава this, если other.offsetStart + other.railcars.size() * railcarLength <= this.offsetStart
     */
    public void concatenateLeft(RailwayTrain other) {
        ArrayList<Integer> railcars = new ArrayList<>(this.railcars.size() + other.getRailcars().size());
        railcars.addAll(other.getRailcars());

        if (this.reverseOrientation) {
            Collections.reverse(this.railcars);
        }

        if (other.getReverseOrientation()) {
            Collections.reverse(railcars);
        }

        railcars.addAll(this.railcars);

        this.offsetStart = other.getOffsetStart();
        this.reverseOrientation = false;
        this.railcars = railcars;
    }

    /**
     * Присоединяет состав справа. Будем говорить, что состав other расположен справа относительно
     * состава this, если this.offsetStart + this.railcars.size() * railcarLength <= other.offsetStart
     */
    public void concatenateRight(RailwayTrain other) {
        ArrayList<Integer> railcars = new ArrayList<>(other.getRailcars());

        if (this.reverseOrientation) {
            Collections.reverse(this.railcars);
        }

        if (other.getReverseOrientation()) {
            Collections.reverse(railcars);
        }

        this.offsetStart = other.getOffsetStart();
        this.reverseOrientation = false;
        this.railcars.addAll(railcars);
    }

    /**
     * Отделяет от состава слева [относительно порядка вагонов в списке] railcarCount
     * вагонов в отдельный состав
     * @param railcarCount количество вагонов в отдельном составе
     * @return полученный состав
     */
    public RailwayTrain popLeft(int railcarCount) {
        if (this.reverseOrientation) {
            Collections.reverse(this.railcars);
        }

        ArrayList<Integer> left = new ArrayList<>(railcarCount);
        ArrayList<Integer> right = new ArrayList<>(this.railcars.size() - railcarCount);

        for (int i = 1; i < this.railcars.size(); i++) {
            if (i < railcarCount) {
                left.add(this.railcars.get(i));
            } else {
                right.add(this.railcars.get(i));
            }
        }

        this.railcars = right;
        this.reverseOrientation = false;
        this.offsetStart += railcarLength * railcarCount;

        return new RailwayTrain(left, this.time, this.offsetStart, this.track, false);
    }

    /**
     * Отделяет от состава справа [относительно порядка вагонов в списке] railcarCount
     * вагонов в отдельный состав
     * @param railcarCount количество вагонов в отдельном составе
     * @return полученный состав
     */
    public RailwayTrain popRight(int railcarCount) {
        if (this.reverseOrientation) {
            Collections.reverse(this.railcars);
        }

        int remainingNumber = this.railcars.size() - railcarCount;

        ArrayList<Integer> left = new ArrayList<>(remainingNumber);
        ArrayList<Integer> right = new ArrayList<>(railcarCount);

        for (int i = 1; i < this.railcars.size(); i++) {
            if (i < railcarCount) {
                left.add(this.railcars.get(i));
            } else {
                right.add(this.railcars.get(i));
            }
        }

        this.railcars = left;
        this.reverseOrientation = false;

        return new RailwayTrain(
                right,
                this.time,
                this.offsetStart + railcarLength * remainingNumber,
                this.track,
                false
        );
    }

}
