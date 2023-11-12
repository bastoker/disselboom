package st.bas.disselboom.query;

import org.junit.jupiter.api.Test;
import st.bas.parser.Parsed;
import st.bas.parser.Result;

import static org.assertj.core.api.Assertions.assertThat;

public class StringParserTest {

    @Test
    void stringParserParsesValidSQLTextLiterals() {
        String sqlExpressionLettersAsText = "'abc'";
        String sqlExpressionDigitsAsText = "'123'";

        StringParser parser = new StringParser();

        Result<Parsed<String>> parserOutputLetters = parser.parse(sqlExpressionLettersAsText);
        assertThat(parserOutputLetters.isFailure()).isFalse();
        assertThat(parserOutputLetters.result()).isEqualTo(new Parsed<>("abc", ""));

        Result<Parsed<String>> parserOutputDigits = parser.parse(sqlExpressionDigitsAsText);
        assertThat(parserOutputDigits.isFailure()).isFalse();
        assertThat(parserOutputDigits.result()).isEqualTo(new Parsed<>("123", ""));
    }

    @Test
    void stringParserParsesValidSQLTextLiterals_withRestString() {
        String sqlExpressionWithRest = "'abc' AND ";

        StringParser parser = new StringParser();

        Result<Parsed<String>> parserOutputLetters = parser.parse(sqlExpressionWithRest);
        assertThat(parserOutputLetters.isFailure()).isFalse();
        assertThat(parserOutputLetters.result()).isEqualTo(new Parsed<>("abc", " AND "));
    }

    @Test
    void stringParserParsesInvalidStringLiteralsCorrectly() {
        String sqlExpressionWithoutEndQuot = "'abc";
        String sqlExpressionWithNumberLiteral = "123";

        StringParser parser = new StringParser();

        Result<Parsed<String>> parserOutputNoEnd = parser.parse(sqlExpressionWithoutEndQuot);
        assertThat(parserOutputNoEnd.isFailure()).isTrue();

        Result<Parsed<String>> parserOutputNumber = parser.parse(sqlExpressionWithNumberLiteral);
        assertThat(parserOutputNumber.isFailure()).isTrue();
    }
}
