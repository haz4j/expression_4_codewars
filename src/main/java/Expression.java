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

    public Integer evaluate() {
        if (getLeft() != null && getRight() != null) {
            value = getOperator().getOperator().apply(getLeft().evaluate(), getRight().evaluate());
        }
        return value;
    }
}
