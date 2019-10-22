import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UtilsTest {

    @Test
    void parseExpression() {
        String string = "1 + 2";
        List<String> parsed = Utils.parseExpression(string);
        assertEquals(Arrays.asList("1", "+", "2"), parsed);
    }

    @Test
    void parseExpressionWithBracers() {
        String string = "(1 + 2) / (((3+7 *21)))";
        List<String> parsed = Utils.parseExpression(string);
        assertEquals(
                Arrays.asList("(", "1", "+", "2", ")", "/", "(", "(", "(", "3", "+", "7", "*", "21", ")", ")", ")"),
                parsed);
    }

    @Test
    void toExpressionSimple() {
        String string = "1 + 2";
        Expression expression = Utils.toExpression(string);
        assertEquals(new Expression(new Expression(1), Operator.PLUS, new Expression(2)), expression);
    }

    @Test
    void expression3Numbers() {
        String string = "1 + 2 + 3";
        Expression expression = Utils.toExpression(string);
        assertEquals(
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
        expression.evaluate();
        assertNotNull(expression.getValue());
        assertEquals(6, expression.getValue().intValue());
    }

    @Test
    void expression4Numbers() {
        String string = "1 - 2 + 3 - 4";
        Expression expression = Utils.toExpression(string);
        assertEquals(
                new Expression(
                        new Expression(1),
                        Operator.MINUS,
                        new Expression(
                                new Expression(2),
                                Operator.PLUS,
                                new Expression(
                                        new Expression(3),
                                        Operator.MINUS,
                                        new Expression(4)
                                )
                        )
                )
                , expression);
        expression.evaluate();
        assertNotNull(expression.getValue());
        assertEquals(-2, expression.getValue().intValue());
    }
}