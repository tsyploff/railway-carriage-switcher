package station;

import actions.*;
import station.movable.RailwayCouplingType;
import station.movable.RailwayTrain;
import station.movable.ShuntingLocomotive;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Станция хранит информацию об изменяемых объектах: локомотивах и составах.
 * Поскольку любые действия меняют состояние локомотивов и состаов, чтобы отправлять
 * запросы на изменения, необходимо каждому поезду и каждому составу присваивать свой идентификатор.
 * За присвоение идентификаторов отвечает именно станция.
 */
public class Station {

    private int trainId;
    private int locomotiveId;

    private final HashMap<Integer, RailwayTrain> trains;
    private final HashMap<Integer, ShuntingLocomotive> locomotives;

    public Station() {
        this.trainId = 0;
        this.locomotiveId = 0;
        this.trains = new HashMap<>();
        this.locomotives = new HashMap<>();
    }

    public HashMap<Integer, RailwayTrain> getTrains() {
        return trains;
    }

    public HashMap<Integer, ShuntingLocomotive> getLocomotives() {
        return locomotives;
    }

    public void addTrain(RailwayTrain train) {
        this.trains.put(this.trainId, train);
        this.trainId += 1;
    }

    public void addLocomotive(ShuntingLocomotive locomotive) {
        this.locomotives.put(this.locomotiveId, locomotive);
        this.locomotiveId += 1;
    }

    public ArrayList<String> execute(Action action) {

        ArrayList<String> log = new ArrayList<>();

        switch (action.getActionType()) {
            case SWITCH_MOVEMENT_DIRECTION: log.addAll(this.executeSMD((SwitchMovementDirection) action));
            case COUPLE_WAGONS: log.addAll(this.executeCW((CoupleWagons) action));
            case UNCOUPLE_WAGONS: log.addAll(this.executeUW((UncoupleWagons) action));
            case COUPLE_LOCOMOTIVE: log.addAll(this.executeCL((CoupleLocomotive) action));
        }

        return log;
    }

    private ArrayList<String> executeSMD(SwitchMovementDirection action) {
        ArrayList<String> log = new ArrayList<>();

        int id = action.getLocomotiveId();
        int time = action.getTime();

        ShuntingLocomotive locomotive = this.locomotives.get(id);

        locomotive.changeMovementDirection();
        locomotive.setTime(time);

        this.locomotives.put(id, locomotive);

        log.add(String.format("Время: %d. Локомотив %d поменял направление движения", time, id));
        return log;
    }

    private ArrayList<String> executeCW(CoupleWagons action) {
        ArrayList<String> log = new ArrayList<>();

        int leftId = action.getLeftId();
        int rightId = action.getRightId();
        int time = action.getTime();

        RailwayTrain left = this.trains.get(leftId);
        RailwayTrain right = this.trains.remove(rightId);

        left.concatenateRight(right);
        left.setTime(time);

        this.trains.put(leftId, left);

        log.add(String.format("Время: %d. К составу %d справа прицеплен состав %d.", time, leftId, rightId));
        return log;
    }

    private ArrayList<String> executeUW(UncoupleWagons action) {
        ArrayList<String> log = new ArrayList<>();

        int rightId = action.getTrainId();
        int leftCount = action.getLeftCount();
        int time = action.getTime();

        log.add(String.format("Время: %d. Состав %d расцеплён на %d и %d", time, rightId, this.trainId, rightId));

        RailwayTrain left;
        RailwayTrain right = this.trains.get(rightId);

        Integer locomotiveId = right.getLocomotiveId();

        if (locomotiveId == null) {
            right.setTime(time);
            left = right.popLeft(leftCount);
        } else {
            ShuntingLocomotive locomotive = this.locomotives.get(locomotiveId);
            RailwayCouplingType type = locomotive.getRailwayCouplingType();
            locomotive.removeAttachment();
            right.setLocomotiveId(null);

            right.setTime(time);
            left = right.popLeft(leftCount);

            if (type == RailwayCouplingType.FRONT) {
                locomotive.setAttachment(left, type);
                left.setLocomotiveId(locomotiveId);
            } else {
                locomotive.setAttachment(right, type);
                right.setLocomotiveId(locomotiveId);
            }

            locomotive.setTime(time);
            this.locomotives.put(locomotiveId, locomotive);
        }


        this.trains.put(rightId, right);
        this.addTrain(left);

        return log;
    }

    private ArrayList<String> executeCL(CoupleLocomotive action) {
        ArrayList<String> log = new ArrayList<>();

        int trainId = action.getTrainId();
        int locomotiveId = action.getLocomotiveId();
        int time = action.getTime();

        ShuntingLocomotive locomotive = this.locomotives.get(locomotiveId);
        RailwayTrain train = this.trains.get(trainId);
        train.correctOrientation();

        train.setLocomotiveId(locomotiveId);
        if (train.getOffsetStart() < locomotive.getOffsetStart()) {
            locomotive.setAttachment(train, RailwayCouplingType.REAR);
        } else {
            locomotive.setAttachment(train, RailwayCouplingType.FRONT);
        }

        locomotive.setTime(time);
        train.setTime(time);

        this.locomotives.put(locomotiveId, locomotive);
        this.trains.put(trainId, train);

        log.add(String.format("Время: %d. Состав %d сцеплён с локомотивом %d", time, trainId, locomotiveId));
        return log;
    }

}
