package st.bas;

import st.bas.disselboom.table.Table;

public class QueryDSL {
    private QueryDSL() {
    }

    public static QueryDSL.SelectBuilder select() {
        return new QueryDSL.SelectBuilder();
    }

    public static class SelectBuilder {

        public FromContext from(Table table, String tableAlias) {
            return new FromContext(table, tableAlias);
        }
    }

    public static class FromContext {
        private final Table table;
        private final String tableAlias;

        public FromContext(Table table, String tableAlias) {
            this.table = table;
            this.tableAlias = tableAlias;
        }

//        public leftJoin(TableDesc table)

    }
}
