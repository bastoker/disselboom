package st.bas.disselboom.conditions;

import java.util.List;

/**
 * WhereCondition is a condition that can be appended to a top-level WHERE SQL clause.
 * It consists of an AND operator followed by the condition.
 */
public class WhereCondition implements Condition {

    private final Condition condition;

    public WhereCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public ConditionLevel level() {
        return condition.level();
    }

    @Override
    public String sql() {
        return " AND %s".formatted(condition.sql());
    }

    @Override
    public List<?> getPlaceholders() {
        return condition.getPlaceholders();
    }


    @Override
    public String toString() {
        return "WhereCondition{" +
                "condition=" + condition +
                '}';
    }
}
