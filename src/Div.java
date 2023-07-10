import java.util.Map;

/**
 * Represent a division expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * Constructor.<br>
     *
     * @param e1 the divided.
     * @param e2 the divisor.
     */
    public Div(Expression e1, Expression e2) {
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
        if (e2After == 0) {
            throw new Exception("Division by zero");
        }
        return e1After / e2After;
    }

    @Override
    public double evaluate() throws Exception {
        double e1After = getE1().evaluate(), e2After = getE2().evaluate();
        if (e2After == 0) {
            throw new Exception("Division by zero");
        }
        return e1After / e2After;
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Div(getE1().assign(var, e), getE2().assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        if (getE2() instanceof Num) {
            return new Mult(getE2(), getE1().differentiate(var));
        }
        return new Div(
                new Minus(
                        new Mult(getE1().differentiate(var), getE2()),
                        new Mult(getE1(), getE2().differentiate(var))
                ),
                new Pow(getE2(), new Num(2))
        );
    }

    @Override
    public Expression simplify() {
        if (getE1().simplify().toString().equals(getE2().simplify().toString())) {
            return new Num(1);
        }
        if (getE1().simplify().toString().equals("0.0")) {
            return new Num(0);
        }
        if (getE2().simplify().toString().equals("1.0")) {
            return getE1().simplify();
        }
        if (getE2().simplify() instanceof Num && getE1().simplify() instanceof Num) {
            try {
                if (getE2().simplify().evaluate() == 0) {
                    return new Div(getE1(), getE2());
                }
                return new Num(getE1().evaluate() / getE2().evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new Div(getE1().simplify(), getE2().simplify());
    }

    @Override
    public String toString() {
        return "(" + getE1().toString() + " / " + getE2().toString() + ")";
    }
}