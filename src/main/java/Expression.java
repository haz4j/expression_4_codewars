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
