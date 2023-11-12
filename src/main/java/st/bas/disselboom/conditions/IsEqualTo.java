package st.bas.disselboom.conditions;

import java.util.Collections;
import java.util.List;

public record IsEqualTo<T>(T value) implements Operator<T> {

    @Override
    public String sql() {
        return " = ?";
    }

    @Override
    public List<T> getPlaceholders() {
        return Collections.singletonList(value);
    }

    @Override
    public String toString() {
        return "IsEqualTo{" +
                "value=" + value +
                '}';
    }
}
