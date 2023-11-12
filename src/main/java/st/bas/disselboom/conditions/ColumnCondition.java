package st.bas.disselboom.conditions;

import java.util.List;

/**
 * Conditions are parameterized SQL conditions that evaluate to a SQL predicate.
 * @param <T> Type of the database column which is compatbile with this condition
 */
public record ColumnCondition<T>(ConditionColumn<?> kolom, Operator<?> operator) implements Condition {

    private static final String DOT = ".";

    @Override
    public ConditionLevel level() {
        return kolom.getLevel();
    }

    @Override
    public String sql() {
        // e.g. 'cart.id = 1234'
        return  kolom.getTableName() + DOT + kolom.getColumnName() + operator.sql();
    }

    public T getValue() {
        return null;
    }

    @Override
    public List<?> getPlaceholders() {
        return operator.getPlaceholders();
    }

    @Override
    public String toString() {
        return "KolomConditie{" +
                "kolom=" + kolom +
                ", operator=" + operator +
                '}';
    }
}
