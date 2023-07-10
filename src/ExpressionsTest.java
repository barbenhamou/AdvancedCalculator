import java.util.Map;
import java.util.TreeMap;

/**
 * A tester.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public class ExpressionsTest {
    /**
     * The main function of the program.<br>
     * @param args - arguments from cmd.
     */
    public static void main(String[] args) {
        Expression e = new Plus(new Plus(new Mult(new Num(2), new Var("x")),
                new Sin(new Mult(new Num(4), new Var("y")))),
                new Pow(new Var(
                        "e"),
                        new Var("x")));

        System.out.println(e);
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        try {
            System.out.println(e.evaluate(assignment));
        } catch (Exception e1) {
            System.out.println("failed 1");
        }
        System.out.println(e.differentiate("x"));
        try {
            System.out.println(e.differentiate("x").evaluate(assignment));
        } catch (Exception e2) {
            System.out.println("failed 2");
        }
        System.out.println(e.differentiate("x").simplify());
    }
}