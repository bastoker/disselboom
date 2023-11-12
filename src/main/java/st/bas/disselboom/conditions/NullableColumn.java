package st.bas.disselboom.conditions;

import st.bas.disselboom.table.Table;

/**
 * Sybtype of column that facilitates searching for NULL values.
 * @param <T> Type of the database column this column describes.
 */
public class NullableColumn<T extends Comparable<T>> extends ConditionColumn<T> {
    public NullableColumn(ConditionLevel level, Table tableDesc, String columnName) {
        super(level, tableDesc, columnName);
    }

    public ColumnCondition<T> isNull() {
        return new ColumnCondition<>(this, IsNull.isNull());
    }
}
