public enum Operator {
    PLUS(0),
    MINUS(0),
    MULTIPLICATION(1),
    DIVISION(1);

    public final int priority;

    Operator(int priority) {
        this.priority = priority;
    }
}
