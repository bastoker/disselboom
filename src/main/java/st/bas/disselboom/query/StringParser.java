package st.bas.disselboom.query;

import st.bas.parser.Parsed;
import st.bas.parser.Parser;
import st.bas.parser.Result;

public class StringParser implements Parser<String> {

    private final static String quoteAsString = "'";
    private final static char quote = '\'';

    @Override
    public Result<Parsed<String>> parse(String input) {

        if (input.length() > 1) {
            if (input.startsWith(quoteAsString)) {
                StringBuilder builder = new StringBuilder();
                char[] chars = input.toCharArray();
                for (int i = 1; i < chars.length; i++) {
                    if (chars[i] != quote) {
                        builder.append(chars[i]);
                    } else {
                        return Result.of(new Parsed<>(builder.toString(), new String(chars, i + 1, chars.length - i - 1)));
                    }
                }
            }
        }
        return Result.failure("Not enough characters left in input string to match to a SQL text literal");
    }
}
