import java.util.List;

/**
 * Represent a binary expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression e1;
    private Expression e2;

    /**
     * Constructor.<br>
     *
     * @param e1 the first expression we want to apply something.
     * @param e2 the second expression we want to apply something.
     */
    public BinaryExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    /**
     * Getter for all the variables.<br>
     *
     * @return string list of the variables.
     */
    public List<String> getVariables() {
        List<String> vars = getE1().getVariables();
        vars.addAll(getE2().getVariables());
        return vars;
    }

    /**
     * Getter for the first expression.<br>
     *
     * @return the first expression.
     */
    public Expression getE1() {
        return e1;
    }

    /**
     * Getter for the second expression.<br>
     *
     * @return the second expression.
     */
    public Expression getE2() {
        return e2;
    }
}