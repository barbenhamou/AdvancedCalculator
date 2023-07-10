import java.util.Map;

/**
 * Represent a multiplication expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * Constructor.<br>
     *
     * @param e1 the first expression we want to multiply.
     * @param e2 the second expression we want to multiply.
     */
    public Mult(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double e1After, e2After;
        if (getE1() instanceof Num) {
            e1After = getE1().evaluate();
        } else {
            e1After = getE1().evaluate(assignment);
        }

        if (getE2() instanceof Num) {
            e2After = getE2().evaluate();
        } else {
            e2After = getE2().evaluate(assignment);
        }

        return e1After * e2After;
    }

    @Override
    public double evaluate() throws Exception {
        return getE1().evaluate() * getE2().evaluate();
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Mult(getE1().assign(var, e), getE2().assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(
                new Mult(getE1().differentiate(var), getE2()),
                new Mult(getE1(), getE2().differentiate(var))
        );
    }

    @Override
    public Expression simplify() {
        if (getE1().simplify().toString().equals("0.0")
                || getE2().simplify().toString().equals("0.0")) {
            return new Num(0);
        }
        if (getE1().simplify().toString().equals("1.0")) {
            return getE2().simplify();
        }
        if (getE2().simplify().toString().equals("1.0")) {
            return getE1().simplify();
        }
        if (getE2().simplify() instanceof Num && getE1().simplify() instanceof Num) {
            try {
                return new Num(getE2().simplify().evaluate() * getE1().simplify().evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new Mult(getE1().simplify(), getE2().simplify());
    }

    @Override
    public String toString() {
        return "(" + getE1().toString() + " * " + getE2().toString() + ")";
    }
}