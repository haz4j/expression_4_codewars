import lombok.Getter;

@Getter
public enum Bracket {

    OPEN("("),
    CLOSE(")");

    private final String symbol;

    Bracket(String symbol) {
        this.symbol = symbol;
    }
}
