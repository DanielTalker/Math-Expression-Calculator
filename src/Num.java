// 322624206 Daniel Talker

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;


/**
 * The Num class represents a numeric constant in an arithmetic expression.
 * It implements the Expression interface.
 */
public class Num implements Expression {
    private double value;

    /**
     * Instantiates a new Num with the specified value.
     *
     * @param value the value of the numeric constantInstantiates a new Num
     *
     */
    public Num(double value) {
        this.value = value;
    }

    /**
     * Instantiates a new Num with the specified integer value.
     *
     * @param value the value of the numeric constant as an integer
     */
    public Num(int value) {
        this.value = (double) value;
    }

    /**
     * Evaluates the numeric constant by returning its stored value.
     *
     * @param assignment a map containing variable assignments (not used in this case)
     * @return the value of the numeric constant
     * @throws Exception if evaluation fails
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.value;
    }

    /**
     * Evaluates the numeric constant by returning its stored value,
     * assuming no variable assignments.
     *
     * @return the value of the numeric constant
     * @throws Exception if evaluation fails
     */
    @Override
    public double evaluate() throws Exception {
        return this.evaluate(Collections.emptyMap());
    }

    /**
     * Retrieves the list of variables in the numeric constant.
     * Since it is a constant, it does not contain any variables.
     *
     * @return an empty list
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    /**
     * Returns a string representation of the numeric constant.
     *
     * @return a string representation of the numeric constant
     */
    @Override
    public String toString() {
        return Double.toString(value);
    }

    /**
     * Assigns a new expression to the specified variable.
     * Since the numeric constant does not contain any variables,
     * no changes are made.
     *
     * @param var the variable name
     * @param expression the new expression to assign
     * @return the unchanged numeric constant
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * Computes the derivative of the numeric constant with respect to the
     * specified variable.
     * Since the numeric constant does not contain any variables,
     * the derivative is always 0.
     *
     * @param var the variable to differentiate with respect to
     * @return a new Num expression representing the derivative,
     * which is always 0
     *
     */
    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * Simplifies the numeric constant expression by returning itself.
     *
     * @return the numeric constant itself
     */
    @Override
    public Expression simplify() {
        return this;
    }
}
