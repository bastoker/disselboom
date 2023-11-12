package st.bas.parser;

import java.util.function.Function;

public record Parsed<A>(A parsed, String rest) {

    public <U> Parsed<U> map(Function<? super A, ? extends U> mapper) {
        return new Parsed<>(mapper.apply(parsed), rest);
    }
}
