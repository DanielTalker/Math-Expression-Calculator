
import java.util.Map;

/**
 * The Minus class represents a subtraction operation in an arithmetic
 * expression.
 * It extends the BinaryExpression class and implements the Expression interface.
 * The Minus class can be used to perform subtraction between two expressions
 * or between a double value and an expression.
 */
public class Minus extends BinaryExpression implements Expression {

    /**
     * Constructs a new Minus object with the specified left and right
     * expressions.
     *
     * @param left  the left expression operand
     * @param right the right expression operand
     */
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Constructs a new Minus object with the specified left double value and
     * right expression.
     *
     * @param left  the left double value operand
     * @param right the right expression operand
     */
    public Minus(double left, double right) {
        super(left, right);
    }

    /**
     * Retrieves the symbol representing the subtraction operation.
     *
     * @return the symbol "-"
     */
    @Override
    public String getOperator() {
        return "-";
    }

    /**
     * Evaluates the subtraction expression and returns the result based
     * on the given variable assignments.
     *
     * @param assignment a map containing variable assignments, where the keys
     * are variable names and the values are their corresponding values
     * @return the result of subtracting the left expression value from the
     * right expression value
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftVal = getLeft().evaluate(assignment);
        double rightVal = getRight().evaluate(assignment);
        return leftVal - rightVal;
    }

    /**
     * Assigns a new expression to a specified variable within the
     * subtraction expression.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Minus expression with the updated variable assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression newLeft = super.getLeft().assign(var, expression);
        Expression newRight = super.getRight().assign(var, expression);
        return new Minus(newLeft, newRight);
    }

    /**
     * Computes the derivative of the subtraction expression with respect to
     * a specified variable.
     * The derivative is calculated using the rule:
     * d/dx(f(x) - g(x)) = f'(x) - g'(x)
     *
     * @param var the variable to differentiate with respect to
     * @return the derivative of the subtraction expression with respect to the
     * specified variable
     */
    @Override
    public Expression differentiate(String var) {
        Expression differentiateLeft = super.getLeft().differentiate(var);
        Expression differentiateRight = super.getRight().differentiate(var);
        return new Minus(differentiateLeft, differentiateRight);
    }

    /**
     * Simplifies the subtraction expression by performing algebraic
     * simplifications where possible.
     *
     * @return a simplified version of the subtraction expression
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeft().simplify();
        Expression right = super.getRight().simplify();

        try {
            /* If the left expression is 0, the subtraction outputs
            the opposite of the right value */
            if (super.isEquals(left, 0.0)) {
                return new Neg(right);
            }

            /* If the value of the left expression is 0, the subtraction outputs
            the opposite of the right value */
            if (super.isEquals(right, 0.0)) {
                return left;
            }

            /* If the right and left expressions are the same,
            their subtraction equals 0 */
            if (right.toString().equals(left.toString())) {
                return new Num(0);
            }

            Minus simplifiedMinus = new Minus(left, right);
            int numVariables = simplifiedMinus.getVariables().size();
            /* If the expression has no variables at all, return the
            value of the expression by using the evaluate method */
            if (numVariables == 0) {
                return new Num(left.evaluate() - right.evaluate());
            }

        } catch (Exception except) {
            return new Minus(left, right);
        }
        return new Minus(left, right);
    }

}
