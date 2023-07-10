import java.util.Map;

/**
 * Represent a Log expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class Log extends BinaryExpression implements Expression {
    /**
     * Constructor.<br>
     *
     * @param e1 the base.
     * @param e2 the anti-log.
     */
    public Log(Expression e1, Expression e2) {
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

        if (e1After == 0 || e2After == 0 || e1After == 1 || e1After < 0) {
            throw new Exception("Cant calc");
        }

        return Math.log(e2After) / Math.log(e1After);
    }

    @Override
    public double evaluate() throws Exception {
        double e1After = getE1().evaluate(), e2After = getE2().evaluate();
        if (e1After == 0 || e2After == 0 || e1After == 1 || e1After < 0) {
            throw new Exception("Division by zero");
        }

        return Math.log(e2After) / Math.log(e1After);
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Log(getE1().assign(var, e), getE2().assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(getE2().differentiate(var),
                new Mult(getE2(), new Log(new Num(Math.E), getE1())));
    }

    @Override
    public Expression simplify() {
        if (getE1().simplify().toString().equals(getE2().simplify().toString())) {
            return new Num(1);
        }
        if (getE2().simplify() instanceof Num && getE1().simplify() instanceof Num) {
            try {
                double e1After = getE1().evaluate(), e2After = getE2().evaluate();
                if (e1After == 0 || e2After == 0 || e1After == 1 || e1After < 0) {
                    return new Log(getE1(), getE2());
                }
                return new Num(Math.log(getE2().evaluate()) / Math.log(getE1().evaluate()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new Log(getE1().simplify(), getE2().simplify());
    }

    @Override
    public String toString() {
        return "log(" + getE1().toString() + ", " + getE2().toString() + ")";
    }
}