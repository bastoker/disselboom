/**
 * <direct select statement: multiple rows>    ::=   <query expression> [ <order by clause> ]
 * <p>
 *
 * <query specification>    ::=
 *          SELECT [ <set quantifier> ] <select list> <table expression>
 * <p>
 *
 * <select list>    ::=
 *          <asterisk>
 *      |     <select sublist> [ { <comma> <select sublist> }... ]
 * <p>
 *
 * <select sublist>    ::=   <derived column> | <qualifier> <period> <asterisk>
 * <p>
 *
 * <derived column>    ::=   <value expression> [ <as clause> ]
 * <p>
 *
 * <value expression>    ::=
 *          <numeric value expression>
 *      | <string value expression>
 *      | <datetime value expression>
 *      | <interval value expression>
 *
 * <term>    ::=
 *          <factor>
 *      | <term> <asterisk> <factor>
 *      | <term> <solidus> <factor>
 *
 * <factor>    ::=   [ <sign> ] <numeric primary>
 *
 * <numeric primary>    ::=   <value expression primary> | <numeric value function>
 *
 * <value expression primary>    ::=
 *          <unsigned value specification>
 *      | <column reference>
 *      | <set function specification>
 *      | <scalar subquery>
 *      | <case expression>
 *      | <left paren> <value expression> <right paren>
 *      | <cast specification>
 *
 * <unsigned value specification>    ::=   <unsigned literal> | <general value specification>
 *
 * <table expression>    ::=
 *          <from clause>
 *          [ <where clause> ]
 *          [ <group by clause> ]
 *          [ <having clause> ]
 * <p>
 *
 *
 * /////////// ORDER BY CLAUSE BELOW /////////// <p>
 *
 *
 * <order by clause>    ::=   ORDER BY <sort specification list>
 * <p>
 *
 * <sort specification list>    ::=   <sort specification> [ { <comma> <sort specification> }... ]
 * <p>
 *
 * <sort specification>    ::=   <sort key> [ <ordering specification> ]
 * <p>
 *
 * <ordering specification>    ::=   ASC | DESC
 *
 *
 * @see <a href="https://ronsavage.github.io/SQL/sql-92.bnf.html#table%20expression">...</a>
 */
package st.bas.disselboom.query;

