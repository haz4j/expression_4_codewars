import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static List<String> parseExpression(String expression) {
        List<String> strings = Arrays.asList(expression
                .replaceAll("\\s", "")
                .replaceAll("\\+", "#+#")
                .replaceAll("-", "#-#")
                .replaceAll("\\*", "#*#")
                .replaceAll("/", "#/#")
                .replaceAll("\\(", "#(#")
                .replaceAll("\\)", "#)#")
                .split("#"));

        return strings.stream().filter(s -> !s.isBlank()).collect(Collectors.toList());
    }

    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isOperator(String s) {
        return Arrays.stream(Operator.values()).map(Operator::getText).anyMatch(text -> text.equals(s));
    }

    public static boolean isOpenBracket(String s) {
        return Bracket.OPEN.getSymbol().equals(s);
    }

    public static boolean isCloseBracket(String s) {
        return Bracket.CLOSE.getSymbol().equals(s);
    }

    public static Expression toExpression(String string) {
        List<String> strings = parseExpression(string);

        Expression rootExpression = new Expression();
        Expression currentParent = rootExpression;
        rootExpression.setChilds(new ArrayList<>());
        for (String item : strings) {
            if (isNumber(item)) {
                Expression itemExpression = createExpressionWithParent(currentParent);
                itemExpression.setValue(Integer.parseInt(item));
            } else if (isOperator(item)) {
                Expression itemExpression = createExpressionWithParent(currentParent);
                itemExpression.setOperator(Operator.readValue(item));
            } else if (isOpenBracket(item)) {
                Expression itemExpression = createExpressionWithParent(currentParent);
                currentParent = itemExpression;
            } else if (isCloseBracket(item)) {
                currentParent = currentParent.getParent();
            } else throw new RuntimeException("Can't read " + item);
        }

        return rootExpression;
    }

    private static Expression createExpressionWithParent(Expression currentExpression) {
        Expression itemExpression = new Expression();
        itemExpression.setParent(currentExpression);
        currentExpression.getChilds().add(itemExpression);
        return itemExpression;
    }

    public static Expression evaluate(List<Expression> expressions) {
        List<Expression> withoutChilds = evaluateChildExpressions(expressions);

        List<Operator> operators = Arrays.stream(Operator.values()).sorted(Comparator.comparingInt(Operator::getPriority)).collect(Collectors.toList());

        for (Operator operator : operators) {
            withoutChilds = calculateAllOperations(withoutChilds, operator);
        }
        if (withoutChilds.size() != 1) {
            throw new RuntimeException();
        }

        return withoutChilds.get(0);
    }

    private static List<Expression> evaluateChildExpressions(List<Expression> expressions) {
        for (int i = 0; i < expressions.size(); i++) {
            if (expressions.get(i).getChilds() != null && expressions.get(i).getChilds().size() != 0) {
                Expression evaluated = evaluate(expressions.get(i).getChilds());
                expressions.set(i, evaluated);
            }
        }

        return expressions;
    }

    private static List<Expression> calculateAllOperations(final List<Expression> expressions, Operator operator) {
        List<Expression> returnExpressions = expressions;
        while (hasOperator(returnExpressions, operator)) {
            int operatorPosition = findOperatorPosition(returnExpressions, operator);
            Integer result = calculateResult(returnExpressions.subList(operatorPosition - 1, operatorPosition + 2));
            returnExpressions = replaceSubListWithResult(returnExpressions, operatorPosition, result);
        }
        return returnExpressions;
    }

    private static List<Expression> replaceSubListWithResult(List<Expression> expressions, int operatorPosition, Integer result) {
        List<Expression> before = expressions.subList(0, operatorPosition - 1);
        List<Expression> returnExpressions = new ArrayList<>(before);
        returnExpressions.add(new Expression(result));
        List<Expression> after = expressions.subList(operatorPosition + 2, expressions.size());
        returnExpressions.addAll(after);
        return returnExpressions;
    }

    private static boolean hasOperator(List<Expression> expressions, Operator operator) {
        return expressions.stream().anyMatch(expression -> operator == expression.getOperator());
    }

    private static int findOperatorPosition(List<Expression> expressions, Operator operator) {
        for (int i = 0; i < expressions.size(); i++) {
            if (operator == expressions.get(i).getOperator()) {
                return i;
            }
        }
        return 0;
    }

    private static Integer calculateResult(List<Expression> expressions) {
        if (expressions.size() != 3) {
            throw new RuntimeException();
        }
        Integer left = expressions.get(0).getValue();
        Operator operator = expressions.get(1).getOperator();
        Integer right = expressions.get(2).getValue();
        //TODO add validation
        return operator.getOperator().apply(left, right);
    }
}
