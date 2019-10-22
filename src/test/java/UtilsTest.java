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
    void expression3Numbers() {
        String string = "1 + 2 + 3";
        Expression expression = Utils.toExpression(string);
        assertEquals(
                Expression.builder().subexpressions(Arrays.asList(
                        Expression.builder().value(1).build(),
                        Expression.builder().operator(Operator.PLUS).build(),
                        Expression.builder().value(2).build(),
                        Expression.builder().operator(Operator.PLUS).build(),
                        Expression.builder().value(3).build()
                )).build(),
                expression);
        expression.evaluate();
        assertNotNull(expression.getValue());
        assertEquals(6, expression.getValue().intValue());
    }

    @Test
    void expression4Numbers() {
        String string = "1 - 2 + 3 - 4";
        Expression expression = Utils.toExpression(string);
        assertEquals(
                Expression.builder().subexpressions(Arrays.asList(
                        Expression.builder().value(1).build(),
                        Expression.builder().operator(Operator.MINUS).build(),
                        Expression.builder().value(2).build(),
                        Expression.builder().operator(Operator.PLUS).build(),
                        Expression.builder().value(3).build(),
                        Expression.builder().operator(Operator.MINUS).build(),
                        Expression.builder().value(4).build()
                )).build(),
                expression);
        expression.evaluate();
        assertNotNull(expression.getValue());
        assertEquals(-2, expression.getValue().intValue());
    }

    @Test
    void expression10Numbers() {
        String string = "1 - 2 + 3 - 4 - 5 + 6 + 6 + 23 + 2 - 15";
        Expression expression = Utils.toExpression(string);
        expression.evaluate();
        assertNotNull(expression.getValue());
        assertEquals(15, expression.getValue().intValue());
    }

    @Test
    void expressionMultiply() {
        String string = "1 + 2*3";
        Expression expression = Utils.toExpression(string);
        expression.evaluate();
        assertNotNull(expression.getValue());
        assertEquals(7, expression.getValue().intValue());
    }

    @Test
    void expressionMultiply6Numbers() {
        String string = "1 * 4 / 2 * 10 / 5 * 3";
        Expression expression = Utils.toExpression(string);
        expression.evaluate();
        assertNotNull(expression.getValue());
        assertEquals(12, expression.getValue().intValue());
    }

    @Test
    void expressionMultiplySeveralNumbers() {
        String string = "4 + 3 - 10 / 5 - 5 * 3 + 7 * 2 - 4 / 2 * 2";
        Expression expression = Utils.toExpression(string);
        expression.evaluate();
        assertNotNull(expression.getValue());
        assertEquals(0, expression.getValue().intValue());
    }
}