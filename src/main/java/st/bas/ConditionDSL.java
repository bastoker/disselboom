package st.bas;

import st.bas.disselboom.conditions.Both;
import st.bas.disselboom.conditions.Condition;
import st.bas.disselboom.conditions.OneOrTheOther;
import st.bas.disselboom.conditions.WhereCondition;

/**
 * DSL.condition().column(<COLUMN_DEFINITION).isEqualTo(1234);
 */
public class ConditionDSL {

    private ConditionDSL() {
    }

    public static WhereConditieBuilder where() {
        return new WhereConditieBuilder();
    }

    public static SubConditieBuilder samengesteld() {
        return new SubConditieBuilder();
    }

    public static class SubConditieBuilder {
        public Condition eenOfAnder(Condition een, Condition ander) {
            return new OneOrTheOther(een, ander);
        }

        public Condition beide(Condition een, Condition ander) {
            return new Both(een, ander);
        }
    }

    public static class WhereConditieBuilder {

        public WhereCondition condition(Condition kolomConditie) {
            return new WhereCondition(kolomConditie);
        }

        public WhereCondition eenOfAnder(Condition een, Condition ander) {
            return new WhereCondition(new OneOrTheOther(een, ander));
        }

        public WhereCondition beide(Condition een, Condition ander) {
            return new WhereCondition(new Both(een, ander));
        }
    }
}
