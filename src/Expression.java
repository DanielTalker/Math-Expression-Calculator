// 322624206 Daniel Talker

import java.util.List;
import java.util.Map;

/**
 * The interface Expression represents a mathematical expression.
 * It provides methods to evaluate, manipulate, and simplify expressions.
 */
public interface Expression {
    /**
     * Evaluates the expression using the variable values provided
     * in the assignment and returns the result.
     *
     * @param assignment a map containing variable-value pairs
     * @return the evaluated result as a double
     * @throws Exception if the expression contains a variable that is
     * not in the assignment
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluates the expression using an empty assignment.
     * A convenience method equivalent to calling evaluate with an empty
     * assignment.
     *
     * @return the evaluated result as a double
     * @throws Exception if the expression contains a variable that is
     * not in the assignment
     */
    double evaluate() throws Exception;

    /**
     * Gets a list of the variables present in the expression.
     *
     * @return a list of variables as strings
     */
    List<String> getVariables();

    /**
     * Returns a string representation of the expression.
     *
     * @return a string representation of the expression
     */
    String toString();

    /**
     * Assigns a new expression to a variable in the current expression.
     * Returns a new expression in which all occurrences of the variable `var`
     * are replaced with the provided expression. This method does not modify
     * the current expression.
     *
     * @param var        the variable to be replaced
     * @param expression the new expression to be assigned to the variable
     * @return a new expression with the variable replaced
     */
    Expression assign(String var, Expression expression);

    /**
     * Differentiates the current expression with respect to a variable.
     * Returns the expression tree resulting from differentiating
     * the current expression relative to the variable `var`.
     *
     * @param var the variable to differentiate with respect to
     * @return the differentiated expression
     */
    Expression differentiate(String var);

    /**
     * Simplifies the current expression.
     * Returns a simplified version of the current expression.
     *
     * @return the simplified expression
     */
    Expression simplify();
}
