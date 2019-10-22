import java.util.Arrays;
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
            int i = Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isOperator(String s) {
        return Arrays.stream(Operator.values()).map(operator -> operator.text).anyMatch(text -> text.equals(s));
    }

    public static Expression toExpression(String string) {
        List<String> strings = parseExpression(string);
        Expression rootExpression = null;
        Expression currentExpression = null;
        for (int i = 0; i < strings.size(); i++) {
            String item = strings.get(i);
            if (currentExpression == null) {
                currentExpression = new Expression();
            }
            if (rootExpression == null) {
                rootExpression = currentExpression;
            }
            if (isNumber(item)) {
                Expression expression = new Expression(Integer.parseInt(item));
                if (rootExpression.getLeft() == null) {
                    rootExpression.setLeft(expression);
                } else {
                    if (isLastNumber(strings, i)) {
                        rootExpression.setRight(new Expression(Integer.parseInt(item)));
                    } else {
                        Expression newExpression = new Expression();
                        newExpression.setLeft(expression);
                        currentExpression = newExpression;
                        rootExpression.setRight(newExpression);
                    }
                }
                continue;
            }
            if (isOperator(item)) {
                rootExpression.setOperator(Operator.readValue(item));
            }
        }

        return rootExpression;
    }

    private static boolean isLastNumber(List<String> strings, int i) {
        List<String> sublist = strings.subList(i, strings.size());
        return sublist.stream().filter(Utils::isNumber).count() == 1;
    }
}
