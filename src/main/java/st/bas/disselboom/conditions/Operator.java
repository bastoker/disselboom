package st.bas.disselboom.conditions;

import java.util.List;

/**
 * a Operator is a SQL operation with 0, 1 or n operands, e.g.
 * ' = x'
 * ' IS NULL'
 * ' BETWEEN x AND y'
 */
public interface Operator<T> {
    String sql();

    List<T> getPlaceholders();
}
