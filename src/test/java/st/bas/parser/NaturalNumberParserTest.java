package st.bas.parser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NaturalNumberParserTest {

    private static final Parser<Integer> parser = new NaturalNumberParser();

    @Test
    void naturalNumberIsSuccesfullyParsed() {
        Result<Parsed<Integer>> result = parser.parse("123");
        assertThat(result.isFailure()).isFalse();
        assertThat(result.result()).isEqualTo(new Parsed<>(123, ""));
    }

    @Test
    void naturalNumberEndingWithLettersIsSuccesfullyParsed() {
        Result<Parsed<Integer>> result = parser.parse("123a");
        assertThat(result.isFailure()).isFalse();
        assertThat(result.result()).isEqualTo(new Parsed<>(123, "a"));
    }

    @Test
    void naturalNumberParsingOfLettersFails() {
        Result<Parsed<Integer>> result = parser.parse("a");
        assertThat(result.isFailure()).isTrue();
    }
}
