package st.bas.disselboom.conditions;

public class Both extends Combine {

    public Both(Condition one, Condition other) {
        super(one, other);
    }

    @Override
    public String sql() {
        return "(%s AND %s)".formatted(one.sql(), other.sql());
    }
}
