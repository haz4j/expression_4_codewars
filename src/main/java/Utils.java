import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Expression rootExpression = new Expression();
        for (String item : strings) {
            if (isNumber(item)) {
                Expression expression = new Expression(Integer.parseInt(item));
                if (rootExpression.getLeft() == null) {
                    rootExpression.setLeft(expression);
                } else {
                    rootExpression.setRight(new Expression(Integer.parseInt(item)));
                }
                continue;
            }
            if (isOperator(item)) {
                rootExpression.setOperator(Operator.readValue(item));
            }
        }
        return rootExpression;
    }
}
