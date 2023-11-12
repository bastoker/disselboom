package st.bas.disselboom.conditions;

import st.bas.disselboom.table.Table;

/**
 * Boolean specific subtype of column.
 */
public class BooleanColumn extends ConditionColumn<Boolean> {
    public BooleanColumn(ConditionLevel niveau, Table tableDesc, String kolomnaam) {
        super(niveau, tableDesc, kolomnaam);
    }

    public ColumnCondition<Boolean> isTrue() {
        return new ColumnCondition<>(this, new IsEqualTo<>(true));
    }

    public ColumnCondition<Boolean> isFalse() {
        return new ColumnCondition<>(this, new IsEqualTo<>(false));
    }
}
