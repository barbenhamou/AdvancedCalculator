import java.util.List;
import java.util.Map;

/**
 * A representative of an expression.<br>
 * name: Bar Ben Hamou.<br>
 * id: 330591207.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided.<br>
     *
     * @param assignment the variables and their values.
     * @return the result, may throw exception if a variable that is not
     * in the expression is provided.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;


    /**
     * Evaluate with empty assignment.<br>
     *
     * @return the value or an exception.
     */
    double evaluate() throws Exception;

    /**
     * Getter for all the variables.<br>
     *
     * @return string list of the variables.
     */
    List<String> getVariables();


    /**
     * @return a nice representation of the expression.
     */
    String toString();

    /**
     * Doing some sort of function composition.<br>
     *
     * @param e   the expression we want to composite.
     * @param var the var we want to switch with the expression.
     * @return new expression after composition.
     */
    Expression assign(String var, Expression e);

    /**
     * @param var the variable we differentiate with.
     * @return the differential.
     * */
    Expression differentiate(String var);

    /**
     * Simplifying the expression.<br>
     *
     * @return the simplified expression.
     * */
    Expression simplify();
}