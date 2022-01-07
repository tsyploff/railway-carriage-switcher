package station.movable;

import station.immovable.RailwayTrack;

/**
 * Класс для описания маневровых локомотивов. Локомотив в отличие от составов
 * может самостоятельно двигаться. Направление движения определяется enum:
 *
 *   если movementDirection == FORWARD, то движение от switchStart к switchEnd;
 *   если movementDirection == BACKWARD, то движение от switchEnd к switchStart.
 *
 * Спереди и сзади у локомотива есть сцепки, к которым может быть прикреплён
 * состав attachment. Если состав прикреплён спереди, то
 * railwayCouplingType == FRONT, иначе REAR.
 *
 * Если movementDirection == FORWARD и railwayCouplingType == REAR, то локомотив
 * ТЯНЕТ состав. Если movementDirection == BACKWARD и railwayCouplingType == FRONT,
 * то локомотив ТЯНЕТ состав.
 *
 * Во всех остальных раскладках локомотив ТОЛКАЕТ состав.
 */
public class ShuntingLocomotive {

    int time;
    int offsetStart;
    RailwayTrack track;
    RailwayTrain attachment;
    MovementDirection movementDirection;
    RailwayCouplingType railwayCouplingType;

    public ShuntingLocomotive(int time, int offsetStart, String trackName) {
        this.time = time;
        this.offsetStart = offsetStart;
        this.setTrack(trackName);
        this.attachment = null;
        this.movementDirection = MovementDirection.FORWARD;
        this.railwayCouplingType = RailwayCouplingType.REAR;
    }

    public void setTrack(String trackName) {
        this.track = RailwayTrack.get(trackName);
    }

}
