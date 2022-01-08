package actions;

public enum QueryType {

    COUPLE_WAGONS("Соединить составы"),
    UNCOUPLE_WAGONS("Отсоединить вагоны"),
    COUPLE_LOCOMOTIVE("Присоединить локомотив"),
    UNCOUPLE_LOCOMOTIVE("Отсоединить локомотив"),
    MOVE_TO_ANOTHER_TRACK("Проехать через стрелку"),
    SWITCH_MOVEMENT_DIRECTION("Поменять направление движения");

    private final String name;

    QueryType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
