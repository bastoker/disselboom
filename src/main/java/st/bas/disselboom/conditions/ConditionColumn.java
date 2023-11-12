package st.bas.disselboom.conditions;


import st.bas.disselboom.table.Table;

import java.util.Arrays;
import java.util.List;

/**
 * Supertype of all column types.
 *  @param <T> Type of the database column this column describes.
 */
public class ConditionColumn<T extends Comparable<T>> {
    protected final ConditionLevel level;
    protected final String tableName;
    protected final String columnName;

    public ConditionColumn(ConditionLevel level, Table tableDesc, String columnName) {
        this.level = level;
        this.tableName = tableDesc.getName();
        this.columnName = columnName;
    }

    public ConditionLevel getLevel() {
        return level;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public ColumnCondition<T> isEqualTo(T waarde) {
        return new ColumnCondition<>(this, new IsEqualTo<>(waarde));
    }

    public ColumnCondition<T> inList(List<T> waarden) {
        return new ColumnCondition<>(this, new InList<>(waarden));
    }

    public ColumnCondition<T> inList(T... valuesArgs) {
        List<T> values = Arrays.asList(valuesArgs);
        return inList(values);
    }

    public ColumnCondition<T> between(T from, T to) {
        return new ColumnCondition<>(this, new Between<>(from, to));
    }

    @Override
    public String toString() {
        return "Column{" +
                "level=" + level +
                ", tabelnaam='" + tableName + '\'' +
                ", kolomnaam='" + columnName + '\'' +
                '}';
    }
}
