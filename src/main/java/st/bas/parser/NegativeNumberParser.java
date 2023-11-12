package st.bas.parser;

import java.util.Objects;
import java.util.function.Function;

import static java.lang.Integer.parseInt;
import static st.bas.parser.Combinators.some;
import static st.bas.parser.Parser.stringOf;

public class NegativeNumberParser implements Parser<Integer> {

    private static final CharParser minusParser = new CharParser('-');
    private static final DigitParser digits = new DigitParser();

    @Override
    public Result<Parsed<Integer>> parse(String input) {
       return some(digits).parse(input).map(p -> p.map(cs -> parseInt(stringOf(cs))));
    }
}
