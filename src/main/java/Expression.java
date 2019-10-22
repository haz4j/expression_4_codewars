import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Expression {
    private Operator operator;
    private Integer value;
    private List<Expression> subexpressions;
    public void evaluate() {
        value = Utils.evaluate(subexpressions);
    }
}
