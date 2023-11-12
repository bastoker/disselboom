package st.bas.parser;

import static st.bas.parser.Parser.restOf;

public class CharParser implements Parser<Character> {
    private final char c;

    public CharParser(char c) {
        this.c = c;
    }

    @Override
    public Result<Parsed<Character>> parse(String input) {
        if (!input.isEmpty() && c == input.charAt(0)) {
            return Result.of(new Parsed<>(input.charAt(0), restOf(input)));
        } else {
            return Result.failure("Char '%s' not found at the start of String(\"%s\") ".formatted(c, input));
        }
    }
}
