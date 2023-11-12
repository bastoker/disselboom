package st.bas.parser;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class NegativeNumberParserTest {

    private static final Parser<Character> minusSignParser = new CharParser('-');
    private static final Parser<Integer> naturalNumberParser = new NaturalNumberParser();
    private static final Parser<Integer> negativeNumberParser =
            minusSignParser.flatMap(p -> naturalNumberParser).map(Math::negateExact);

    @Test
    void negativeNumberIsSuccesfullyParsed() {
        Result<Parsed<Integer>> result = negativeNumberParser.parse("-123");
        assertThat(result.isFailure()).isFalse();
        assertThat(result.result()).isEqualTo(new Parsed<>(-123, ""));
    }

    @Test
    void failureToParseNegativeNumberIsAccompaniedWithHelpfulMessage() {
        Result<Parsed<Integer>> result = negativeNumberParser.parse("123");
        assertThat(result.isFailure()).isTrue();
        assertThat(result.getFailureMessage()).isEqualTo("Character '-' not found at the start of String(\"123\") ");
        System.out.println(result.getFailureMessage());
    }
}
