// 322624206 Daniel Talker

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The abstract class BaseExpression provides a basic implementation of
 * the Expression interface.
 * It serves as a base class for specific expression implementations.
 */
public abstract class BaseExpression implements Expression {

    /**
     * Evaluates the expression using an empty assignment.
     * A convenience method equivalent to calling evaluate with an empty
     * assignment.
     *
     * @return the evaluated result as a double
     * @throws Exception if the expression contains a variable that is not in
     * the assignment
     */
    @Override
    public double evaluate() throws Exception {
        return this.evaluate(Collections.emptyMap());
    }

    /**
     * Returns a string representation of the expression.
     *
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    /**
     * Gets a list of the variables present in the expression.
     *
     * @return an empty list, as the base expression does not contain
     * any variables
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<String>();
    }

    /**
     * Assigns a new expression to a variable in the current expression.
     * Returns the current expression as it is, since the base expression does
     * not contain any variables.
     *
     * @param var the variable to be replaced
     * @param expression the new expression to be assigned to the variable
     * @return the current expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }
}
