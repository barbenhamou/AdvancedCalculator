import java.util.Map;

/**
 * Do neg on the expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * Constructor.<br>
     *
     * @param e1 the expression we want to apply sin on.
     */
    public Neg(Expression e1) {
        super(e1);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double eAfter;
        if (getE1() instanceof Num) {
            eAfter = getE1().evaluate();
        } else {
            eAfter = getE1().evaluate(assignment);
        }
        return -1 * (eAfter);
    }

    @Override
    public double evaluate() throws Exception {
        return -1 * getE1().evaluate();
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Neg(e.assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(getE1().differentiate(var));
    }

    @Override
    public Expression simplify() {
        if (getE1().simplify() instanceof Num) {
            try {
                return new Num(-getE1().evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new Neg(getE1().simplify());
    }

    @Override
    public String toString() {
        return "(-" + getE1().toString() + ")";
    }
}