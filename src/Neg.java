import java.util.Map;

/**
 * The Neg class represents the negation operation in an expression.
 * It is a subclass of the UnaryExpression class and implements the
 * Expression interface.
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * Constructs a new Neg object with the specified expression.
     *
     * @param expression the expression to be negated
     */
    public Neg(Expression expression) {
        super(expression);
    }

    /**
     * Constructs a new Neg object with the specified number.
     *
     * @param number the number to be negated
     */
    public Neg(Double number) {
        super(number);
    }

    /**
     * Constructs a new Neg object with the specified number.
     *
     * @param number the number to be negated
     */
    public Neg(int number) {
        super(number);
    }

    /**
     * Returns the string representation of the negation expression.
     *
     * @return the string representation of the negation expression
     */
    @Override
    public String toString() {
        return "(-" + super.getOperand().toString() + ")";
    }

    /**
     * Assigns a new expression to the specified variable within the
     * negation expression.
     *
     * @param var the variable to be assigned
     * @param expression the new expression to assign to the variable
     * @return a new negation expression with the updated variable assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(super.getOperand().assign(var, expression));
    }

    /**
     * Evaluates the negation expression by substituting variable values from
     * the given assignment.
     *
     * @param assignment the map of variable-value assignments
     * @return the result of evaluating the negation expression
     * @throws Exception if a variable in the expression is not present
     * in the assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double operand = super.getOperand().evaluate(assignment);
        return -1 * operand;
    }

    /**
     * Computes the derivative of the negation expression with respect to
     * the specified variable.
     *
     * @param var the variable to differentiate with respect to
     * @return the derivative of the negation expression
     */
    @Override
    public Expression differentiate(String var) {
        return new Neg(super.getOperand().differentiate(var));
    }

    /**
     * Gets the symbol of the negation operation.
     *
     * @return the symbol representing the negation operation
     */
    public String getSymbol() {
        return "-";
    }

    /**
     * Simplifies the negation expression.
     *
     * @return a simplified version of the negation expression
     */
    public Expression simplify() {
        // -0.0 = 0.0
        if (super.getOperand().simplify().toString().equals("0.0")) {
            return new Num(0);
        }
        try {
            return new Num(-1 * super.getOperand().simplify().evaluate());
        } catch (Exception except) {
            return new Neg(super.getOperand().simplify());
        }
    }
}
