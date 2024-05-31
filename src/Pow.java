// 322624206 Daniel Talker

import java.util.Map;

/**
 * The Pow class represents a power operation in an arithmetic expression.
 * It extends the BinaryExpression class and implements the Expression interface.
 * The Pow class can be used to raise an expression or a double value
 * to a specified power.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Constructs a new Pow object with the specified base and exponent
     * expressions.
     *
     * @param left the base expression
     * @param right the exponent expression
     */
    public Pow(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Constructs a new Pow object with the specified base double
     * value and exponent double value.
     *
     * @param left     the base double value
     * @param right the exponent double value
     */
    public Pow(double left, double right) {
        super(left, right);
    }

    /**
     * Constructs a new Pow object with the specified base string and
     * exponent string.
     *
     * @param left     the base string
     * @param right the exponent string
     */
    public Pow(String left, String right) {
        super(left, right);
    }

    /**
     * Retrieves the symbol representing the power operation.
     *
     * @return the symbol "^"
     */
    @Override
    public String getOperator() {
        return "^";
    }

    /**
     * Returns a string representation of the power expression in the
     * form "(base^exponent)".
     *
     * @return the string representation of the power expression
     */
    @Override
    public String toString() {
        Expression left = super.getLeft();
        Expression right = super.getRight();
        String leftS = left.toString();
        String rightS = right.toString();

        return "(" + leftS + getOperator() + rightS + ")";
    }

    /**
     * Evaluates the power expression and returns the result based on the
     * given variable assignments.
     * Handles special cases where the base is zero or negative and the
     * exponent is not a positive integer.
     *
     * @param assignment a map containing variable assignments, where the keys
     * are variable names and the values are their corresponding values
     * @return the result of raising the base expression to the
     * exponent expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Expression leftVal = super.getLeft();
        Expression rightVal = super.getRight();

        if ((leftVal.evaluate(assignment) < 0.0
                && (rightVal.evaluate(assignment) > 0.0
                && rightVal.evaluate(assignment) < 1.0))) {
            throw new Exception("there is no that power for the number");
        }

        if (leftVal.toString().equals("0.0")
                && rightVal.toString().equals("0.0")) {
            throw new RuntimeException("There is no power of 0^0");
        }
        if (leftVal.evaluate(assignment) == 0.0
                && rightVal.evaluate(assignment) == 0.0) {
            throw new Exception("There is no power of 0^0");
        }

        double leftVal1 = getLeft().evaluate(assignment);
        double rightVal1 = getRight().evaluate(assignment);
        return Math.pow(leftVal1, rightVal1);
    }

    /**
     * Assigns a new expression to a specified variable within the
     * power expression.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Pow expression with the updated variable assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression newBase = super.getLeft().assign(var, expression);
        Expression newPower = super.getRight().assign(var, expression);
        return new Pow(newBase, newPower);
    }

    /**
     * Computes the derivative of the power expression with respect
     * to the specified variable.
     * The derivative is calculated using the rule:
     * d/dx(f(x) ^ g(x)) =
     * (f(x) ^ g(x)) * (f'(x) * (f(x) / g(x)) + g'(x) * log(e, f(x)))
     *
     * @param var the variable to differentiate with respect to
     * @return the derivative of the power expression
     */
    @Override
    public Expression differentiate(String var) {
        Expression left = super.getLeft();
        Expression right = super.getRight();

        Expression leftDiff = left.differentiate(var);
        Expression rightDiff = right.differentiate(var);

        Expression e = new Var("e");

        Expression firstTerm = new Pow(left, right);
        Expression secondTerm = new Plus(new Mult(leftDiff,
                new Div(right, left)), new Mult(rightDiff, new Log(e, left)));
        return new Mult(firstTerm, secondTerm);
    }

    /**
     * Simplifies the power expression by performing constant evaluation
     * and simplification rules.
     *
     * @return a simplified version of the power expression
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeft().simplify();
        Expression right = super.getRight().simplify();
        try {

            // If the base is equal to 0, the value of the expression is 0
            if (super.isEquals(left, 0.0)) {
                return new Num(0);
            }

            /* If the base equals 1, or the exponent equals 0,
            the value of the expression is 1 */
            if (super.isEquals(left, 1.0)
                    || super.isEquals(right, 0.0)) {
                return new Num(1);
            }

            /* If the exponent is equal to 1, the value of the expression
            is the left expression */
            if (super.isEquals(right, 1.0)) {
                return left;
            }

            Pow simplifiedPow = new Pow(left, right);
            int numVariables = simplifiedPow.getVariables().size();
            /* If the expression has no variables at all,
            return the value of the expression */
            if (numVariables == 0) {
                return new Num(simplifiedPow.evaluate());
            }

            } catch (Exception except) {
                return new Pow(left, right);
            }
            return new Pow(left, right);
    }

}
