package st.bas.parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static st.bas.parser.Combinators.*;

public class ManyDigitsParserTest {

    private static final Parser<Character> digitParser = new DigitParser();

    @Test
    void manyCombinatorParsesDigitOrLetterLiterals() {
        Result<Parsed<List<Character>>> parseResult = many(or(digitParser, new LetterParser())).parse("1a2b3c");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList('1', 'a', '2', 'b', '3', 'c'), "")));
    }

    @Test
    void manyCombinatorParsesDigitLiterals() {
        Result<Parsed<List<Character>>> parseResult = many(digitParser).parse("123");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList('1', '2', '3'), "")));
    }

    @Test
    void manyCombinatorParsesSingleDigitLiteral() {
        Result<Parsed<List<Character>>> parseResult = many(digitParser).parse("1");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList('1'), "")));
    }

    @Test
    void manyCombinatorMapsEmptyInputToFailure() {
        Result<Parsed<List<Character>>> parseResult = many(digitParser).parse("");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList(), "")));
    }

    @Test
    void manyCombinatorMapsLetterInputToFailure() {
        Result<Parsed<List<Character>>> parseResult = many(digitParser).parse("abc");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList(), "abc")));
    }

    @Test
    void mannyCombinatorParsesDigitLiteralsFollowedByLetters() {
        Result<Parsed<List<Character>>> parseResult = many(digitParser).parse("123ab");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList('1', '2', '3'), "ab")));
    }
}
