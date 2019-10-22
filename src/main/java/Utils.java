public class Utils {

    public static String deleteWhitespace(String expression){
        return expression.replaceAll("\\s", "");
    }

    public static Expression parseExpression(String expression){
        return new Expression();
    }
}
