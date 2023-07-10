import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represent a number.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class Var implements Expression {
    private String var;

    /**
     * Constructor.<br>
     *
     * @param var the string we want to be our variable.
     */
    public Var(String var) {
        this.var = var;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double value;
        try {
            value = assignment.get(var);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception("Numeric evaluation of a var");
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        vars.add(var);
        return vars;
    }

    @Override
    public Expression assign(String var, Expression e) {
        if (this.var.equals(var)) {
            return e;
        }
        return new Var(this.var);
    }

    @Override
    public Expression differentiate(String var) {
        if (this.var.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return new Var(var);
    }

    @Override
    public String toString() {
        return var;
    }
}