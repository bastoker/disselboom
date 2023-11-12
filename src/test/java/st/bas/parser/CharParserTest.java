package st.bas.parser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharParserTest {

    private static final Parser<Character> minusCharParser = new CharParser('-');

    @Test
    void charParserParsesChar() {
        Result<Parsed<Character>> result = minusCharParser.parse("-");
        assertThat(result.isFailure()).isFalse();
        assertThat(result.result()).isEqualTo(new Parsed<>('-', ""));
    }

    @Test
    void charParserParsesCharOnBeginnenOfINput() {
        Result<Parsed<Character>> result = minusCharParser.parse("-abc");
        assertThat(result.isFailure()).isFalse();
        assertThat(result.result()).isEqualTo(new Parsed<>('-', "abc"));
    }

    @Test
    void charParserFailsWhenCharIsNotFoundAtStartOfInput() {
        Result<Parsed<Character>> result = minusCharParser.parse("bla");
        assertThat(result.isFailure()).isTrue();
        System.out.println(result.getFailureMessage());
    }
}
