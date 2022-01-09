package actions;

/**
 * Специальный класс действий, обёртка для запросов планировщика.
 */
public class Action {

    protected ActionType actionType;
    protected int time;

    public Action(String typeName, int time) {
        this.time = time;
        switch (typeName) {
            case "Соединить составы" -> this.actionType = ActionType.COUPLE_WAGONS;
            case "Отсоединить вагоны" -> this.actionType = ActionType.UNCOUPLE_WAGONS;
            case "Присоединить локомотив" -> this.actionType = ActionType.COUPLE_LOCOMOTIVE;
            case "Отсоединить локомотив" -> this.actionType = ActionType.UNCOUPLE_LOCOMOTIVE;
            case "Проехать через стрелку" -> this.actionType = ActionType.MOVE_TO_ANOTHER_TRACK;
            case "Поменять направление движения" -> this.actionType = ActionType.SWITCH_MOVEMENT_DIRECTION;
        }
    }

    public Action(ActionType type, int time) {
        this.time = time;
        this.actionType = type;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getTime() {
        return time;
    }

}
