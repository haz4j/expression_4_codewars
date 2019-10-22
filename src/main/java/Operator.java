import lombok.Getter;

import java.util.function.BinaryOperator;

@Getter
public enum Operator {
    PLUS("+", 0, (i1, i2) -> i1 + i2),
    MINUS("-", 0, (i1, i2) -> i1 - i2),
    MULTIPLICATION("*", 1, (i1, i2) -> i1 * i2),
    DIVISION("/", 1, (i1, i2) -> i1 / i2);

    private final String text;
    private final int priority;
    private final BinaryOperator<Integer> operator;

    Operator(String text, int priority, BinaryOperator<Integer> operator) {
        this.text = text;
        this.priority = priority;
        this.operator = operator;
    }

    public static Operator readValue(String value) {
        for (Operator operator : Operator.values()) {
            if (operator.text.equals(value)) {
                return operator;
            }
        }
        throw new RuntimeException("Can't read " + value);
    }
}
