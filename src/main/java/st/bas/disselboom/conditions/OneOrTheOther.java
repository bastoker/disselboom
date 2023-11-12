package st.bas.disselboom.conditions;

//  ... AND (cart.type = 'christmas' OR cart.old = TRUE)
public class OneOrTheOther extends Combine {

    public OneOrTheOther(Condition one, Condition other) {
        super(one, other);
    }

    @Override
    public String sql() {
        return "(%s OR %s)".formatted(one.sql(), other.sql());
    }

    @Override
    public String toString() {
        return "OneOrTheOther{" +
                "one=" + one +
                ", other=" + other +
                '}';
    }
}
