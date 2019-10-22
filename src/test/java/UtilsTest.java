import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void parseExpression() {
        String expression = "1 + 2";
        Expression parsed = Utils.parseExpression(expression);
        Assertions.assertEquals(
                Expression.builder()
                        .left(Expression.builder().value(1).build())
                        .operator(Operator.PLUS)
                        .right(Expression.builder().value(2).build()).build(), parsed);
    }

    @Test
    void deleteWhitespace() {
        String expression = "1 + 2";
        String withoutWhitespaces = Utils.deleteWhitespace(expression);
        Assertions.assertEquals(withoutWhitespaces, "1+2");
    }
}