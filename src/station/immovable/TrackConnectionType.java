package station.immovable;

import station.movable.MovementDirection;

/**
 * Схема соединения путей. Влияет на ориентацию движения локомотивов и
 * ориентацию вагонов относительно пути. Имеет значение при расчёте
 * offsetStart. Необходима при переезде с одного пути на другой.
 *
 * START_TO_START:
 *
 *   RailwayTrack(trackName=1, switchStartName=1, switchEndName=2)
 *   RailwayTrack(trackName=2, switchStartName=1, switchEndName=3)
 *
 * START_TO_END:
 *
 *   RailwayTrack(trackName=1, switchStartName=1, switchEndName=2)
 *   RailwayTrack(trackName=2, switchStartName=3, switchEndName=1)
 *
 * END_TO_START – последовательное соединение путей. Пример:
 *
 *   RailwayTrack(trackName=1, switchStartName=1, switchEndName=2)
 *   RailwayTrack(trackName=2, switchStartName=2, switchEndName=3)
 *
 * END_TO_END:
 *
 *   RailwayTrack(trackName=1, switchStartName=1, switchEndName=2)
 *   RailwayTrack(trackName=2, switchStartName=3, switchEndName=2)
 *
 */
public enum TrackConnectionType {

    START_TO_START(MovementDirection.FORWARD, true),
    START_TO_END(MovementDirection.BACKWARD, false),
    END_TO_START(MovementDirection.FORWARD, false),
    END_TO_END(MovementDirection.BACKWARD, true);

    MovementDirection movementDirection; // направление движение после переезда стрелки
    /**
     * Если trackOrientationChange == true, то ориентация вагонов меняется и тип сцепки меняется.
     */
    boolean trackOrientationChange;

    TrackConnectionType(MovementDirection movementDirection, boolean trackOrientationChange) {
        this.trackOrientationChange = trackOrientationChange;
        this.movementDirection = movementDirection;
    }

    public MovementDirection getMovementDirection() {
        return movementDirection;
    }

    public boolean isTrackOrientationChange() {
        return trackOrientationChange;
    }
}
