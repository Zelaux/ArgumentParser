package argumentparser;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import sun.security.util.ArrayUtil;

import java.util.Arrays;
import java.util.function.Function;

import static argumentparser.ParserTest.arr;

public class SeparatorsTest extends TestCase {

    char[] defaultSeparators;

    @Test
    public void testDots() {
        defaultSeparators('.');

        check("few", paramPattern("<1><2><3>"), "1 2 3", it -> it.few);
        check("wrong parsing", paramPattern("<1><2><3>"), "1.2.3", it -> {
            return Arrays.equals(it.args.getCopyRawStrings(), arr("1", "2", "3"));
        });
    }

    private void check(String title, ParamPattern paramPattern, String text, Function<ArgumentSplitter.SplitResponse, Boolean> valid) {
        ArgumentSplitter.SplitResponse response = ArgumentSplitter.split(text, paramPattern);
        Assert.assertTrue(title,valid.apply(response));
    }

    private ParamPattern paramPattern(String text) {
        return ParamPatternParser.parse(text)
            .separators(defaultSeparators);
    }

    private void defaultSeparators(char... separators) {
        defaultSeparators = separators;
    }

    @Test
    public void testNewLines() {
        defaultSeparators('\n', ' ', '\t');
        ParamPattern paramPattern = paramPattern("<1><2><3>");
        String[] texts={
            "1 2 3",
            "1\n2 3",
            "1\n2\t3",
            "1 2\t3",
            "1 2 3",
            "1\t2\t3",
            "1\n2\n3",
        };
        for (String text : texts) {
            check("wrong parsing", paramPattern, text, it -> {
                return Arrays.equals(it.args.getCopyRawStrings(), arr("1", "2", "3"));
            });
        }







    }
}
