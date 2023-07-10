import java.util.Map;

/**
 * Represent a sin expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Constructor.<br>
     *
     * @param e1 the expression we want to apply sin on.
     */
    public Sin(Expression e1) {
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
        return Math.sin(Math.toRadians(eAfter));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians(getE1().evaluate()));
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Sin(getE1().assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(getE1().differentiate(var), new Cos(getE1()));
    }

    @Override
    public Expression simplify() {
        if (getE1().simplify() instanceof Num) {
            try {
                return new Num(Math.sin(getE1().simplify().evaluate()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new Sin(getE1().simplify());
    }

    @Override
    public String toString() {
        return "sin(" + getE1().toString() + ")";
    }
}