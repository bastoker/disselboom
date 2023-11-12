package st.bas.disselboom.conditions;

import java.util.Collections;
import java.util.List;

public final class IsNull<T> implements Operator<T> {

    public static final IsNull<?> INSTANCE = new IsNull<>();

    private IsNull() {
    }

    // a Null value is always compatible with every column type
    @SuppressWarnings("unchecked")
    public static <T> IsNull<T> isNull() {
        return (IsNull<T>) INSTANCE;
    }

    @Override
    public String sql() {
        return " IS NULL";
    }

    @Override
    public List<T> getPlaceholders() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "IsNull";
    }
}
