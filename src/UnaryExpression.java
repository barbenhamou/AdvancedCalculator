import java.util.List;

/**
 * Represent a unary expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression e1;

    /**
     * Constructor.<br>
     *
     * @param e1 the first expression we want to apply something.
     */
    public UnaryExpression(Expression e1) {
        this.e1 = e1;
    }

    /**
     * Getter for all the variables.<br>
     *
     * @return string list of the variables.
     */
    public List<String> getVariables() {
        return e1.getVariables();
    }

    /**
     * Getter for the first expression.<br>
     *
     * @return the first expression.
     * */
    public Expression getE1() {
        return e1;
    }
}