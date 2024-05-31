// 322624206 Daniel Talker

import java.util.Map;

/**
 * The Div class represents the division operation in an expression.
 * It is a subclass of the BinaryExpression class and implements the
 * Expression interface.
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * Constructs a new Div object with the specified left and right expressions.
     *
     * @param left  the left expression in the division operation
     * @param right the right expression in the division operation
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Constructs a new Div object with the specified left and right numbers.
     *
     * @param left  the left number in the division operation
     * @param right the right number in the division operation
     */
    public Div(double left, double right) {
        super(left, right);
    }

    /**
     * Gets the symbol representing the division operation.
     *
     * @return the symbol representing the division operation ("/")
     */
    @Override
    public String getOperator() {
        return "/";
    }

    /**
     * Evaluates the division expression by substituting variable values from
     * the given assignment.
     *
     * @param assignment the map of variable-value assignments
     * @return the result of evaluating the division expression
     * @throws Exception if a variable in the expression is not present in
     * the assignment or if division by zero occurs
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftVal = getLeft().evaluate(assignment);
        double rightVal = getRight().evaluate(assignment);

        /* If the denominator is equal to 0, an error must be thrown because
        dividing by 0 is not allowed */
        if (rightVal == 0.0) {
            throw new RuntimeException("there was a division by zero :" + this);
        }

        return leftVal / rightVal;
    }

    /**
     * Assigns a new expression to the specified variable within the
     * division expression.
     *
     * @param var the variable to be assigned
     * @param expression the new expression to assign to the variable
     * @return a new division expression with the updated variable assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression newLeft = super.getLeft().assign(var, expression);
        Expression newRight = super.getRight().assign(var, expression);
        return new Div(newLeft, newRight);
    }

    /**
     * Computes the derivative of the division expression with respect to the
     * specified variable.
     * The derivative is calculated using the chain rule:
     * d/dx(f(x) / g(x)) = (f'(x)*g(x) - f(x)*g'(x)) / g(x)^2.
     * where f(x) and g(x) is the operand expression,
     * and f'(x) and g'(x) is its derivative with respect to 'var'.
     *
     * @param var the variable to differentiate with respect to.
     * @return the derivative of the division expression.
     */
    @Override
    public Expression differentiate(String var) {
        Expression left = super.getLeft();
        Expression right = super.getRight();

        // Calculate the derivative of the numerator and denominator separately
        Expression differentiateLeft = left.differentiate(var);
        Expression differentiateRight = right.differentiate(var);

        Mult newRight = new Mult(left, differentiateRight);
        Mult newLeft = new Mult(differentiateLeft, right);
        Minus numerator = new Minus(newLeft, newRight);
        Expression two = new Num(2);
        Pow denominator = new Pow(right, two);
        return new Div(numerator, denominator);
    }

    /**
     * Simplifies the division expression.
     *
     * @return a simplified version of the division expression.
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeft().simplify();
        Expression right = super.getRight().simplify();
        try {
            /* If the numerator of the fraction is equal to 0, the value
            of the expression is 0 */
            if (super.isEquals(left, 0.0)) {
                return new Num(0.0);
            }

            /* If the denominator of the fraction is equal to 1, the value
            of the expression is the numerator */
            if (super.isEquals(right, 1.0)) {
                return left;
            }

            /* If the denominator is equal to 0, we will reverse the sign of
            the numerator and return it */
            if (super.isEquals(right, -1.0)) {
                return new Neg(left);
            }

            /* If the numerator and denominator of the fraction are the same,
            the value of the entire fraction is 1 */
            if (right.toString().equals(left.toString())) {
                return new Num(1);
            }

            Div simplifiedDiv = new Div(left, right);
            int numVariables = simplifiedDiv.getVariables().size();

            /* If the expression has no variables at all, return the
            value of the expression by using the evaluate method */
            if (numVariables == 0) {
                return new Num(simplifiedDiv.evaluate());
            }
        } catch (Exception except) {
            return new Div(left, right);
        }
        return new Div(left, right);
    }

}
