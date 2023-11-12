package st.bas.disselboom.table;

import java.sql.SQLType;

/**
 * Supertype of all column types.
 *  @param <T> The Java Type of the database column this column describes.
 */
public class Column<T extends Comparable<T>> {
    protected final Table table;
    protected final String columnName;
    private final SQLType sqlType;
    private final Class<T> javaType;

    public Column(Table table, String columnName, SQLType sqlType, Class<T> javaType) {
        this.table = table;
        this.columnName = columnName;
        this.sqlType = sqlType;
        this.javaType = javaType;
    }

//    public ColumnCondition<T> isEqualTo(T value) {
//        return new ColumnCondition<>(this, new IsEqualTo<>(value));
//    }
//
//    public ColumnCondition<T> inList(List<T> waarden) {
//        return new ColumnCondition<>(this, new InList<>(waarden));
//    }
//
//    public ColumnCondition<T> inList(T... valuesArgs) {
//        List<T> values = Arrays.asList(valuesArgs);
//        return inList(values);
//    }
//
//    public ColumnCondition<T> between(T from, T to) {
//        return new ColumnCondition<>(this, new Between<>(from, to));
//    }
}
