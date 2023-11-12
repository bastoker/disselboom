package st.bas.disselboom.conditions;

import java.util.Arrays;
import java.util.List;

/**
 * Between operator always evaluates to a boolean value.
 * @param from lower bound of BETWEEN operator
 * @param to uppoer bound of BETWEEN operator
 */
public record Between<T extends Comparable<T>>(T from, T to) implements Operator<T> {

    public Between {
        if (from.compareTo(to) > 0) {
            throw new IllegalArgumentException("Invalid parameters for Between: 'from' should be equal or smaller than 'to'");
        }
    }

    @Override
    public String sql() {
        return " BETWEEN ? AND ?";
    }

    @Override
    public List<T> getPlaceholders() {
        return Arrays.asList(from, to);
    }

    @Override
    public String toString() {
        return "Between{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
