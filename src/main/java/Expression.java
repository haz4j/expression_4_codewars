import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "parent")
public class Expression {
    private Operator operator;
    private Integer value;
    @Singular
    private List<Expression> expressions = new ArrayList<>();
    private Expression child;
    private Expression parent;

    public Expression(Integer value) {
        this.value = value;
    }

    public Expression(Operator operator) {
        this.operator = operator;
    }

    public Expression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public Expression(Expression child) {
        this.child = child;
    }

    public void setChild(Expression child) {
        this.child = child;
        child.setParent(this);
    }

    public void evaluate() {
        value = Utils.evaluate(expressions);
    }

    @Override
    public String toString() {
        return "Expression{" +
                "operator=" + operator +
                ", value=" + value +
                ", expressions=" + expressions +
                ", child=" + child +
                '}';
    }
}
