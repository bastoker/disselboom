package st.bas.disselboom.conditions;

import java.util.List;

/**
 * Expressie zijn (samenstellingen van) geparameteriseerde SQL-condities die evalueren tot een
 * onderdeel van een SQL WHERE-clause.
 */
public interface Condition {
    ConditionLevel level();

    String sql();

    List<?> getPlaceholders();
}
