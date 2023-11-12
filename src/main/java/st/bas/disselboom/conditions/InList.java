package st.bas.disselboom.conditions;

import java.util.List;
import java.util.StringJoiner;

public record InList<T>(List<T> elementen) implements Operator<T> {

    @Override
    public String sql() {
        final StringJoiner stringJoiner = new StringJoiner(", ", "(", ")");
        elementen.forEach(e -> stringJoiner.add("?"));
        return " IN " + stringJoiner;
    }

    @Override
    public List<T> getPlaceholders() {
        return elementen;
    }

    @Override
    public String toString() {
        return "InLijst{" +
                "elementen=" + elementen +
                '}';
    }
}

