package st.bas.parser;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * This package contains a Java-Port of the Functional Parser Combinator approach of Professor Graham Hutton.
 * <p>
 * This type is the main Parser type described in Haskell as:
 * Type Parser<A> = String -> [(A, String)] // Last String is the unconsumed part of the input stream
 *
 * @see <a href="https://www.youtube.com/watch?v=dDtZLm7HIJs">Video explaining functional parsing</a>
 * @see <a href="http://www.cs.nott.ac.uk/~pszgmh/Parsing.hs">Haskell implementation</a>
 */
public interface Parser<A> {

    /**
     *
     * @param input The input string to parse
     * @return a Tuple of the parsed tree and the unconsumed part of the input stream
     */
    Result<Parsed<A>> parse(String input);

    default <B> Parser<B> map(Function<? super A, B> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");

        return input -> {
            Result<Parsed<A>> parsedResult = parse(input);
            if (parsedResult.isFailure()) {
                return parsedResult.mapFailure();
            } else {
                Parsed<A> parsedA = parsedResult.result();
                return Result.of(new Parsed<>(mapper.apply(parsedA.parsed()), parsedA.rest()));
            }
        };
    }

    // flatMap a.k.a. andThen a.k.a. bind
    //
    default <B> Parser<B> flatMap(Function<Parsed<? super A>, ? extends Parser<B>> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");

        return input -> {
            Result<Parsed<A>> result = parse(input);
            if (result.isFailure()) {
                return result.mapFailure();
            } else {
                Parsed<A> parseResult = result.result();
                return mapper.apply(parseResult).parse(parseResult.rest());
            }
        };
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

    static String restOf(String input) {
        char[] chars = input.toCharArray();
        return new String(chars, 1, chars.length - 1);
    }

    static String stringOf(List<Character> characters) {
        char[] chars = new char[characters.size()];
        for(int i = 0; i < characters.size(); i++) {
            chars[i] = characters.get(i);
        }
        return new String(chars);
    }
}
