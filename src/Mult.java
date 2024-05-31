// 322624206 Daniel Talker

import java.util.Map;

/**
 * The Mult class represents a multiplication operation in an arithmetic
 * expression.
 * It extends the BinaryExpression class and implements the Expression interface.
 * The Mult class can be used to perform multiplication between two expressions
 * or between a double value and an expression.
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * Constructs a new Mult object with the specified left and right
     * expressions.
     *
     * @param left  the left expression operand
     * @param right the right expression operand
     */
    public Mult(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Constructs a new Mult object with the specified left double
     * value and right expression.
     *
     * @param left the left double value operand
     * @param right the right expression operand
     */
    public Mult(double left, double right) {
        super(left, right);
    }

    /**
     * Constructs a new Mult object with the specified left integer value
     * and right string value.
     *
     * @param left  the left integer value operand
     * @param right the right string value operand
     */
    public Mult(int left, String right) {
        super(left, right);
    }

    /**
     * Retrieves the symbol representing the multiplication operation.
     *
     * @return the symbol "*"
     */
    @Override
    public String getOperator() {
        return "*";
    }

    /**
     * Evaluates the multiplication expression and returns the result based
     * on the given variable assignments.
     *
     * @param assignment a map containing variable assignments, where the keys
     * are variable names and the values are their corresponding values
     * @return the result of multiplying the left expression value by the
     * right expression value
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftVal = getLeft().evaluate(assignment);
        double rightVal = getRight().evaluate(assignment);
        return leftVal * rightVal;
    }

    /**
     * Assigns a new expression to a specified variable within the
     * multiplication expression.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Mult expression with the updated variable assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression newLeft = super.getLeft().assign(var, expression);
        Expression newRight = super.getRight().assign(var, expression);
        return new Mult(newLeft, newRight);
    }

    /**
     * Computes the derivative of the multiplication expression with respect
     * to a specified variable.
     * The derivative is calculated using the rule:
     * d/dx(f(x) * g(x)) = f'(x) * g(x) + f(x) * g'(x)
     *
     * @param var the variable to differentiate with respect to
     * @return the derivative of the multiplication expression with respect
     * to the specified variable
     */
    @Override
    public Expression differentiate(String var) {
        Expression left = super.getLeft();
        Expression right = super.getRight();
        Expression differentiateLeft = super.getLeft().differentiate(var);
        Expression differentiateRight = super.getRight().differentiate(var);
        Mult newLeft = new Mult(differentiateLeft, right);
        Mult newRight = new Mult(left, differentiateRight);
        return new Plus(newLeft, newRight);
    }

    /**
     * Simplifies the multiplication expression by performing algebraic
     * simplifications where possible.
     *
     * @return a simplified version of the multiplication expression
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeft().simplify();
        Expression right = super.getRight().simplify();

        try {
            // If one of the expressions is 0, the product will also be 0
            if (super.isEquals(left, 0.0)
                    || super.isEquals(right, 0.0)) {
                return new Num(0);
            }

            /* If the left expression is equal to 1,
            the product will be equal to the right expression */
            if (super.isEquals(left, 1.0)) {
                return right;
            }

            /* If the right expression is equal to 1,
            the product will be equal to the left expression */
            if (super.isEquals(right, 1.0)) {
                return left;
            }

            /* If the right expression is equal to -1, the product will be
            minus the left expression */
            if (super.isEquals(right, -1.0)) {
                return new Neg(left);
            }

            /* If the left expression is equal to -1, the product will be
            minus the right expression */
            if (super.isEquals(left, -1.0)) {
                return new Neg(right);
            }

            int rightNumVars = right.getVariables().size();
            int leftNumVars = left.getVariables().size();

            /* If there are no variables at all in both expressions,
            return their product value */
            if (rightNumVars == 0 && leftNumVars == 0) {
                return new Num(left.evaluate() * right.evaluate());
            }

            // If there are no variables in both expressions
            if (rightNumVars != 0 && leftNumVars != 0) {
                return (new Mult(left, right));
            }

            // If only the right expression has no variables
            if (rightNumVars == 0) {
                return new Mult(left, new Num(right.evaluate()));
            }

            // If only the left expression has no variables
            if (leftNumVars == 0) {
                return new Mult(new Num(left.evaluate()), right);
            }

        } catch (Exception except) {
            return new Mult(left, right);
        }
        return new Mult(left, right);
    }
}
