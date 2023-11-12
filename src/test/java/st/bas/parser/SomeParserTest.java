package st.bas.parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static st.bas.parser.Combinators.or;
import static st.bas.parser.Combinators.some;

public class SomeParserTest {

    private static final Parser<Character> digitParser = new DigitParser();
    private static final Parser<Character> letterParser = new LetterParser();

    @Test
    void someCombinatorParsesDigitOrLetterLiterals() {
        Result<Parsed<List<Character>>> parseResult = some(or(digitParser, letterParser)).parse("1a2b3c");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList('1', 'a', '2', 'b', '3', 'c'), "")));
    }

    @Test
    void someCombinatorParsesDigitLiterals() {
        Result<Parsed<List<Character>>> parseResult = some(digitParser).parse("123");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList('1', '2', '3'), "")));
    }

    @Test
    void manyCombinatorParsesSingleDigitLiteral() {
        Result<Parsed<List<Character>>> parseResult = some(digitParser).parse("1");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList('1'), "")));
    }

    @Test
    void someCombinatorMapsEmptyInputToFailure() {
        Result<Parsed<List<Character>>> parseResult = some(digitParser).parse("");

        assertThat(parseResult.isFailure()).isTrue();
        assertThat(parseResult.getFailureMessage()).contains("No digit found at the start of String(\"\")");
    }

    @Test
    void someCombinatorMapsLetterInputToFailure() {
        Result<Parsed<List<Character>>> parseResult = some(digitParser).parse("abc");

        assertThat(parseResult.isFailure()).isTrue();
        assertThat(parseResult.getFailureMessage()).contains("No digit found at the start of String(\"abc\")");
    }

    @Test
    void someCombinatorParsesDigitLiteralsFollowedByLetters() {
        Result<Parsed<List<Character>>> parseResult = some(digitParser).parse("123ab");

        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>(Arrays.asList('1', '2', '3'), "ab")));
    }
}
