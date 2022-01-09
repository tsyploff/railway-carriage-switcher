package actions;

/**
 * Присоединяет состав к маневровому локомотиву. Предполагается, что
 * локомотив будет находиться на том же пути.
 */
public class CoupleLocomotive extends Action {

    private final int trainId;
    private final int locomotiveId;

    public CoupleLocomotive(int time, int trainId, int locomotiveId) {
        super(ActionType.COUPLE_LOCOMOTIVE, time);
        this.trainId = trainId;
        this.locomotiveId = locomotiveId;
    }

    public int getTrainId() {
        return this.trainId;
    }

    public int getLocomotiveId() {
        return this.locomotiveId;
    }

}
