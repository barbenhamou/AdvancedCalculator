import java.util.Map;

/**
 * Represent a cos expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class Cos extends UnaryExpression implements Expression {

    /**
     * Constructor.<br>
     *
     * @param e the expression we want to apply sin on.
     */
    public Cos(Expression e) {
        super(e);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double eAfter;
        if (getE1() instanceof Num) {
            eAfter = getE1().evaluate();
        } else {
            eAfter = getE1().evaluate(assignment);
        }
        return Math.cos(Math.toRadians(eAfter));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(getE1().evaluate()));
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Cos(getE1().assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(new Mult(new Sin(getE1()), getE1().differentiate(var)));
    }

    @Override
    public Expression simplify() {
        if (getE1().simplify() instanceof Num) {
            try {
                return new Num(Math.cos(getE1().evaluate()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new Cos(getE1().simplify());
    }

    @Override
    public String toString() {
        return "cos(" + getE1().toString() + ")";
    }
}