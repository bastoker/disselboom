package st.bas.parser;

import org.junit.jupiter.api.Test;
import st.bas.disselboom.query.StringParser;

import static org.assertj.core.api.Assertions.assertThat;

public class DigitParserTest {

    private static final Parser<Character> parser = new DigitParser();

    @Test
    void digitParserParsesDigitLiterals() {
        Result<Parsed<Character>> result = parser.parse("123");
        assertThat(result.isFailure()).isFalse();
        assertThat(result.result()).isEqualTo(new Parsed<>('1', "23"));
    }

    @Test
    void digitParserFailsWhenFirstCharIsLetter() {
        Result<Parsed<Character>> result = parser.parse("abc");
        assertThat(result.isFailure()).withFailMessage("Letter should not be qualified as a digit").isTrue();
    }
}
