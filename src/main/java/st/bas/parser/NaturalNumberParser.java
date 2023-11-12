package st.bas.parser;

import java.util.Objects;
import java.util.function.Function;

import static java.lang.Integer.parseInt;
import static st.bas.parser.Combinators.some;
import static st.bas.parser.Parser.stringOf;

public class NaturalNumberParser implements Parser<Integer> {
    private static final DigitParser digits = new DigitParser();

    @Override
    public Result<Parsed<Integer>> parse(String input) {
       return some(digits).parse(input).map(p -> p.map(cs -> parseInt(stringOf(cs))));
    }

    @Override
    public <B> Parser<B> flatMap(Function<? super Integer, ? extends Parser<? extends Integer>> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");

        return null;
    }

    // Example from VAVR:
//    public <U> Array<U> flatMap(Function<? super T, ? extends Iterable<? extends U>> mapper) {
//        Objects.requireNonNull(mapper, "mapper is null");
//        if (isEmpty()) {
//            return empty();
//        } else {
//            final java.util.List<U> list = new ArrayList<>();
//            for (T t : this) {
//                for (U u : mapper.apply(t)) {
//                    list.add(u);
//                }
//            }
//            return wrap(list.toArray());
//        }
//    }
}
