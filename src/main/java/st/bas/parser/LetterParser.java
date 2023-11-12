package st.bas.parser;

import static st.bas.parser.Parser.restOf;

public class LetterParser implements Parser<Character> {

    @Override
    public Result<Parsed<Character>> parse(String input) {
        if (!input.isEmpty() && Character.isLetter(input.charAt(0))) {
            return Result.of(new Parsed<>(input.charAt(0), restOf(input)));
        } else {
            return Result.failure("No letter found at the start of String(\"%s\") ".formatted(input));
        }
    }
}
