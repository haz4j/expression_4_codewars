import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Expression {

    private Expression left;
    private Operator operator;
    private Expression right;
    private Integer value;

    public Expression(Integer value) {
        this.value = value;
    }

    public Expression(Expression left, Operator operator, Expression right) {
        this.setLeft(left);
        this.setOperator(operator);
        this.setRight(right);
    }

    public void evaluate() {
        if (getLeft() != null) {
            getLeft().evaluate();
            value = getLeft().getValue();
        }
        if (getRight() != null) {
            getRight().evaluate();
            value += getRight().getValue();
        }
    }
}
