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
    private List<Expression> childs = new ArrayList<>();
    private Expression parent;

    public Expression(Integer value) {
        this.value = value;
    }

    public void evaluate() {
        value = Utils.evaluate(childs);
    }

    @Override
    public String toString() {
        return "Expression{" +
                "operator=" + operator +
                ", value=" + value +
                ", expressions=" + childs +
                '}';
    }
}
