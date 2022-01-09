package actions;

public class UncoupleWagons extends Action {

    int trainId;
    int leftCount; // количество вагонов, начиная с ближайшего к началу пути, которые нужно отцепить

    public UncoupleWagons(int time, int trainId, int leftCount) {
        super(ActionType.UNCOUPLE_WAGONS, time);
        this.trainId = trainId;
        this.leftCount = leftCount;
    }

    public int getTrainId() {
        return trainId;
    }

    public int getLeftCount() {
        return leftCount;
    }

}