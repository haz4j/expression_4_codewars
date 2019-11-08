import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Expression {
    private Operator operator;
    private Integer value;
    @Singular
    private List<Expression> subExpressions = new ArrayList<>();
    private Expression wrapper;

    public Expression(Integer value) {
        this.value = value;
    }

    public Expression(Operator operator) {
        this.operator = operator;
    }

    public Expression(List<Expression> expressions) {
        this.subExpressions = expressions;
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
                '}';
    }
}
