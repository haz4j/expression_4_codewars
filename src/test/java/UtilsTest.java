import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class UtilsTest {

    @Test
    void parseExpression() {
        String expression = "1 + 2";
        List<String> parsed = Utils.parseExpression(expression);
        Assertions.assertEquals(Arrays.asList("1", "+", "2"), parsed);
    }

    @Test
    void parseExpressionWithBracers() {
        String expression = "(1 + 2) / (((3+7 *21)))";
        List<String> parsed = Utils.parseExpression(expression);
        Assertions.assertEquals(
                Arrays.asList("(", "1", "+", "2", ")", "/", "(", "(", "(", "3", "+", "7", "*", "21", ")", ")", ")"),
                parsed);
    }
}