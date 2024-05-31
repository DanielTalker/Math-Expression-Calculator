// 322624206 Daniel Talker

import java.util.Map;

/**
 * The Plus class represents an addition operation in an arithmetic expression.
 * It extends the BinaryExpression class and implements the Expression
 * interface.
 * The Plus class can be used to perform addition between two expressions or
 * between a double value and an expression.
 */
public class Plus extends BinaryExpression implements Expression {

    /**
     * Constructs a new Plus object with the specified left and right
     * expressions.
     *
     * @param left  the left expression operand
     * @param right the right expression operand
     */
    public Plus(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Constructs a new Plus object with the specified left double
     * value and right double value.
     *
     * @param left  the left double value operand
     * @param right the right double value operand
     */
    public Plus(double left, double right) {
        super(left, right);
    }

    /**
     * Assigns a new expression to a specified variable within
     * the addition expression.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Plus expression with the updated variable assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression newLeft = super.getLeft().assign(var, expression);
        Expression newRight = super.getRight().assign(var, expression);
        return new Plus(newLeft, newRight);
    }

    /**
     * Retrieves the symbol representing the addition operation.
     *
     * @return the symbol "+"
     */
    @Override
    public String getOperator() {
        return "+";
    }

    /**
     * Evaluates the addition expression and returns the result based on the
     * given variable assignments.
     *
     * @param assignment a map containing variable assignments, where the
     * keys are variable names and the values are their corresponding values
     * @return the result of adding the left expression value to the right
     * expression value
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftVal = getLeft().evaluate(assignment);
        double rightVal = getRight().evaluate(assignment);
        return leftVal + rightVal;
    }

    /**
     * Computes the derivative of the addition expression with respect to
     * a specified variable.
     * The derivative is calculated using the rule:
     * d/dx(f(x) + g(x)) = f'(x) + g'(x)
     *
     * @param var the variable to differentiate with respect to
     * @return the derivative of the addition expression with respect
     * to the specified variable
     */
    @Override
    public Expression differentiate(String var) {
        Expression differentiateLeft = super.getLeft().differentiate(var);
        Expression differentiateRight = super.getRight().differentiate(var);
        return new Plus(differentiateLeft, differentiateRight);
    }

    /**
     * Simplifies the addition expression by performing algebraic
     * simplifications where possible.
     *
     * @return a simplified version of the addition expression
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeft().simplify();
        Expression right = super.getRight().simplify();
        try {

            // If the left expression is 0, return the right expression
            if (super.isEquals(left, 0.0)) {
                return right;
            }

            // If the right expression is 0, return the left expression
            if (super.isEquals(right, 0.0)) {
                return left;
            }

            int rightNumVars = right.getVariables().size();
            int leftNumVars = left.getVariables().size();

            /* If the expression has no variables at all,
            return the value of the expression */
            if (rightNumVars == 0 && leftNumVars == 0) {
                Plus p = new Plus(left, right);
                return new Num(p.evaluate());
            }

            // If only the right expression has no variables
            if ((rightNumVars == 0 && leftNumVars != 0)) {
                return new Plus(left, new Num(right.evaluate()));
            }

            // If only the left expression has no variables
            if ((rightNumVars != 0 && leftNumVars == 0)) {
                return new Plus(new Num(left.evaluate()), right);
            } else {
                return new Plus(left, right);
            }

        } catch (Exception except) {
            return new Plus(left, right);
        }
    }

}