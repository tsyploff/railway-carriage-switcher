package station.movable;

import station.immovable.RailwayTrack;
import station.immovable.TrackConnectionType;

/**
 * Класс для описания маневровых локомотивов. Локомотив в отличие от составов
 * может самостоятельно двигаться. Направление движения определяется enum:
 *
 *   если movementDirection == FORWARD, то движение от switchStart к switchEnd (слева направо);
 *   если movementDirection == BACKWARD, то движение от switchEnd к switchStart (справа налево).
 *
 * Спереди и сзади у локомотива есть сцепки, к которым может быть прикреплён
 * состав attachment. Если состав прикреплён спереди (справа), то
 * railwayCouplingType == FRONT, иначе REAR.
 *
 * Если movementDirection == FORWARD и railwayCouplingType == REAR, то локомотив
 * ТЯНЕТ состав. Если movementDirection == BACKWARD и railwayCouplingType == FRONT,
 * то локомотив ТЯНЕТ состав.
 *
 * Во всех остальных раскладках локомотив ТОЛКАЕТ состав.
 */
public class ShuntingLocomotive {

    private int time;
    private int speed; // скорость в метрах в минуту
    private int offsetStart;
    private RailwayTrack track;
    private RailwayTrain attachment;
    private MovementDirection movementDirection;
    private RailwayCouplingType railwayCouplingType;

    public ShuntingLocomotive(int time, int speed, int offsetStart, String trackName) {
        this.time = time;
        this.speed = speed;
        this.offsetStart = offsetStart;
        this.setTrack(trackName);
        this.attachment = null;
        this.movementDirection = MovementDirection.FORWARD;
        this.railwayCouplingType = RailwayCouplingType.REAR;
    }

    public RailwayCouplingType getRailwayCouplingType() {
        return railwayCouplingType;
    }

    public void setMovementDirection(MovementDirection movementDirection) {
        this.movementDirection = movementDirection;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void changeMovementDirection() {
        if (this.movementDirection == MovementDirection.FORWARD) {
            this.movementDirection = MovementDirection.BACKWARD;
        } else {
            this.movementDirection = MovementDirection.FORWARD;
        }

    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setTrack(String trackName) {
        this.track = RailwayTrack.get(trackName);
    }

    public void setAttachment(RailwayTrain train, RailwayCouplingType type) {
        this.attachment = train;
        this.railwayCouplingType = type;
    }

    public RailwayTrain removeAttachment() {
        RailwayTrain train = this.attachment;
        this.attachment = null;
        this.railwayCouplingType = RailwayCouplingType.REAR;
        return train;
    }

    /**
     * Локомотив выполняет движение в рамках одного ж/д пути. Если к нему
     * прикрепрлён состав, состав движется. Функция не проверяет безопасность
     * операции.
     */
    public void drive(int offsetStart) {

        int driveTime = Math.abs(offsetStart - this.offsetStart) / this.speed;
        this.time += driveTime;
        this.offsetStart = offsetStart;

        if (this.attachment != null) {
            this.attachment.addTime(driveTime);
            int trainLength = this.attachment.getRailcars().size() * RailwayTrain.getRailcarLength();
            if (this.railwayCouplingType == RailwayCouplingType.FRONT) {
                this.attachment.setOffsetStart(offsetStart + RailwayTrain.getRailcarLength());
            } else {
                this.attachment.setOffsetStart(offsetStart - trainLength);
            }
        }

    }

    /**
     * Локомотив выполняет переезд с одного ж/д пути на другой. Функция не
     * проверяет, что у путей есть общая стрелка, но подразумевает это. В
     * аргументах указывается тип соединения путей. Функция не проверяет,
     * что состав целиком помещается на указанный путь с учётом длины этого
     * пути и расположения других поездов.
     */
    public void drive(int offsetStart, String trackName, TrackConnectionType connectionType) {

        if (this.movementDirection == MovementDirection.FORWARD) {
            this.drive(this.track.getLengthMeters());
        } else {
            this.drive(0);
        }

        this.setTrack(trackName);
        this.setMovementDirection(connectionType.getMovementDirection());

        if (this.attachment != null) {
            this.attachment.setTrack(trackName);

            if (connectionType.isTrackOrientationChange()) {
                this.attachment.changeReverseOrientation();

                if (this.railwayCouplingType == RailwayCouplingType.FRONT) {
                    this.railwayCouplingType = RailwayCouplingType.REAR;
                } else {
                    this.railwayCouplingType = RailwayCouplingType.FRONT;
                }
            }
        }

        if (this.movementDirection == MovementDirection.FORWARD) {
            this.offsetStart = 0;
        } else {
            this.offsetStart = this.track.getLengthMeters();
        }
        this.drive(offsetStart);

    }

}
