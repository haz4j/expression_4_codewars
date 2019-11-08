import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "parent") //TODO: удалить
public class Expression {
    private Operator operator;
    private Integer value;
    @Singular
    private List<Expression> subExpressions = new ArrayList<>();
    private Expression wrapper;

    private Expression child;
    private Expression parent;

    public Expression(Integer value) {
        this.value = value;
    }

    public Expression(Operator operator) {
        this.operator = operator;
    }

    public Expression(List<Expression> expressions) {
        this.subExpressions = expressions;
    }

    public Expression(Expression child) {
        this.child = child;
    }

    public void setChild(Expression child) {
        this.child = child;
        child.setParent(this);
    }

    public void evaluate() {
        value = Utils.evaluate(subExpressions);
    }

    @Override
    public String toString() {
        return "Expression{" +
                "operator=" + operator +
                ", value=" + value +
                ", expressions=" + subExpressions +
                ", child=" + child +
                '}';
    }
}
