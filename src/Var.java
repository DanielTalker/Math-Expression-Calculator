// 322624206 Daniel Talker

import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.TreeMap;

/**
 * The Var class represents a variable in an arithmetic expression.
 * It implements the Expression interface.
 */
public class Var implements Expression {
    private String variable;

    /**
     * Instantiates a new Var with the specified variable name.
     *
     * @param variable the name of the variable
     */
    public Var(String variable) {
        this.variable = variable;
    }

    /**
     * Evaluates the value of the variable based on the given variable
     * assignments.
     *
     * @param assignment a map containing variable assignments
     * @return the value of the variable
     * @throws Exception if the variable is not found in the assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!assignment.containsKey(variable)) {
            throw new Exception("Variable not found in assignment: " + variable);
        }
        return assignment.get(variable);
    }

    /**
     * Evaluates the value of the variable assuming no variable assignments.
     * If the variable name is "e", it returns the value of Euler's
     * number (approximately 2.71828).
     *
     * @return the value of the variable
     * @throws Exception if evaluation fails
     */
    @Override
    public double evaluate() throws Exception {
        if (variable.equals("e")) {
            Map<String, Double> assignment = new TreeMap<String, Double>();
            assignment.put("e", Math.E);
            return this.evaluate(assignment);
        }
        return this.evaluate(Collections.emptyMap());
    }

    /**
     * Retrieves the list of variables in the expression,
     * which is just the current variable.
     *
     * @return a list containing the variable name
     */
    @Override
    public List<String> getVariables() {
        return List.of(variable);
    }

    /**
     * Returns a string representation of the variable.
     *
     * @return the variable name
     */
    @Override
    public String toString() {
        return variable;
    }

    /**
     * Assigns a new expression to the specified variable.
     * If the variable name matches, it returns the new assigned expression;
     * otherwise, it returns itself.
     *
     * @param var the variable name
     * @param expression the new expression to assign
     * @return the new assigned expression if the variable name matches;
     * otherwise, itself
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(variable)) {
            return expression;
        }
        return this;
    }

    /**
     * Computes the derivative of the variable with respect to the
     * specified variable.
     * If the variable name matches, it returns a Num expression representing 1;
     * otherwise, it returns a Num expression representing 0.
     *
     * @param var the variable to differentiate with respect to
     * @return a Num expression representing the derivative (1 if the variable
     * name matches, 0 otherwise)
     */
    @Override
    public Expression differentiate(String var) {
        if (this.variable.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    /**
     * Simplifies the variable expression by returning itself.
     *
     * @return the variable itself
     */
    @Override
    public Expression simplify() {
        return this;
    }
}
