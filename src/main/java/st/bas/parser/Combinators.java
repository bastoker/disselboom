package st.bas.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static st.bas.parser.Parser.restOf;

public class Combinators {
    /**
     * Some defines the semantics of parsing one or more A's defined by downstreamParser.
     * <p>
     * Haskell type and implementation of some
     * -- | One or more.
     * some :: f a -> f [a]
     * some v = some_v
     * where
     * many_v = some_v <|> pure []
     * some_v = liftA2 (:) v many_v
     */
    public static <A> Parser<List<A>> some(Parser<A> downstreamParser) {
        Function<Parsed<A>, Parsed<List<A>>> mapper =
                parsed -> parsed.map(Collections::singletonList);

        return (final String input) -> {
            List<A> parsedList = new ArrayList<>();
            String rest = input;
            while (true) {
                Result<Parsed<A>> result = downstreamParser.parse(rest);
                if (result.isSuccess()) {
                    parsedList.add(result.result().parsed());
                    if (result.result().rest().isEmpty()) {
                        return Result.of(new Parsed<>(parsedList, ""));
                    }
                } else {
                    if (parsedList.isEmpty()) {
                        // semantics for some is 1 or more, so propagate failure in case of 0
                        return result.map(mapper);
                    } else {
                        return Result.of(new Parsed<>(parsedList, rest));
                    }
                }
                rest = restOf(rest);
            }
        };
    }

    public static <A> Parser<List<A>> many(Parser<A> downstreamParser) {
        return (final String input) -> {
            List<A> parsedList = new ArrayList<>();
            String rest = input;
            while (true) {
                Result<Parsed<A>> result = downstreamParser.parse(rest);
                if (result.isSuccess()) {
                    parsedList.add(result.result().parsed());
                    if (result.result().rest().isEmpty()) {
                        return Result.of(new Parsed<>(parsedList, ""));
                    }
                } else {
                    if (parsedList.isEmpty()) {
                        // semantics for some is 0 or more, so propagate empty list in case of 0
                        return Result.of(new Parsed<>(Collections.emptyList(), rest));
                    } else {
                        return Result.of(new Parsed<>(parsedList, rest));
                    }
                }
                rest = restOf(rest);
            }
        };
    }

    public static <A> Parser<A> or(Parser<A> downstreamParserA, Parser<A> downstreamParserB) {
        return (final String input) -> {
            Result<Parsed<A>> resultA = downstreamParserA.parse(input);
            if (resultA.isSuccess()) {
                return resultA;
            } else {
                return downstreamParserB.parse(input);
            }
        };
    }
}
