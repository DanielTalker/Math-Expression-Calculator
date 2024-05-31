import java.util.Map;

/**
 * The Sin class represents the sine function in an expression.
 * It is a subclass of the UnaryExpression class and implements the Expression
 * interface.
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * Constructs a new Sin object with the specified expression.
     *
     * @param expression the expression for the sine function
     */
    public Sin(Expression expression) {
        super(expression);
    }

    /**
     * Constructs a new Sin object with the specified number.
     *
     * @param number the number for the sine function
     */
    public Sin(Double number) {
        super(number);
    }

    /**
     * Gets the symbol of the sine function.
     *
     * @return the symbol representing the sine function
     */
    public String getSymbol() {
        return "sin";
    }

    /**
     * Evaluates the sine function by substituting variable values from the
     * given assignment.
     * If the expression contains a variable that is not present in the
     * assignment, an exception is thrown.
     *
     * @param assignment the map of variable-value assignments
     * @return the result of evaluating the sine function
     * @throws Exception if a variable in the expression is not present
     * in the assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double value = super.getExpression().evaluate(assignment);
        return Math.sin(Math.toRadians(value));
    }

    /**
     * Assigns a new expression to the specified variable within the sine
     * function expression.
     *
     * @param var the variable to be assigned
     * @param expression the new expression to assign to the variable
     * @return a new sine function expression with the updated variable
     * assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(super.getOperand().assign(var, expression));
    }

    /**
     * Computes the derivative of the Sin expression with respect to the
     * variable 'var'.
     * The derivative is calculated using the chain rule:
     * d/dx(sin(u(x))) = cos(u(x)) * u'(x),
     * where u(x) is the operand expression and u'(x) is its derivative with
     * respect to 'var'.
     *
     * @param var the variable to differentiate with respect to.
     * @return the derivative of the sine function.
     */
    @Override
    public Expression differentiate(String var) {
        Expression internalDerivative = super.getOperand().differentiate(var);
        return new Mult(new Cos(super.getOperand()), internalDerivative);
    }

    /**
     * Simplifies the sine function expression.
     *
     * @return a simplified version of the sine function expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception except) {
            return new Sin(super.getOperand().simplify());
        }
    }

}
