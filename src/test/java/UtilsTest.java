import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class UtilsTest {

    @Test
    void parseExpression() {
        String string = "1 + 2";
        List<String> parsed = Utils.parseExpression(string);
        Assertions.assertEquals(Arrays.asList("1", "+", "2"), parsed);
    }

    @Test
    void parseExpressionWithBracers() {
        String string = "(1 + 2) / (((3+7 *21)))";
        List<String> parsed = Utils.parseExpression(string);
        Assertions.assertEquals(
                Arrays.asList("(", "1", "+", "2", ")", "/", "(", "(", "(", "3", "+", "7", "*", "21", ")", ")", ")"),
                parsed);
    }

    @Test
    void toExpressionSimple() {
        String string = "1 + 2";
        Expression expression = Utils.toExpression(string);
        Assertions.assertEquals(new Expression(new Expression(1), Operator.PLUS, new Expression(2)), expression);
    }

    @Test
    void toExpression3Numbers() {
        String string = "1 + 2 + 3";
        Expression expression = Utils.toExpression(string);
        Assertions.assertEquals(
                new Expression(
                        new Expression(1),
                        Operator.PLUS,
                        new Expression(
                                new Expression(2),
                                Operator.PLUS,
                                new Expression(3)
                        )
                )
                , expression);
    }
}