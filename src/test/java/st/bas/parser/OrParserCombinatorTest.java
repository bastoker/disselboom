package st.bas.parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static st.bas.parser.Combinators.or;

public class OrParserCombinatorTest {

    private static final Parser<Character> digitParser = new DigitParser();
    private static final Parser<Character> letterParser = new LetterParser();

    @Test
    void orCombinatorUsesParserA() {
        Result<Parsed<Character>> parseResult = or(digitParser, letterParser).parse("1");
        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>('1', "")));
    }

    @Test
    void orCombinatorUsesParserB() {
        Result<Parsed<Character>> parseResult = or(digitParser, letterParser).parse("a");
        assertThat(parseResult.isFailure()).isFalse();
        assertThat(parseResult).isEqualTo(Result.of(new Parsed<>('a', "")));
    }
}
