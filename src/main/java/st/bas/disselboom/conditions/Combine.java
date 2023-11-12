package st.bas.disselboom.conditions;

import java.util.ArrayList;
import java.util.List;

//  ... AND [NOT] (werklijn.eenheid_soort = 'SLT' OR werklijn.eenheid_soort = 'ICM')
public abstract class Combine implements Condition {

    protected final Condition one;
    protected final Condition other;

    protected Combine(Condition one, Condition other) {
        if (one.level() != other.level()) {
            throw new IllegalArgumentException("Conditions of different levels cannot be mixed");
        }
        this.one = one;
        this.other = other;
    }
    @Override
    public ConditionLevel level() {
        return one.level();
    }

    @Override
    public List<Object> getPlaceholders() {
        final List<Object> result = new ArrayList<>(2);
        result.addAll(one.getPlaceholders());
        result.addAll(other.getPlaceholders());
        return result;
    }
}
