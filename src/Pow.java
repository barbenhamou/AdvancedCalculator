import java.util.Map;

/**
 * Represent sort of a polynomial expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * Constructor.<br>
     *
     * @param e1 the base.
     * @param e2 the power.
     */
    public Pow(Expression e1, Expression e2) {
        super(e1, e2);
    }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double e1After, e2After;
        boolean isNum1 = false, isNum2 = false;
        if (getE1() instanceof Num) {
            e1After = getE1().evaluate();
            isNum1 = true;
        } else {
            e1After = getE1().evaluate(assignment);
        }

        if (getE2() instanceof Num) {
            e2After = getE2().evaluate();
            isNum2 = true;
        } else {
            e2After = getE2().evaluate(assignment);
        }

        if (isNum1 && isNum2) {
            if (e1After < 0 && e2After < 1 && e2After > 0) {
                throw new Exception("Complex Number");
            }
            if (e1After == 0 && e2After < 0) {
                throw new Exception("Division by zero");
            }
        }
        return Math.pow(e1After, e2After);
    }

    @Override
    public double evaluate() throws Exception {
        if (getE1().evaluate() < 0 && getE2().evaluate() < 1 && getE2().evaluate() > 0) {
            throw new Exception("Complex Number");
        }
        if (getE1().evaluate() == 0 && getE2().evaluate() < 0) {
            throw new Exception("Division by zero");
        }
        return Math.pow(getE1().evaluate(), getE2().evaluate());
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Pow(getE1().assign(var, e), getE2().assign(var, e));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(
                new Pow(getE1(), getE2()),
                new Plus(
                        new Mult(getE1().differentiate(var), new Div(getE2(), getE1())),
                        new Mult(getE2().differentiate(var), new Log(new Num(Math.E),
                                getE1()))
                )
        );
    }

    @Override
    public Expression simplify() {
        if (getE2().simplify() instanceof Num && getE1().simplify() instanceof Num) {
            try {
                return new Num(Math.pow(getE1().evaluate(), getE2().evaluate()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (getE2().simplify().toString().equals("0.0") || getE1().simplify().toString().equals("1.0")) {
            return new Num(1);
        }
        if (getE1().simplify().toString().equals("0.0")) {
            return new Num(0);
        }
        if (getE2().simplify().toString().equals("1.0")) {
            return getE1().simplify();
        }
        return new Pow(getE1().simplify(), getE2().simplify());
    }

    @Override
    public String toString() {
        return "(" + getE1().toString() + "^" + getE2().toString() + ")";
    }
}