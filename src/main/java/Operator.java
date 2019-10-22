public enum Operator {
    PLUS("+", 0),
    MINUS("-", 0),
    MULTIPLICATION("*", 1),
    DIVISION("/", 1);

    public final String text;
    public final int priority;

    Operator(String text, int priority) {
        this.text = text;
        this.priority = priority;
    }

    public static Operator readValue(String value){
        for (Operator operator : Operator.values()) {
            if (operator.text.equals(value)) {
                return operator;
            }
        }
        throw new RuntimeException("Can't read " + value);
    }
}
