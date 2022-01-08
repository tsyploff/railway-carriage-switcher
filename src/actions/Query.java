package actions;

import java.util.ArrayList;

/**
 * Данный класс отвечает за запросы. Именно эти запросы и будут обрабатывать
 * машинисты.
 */
public class Query {

    private final QueryType type;

    public Query(QueryType type) {
        this.type = type;
    }

    public QueryType getType() {
        return type;
    }

}
