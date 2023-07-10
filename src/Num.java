import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represent a number.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class Num implements Expression {
    private double num;

    /**
     * Constructor.<br>
     *
     * @param num the num we want to hold.
     */
    public Num(double num) {
        this.num = num;
    }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }

    @Override
    public double evaluate() throws Exception {
        return this.num;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression e) {
        return new Num(this.num);
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return new Num(this.num);
    }

    @Override
    public String toString() {
        if (num == Math.E) {
            return "e";
        }
        return num + "";
    }
}