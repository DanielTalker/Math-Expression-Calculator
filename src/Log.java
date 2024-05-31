
import java.util.Map;

/**
 * The Log class represents a logarithmic expression in the form of
 * log(base, data).
 * It extends the BinaryExpression class and implements the Expression
 * interface.
 */
public class Log extends BinaryExpression  implements Expression {

    /**
     * Instantiates a new Log.
     *
     * @param left the left expression representing the logarithm base
     * @param right the right expression representing the logarithm data
     */
    public Log(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Instantiates a new Log.
     *
     * @param left the left double representing the logarithm base
     * @param right the right double representing the logarithm data
     *
     */
    public Log(double left, double right) {
        super(left, right);
    }

    /**
     * Gets the symbol for the logarithm operator.
     *
     * @return the symbol for logarithm ("log")
     */
    @Override
    public String getOperator() {
        return "log";
    }

    /**
     * Returns the string representation of the logarithmic expression.
     *
     * @return the string representation of the logarithm in the format:
     * "log(base, data)"
     */
    @Override
    public String toString() {
        Expression left = super.getLeft();
        Expression right = super.getRight();
        String leftS = left.toString();
        String rightS = right.toString();
        return "log(" + leftS + ", " + rightS + ")";
    }

    /**
     * Evaluates the logarithmic expression with the provided variable
     * assignments.
     *
     * @param assignment the variable assignments as a map
     * @return the result of evaluating the logarithm expression
     * @throws Exception if the logarithm base is not possible
     * (base <= 0 or base = 1),
     * or the logarithm data is non-positive (data <= 0)
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double base = getLeft().evaluate(assignment);
        double data = getRight().evaluate(assignment);

        // throws Exception if the logarithm is not possible
        if ((base <= 0.0) || (base == 1.0) || (data  <= 0)) {
            throw new Exception("Undefined expression");
        }

        return  Math.log(data) / Math.log(base);
    }

    /**
     * Assigns a new expression to the specified variable in the logarithmic
     * expression.
     *
     * @param var the variable to be replaced
     * @param expression the new expression to be assigned
     * @return a new logarithmic expression with the updated variable assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression newLeft = super.getLeft().assign(var, expression);
        Expression newRight = super.getRight().assign(var, expression);
        return new Log(newLeft, newRight);
    }

    /**
     * Computes the derivative of the logarithmic expression with respect
     * to the specified variable.
     * The derivative is calculated using the chain rule:
     * If the variable x is the base and data (or only base) of the
     * logarithm, we have:
     * d/dxLog(g(x), f(x)) = (log(e, f(x)) / log(e, g(x)))'.
     * If the variable x is the data of the logarithm, we have:
     * d/dxLog(y, f(x)) = f'(x) / f(x)*log(e, y)
     * where f(x) and g(x) is the operand expression,
     * and f'(x) and g'(x) is its derivative with respect to 'var'.
     *
     * @param var the variable to differentiate with respect to
     * @return the derivative of the logarithmic expression with respect to
     * the variable
     */
    @Override
    public Expression differentiate(String var) {
            Expression left = super.getLeft();
            Expression right = super.getRight();
            Var e = new Var("e");

            // If the variable is the base of the log
            if (left.getVariables().contains(var)) {
                Log numerator = new Log(e, right);
                Log denominator = new Log(e, left);
                return new Div(numerator, denominator).differentiate(var);
            }
            // If the variable is the data of the log
            Mult denominator = new Mult(right, new Log(e, super.getLeft()));
            return new Div(right.differentiate(var), denominator);
    }

    /**
     * Simplifies the logarithm expression.
     *
     * @return a simplified version of the logarithm expression.
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeft().simplify();
        Expression right = super.getRight().simplify();
        try {

            /* If the log base and the log content are the same,
            the value of the whole expression is 1 */
            if (right.toString().equals(left.toString())) {
                return new Num(1);
            }

            // If the log content is 1, the value of the entire expression is 0,
            // because: x^0 = 1
            if (super.isEquals(right, 1.0)) {
                return new Num(0);
            }

            Log simplifiedLog = new Log(left, right);
            int numVariables = simplifiedLog.getVariables().size();
            /* If the expression has no variables at all, return the
            value of the expression by using the evaluate method */
            if (numVariables == 0) {
                return new Num(simplifiedLog.evaluate());
            }

        } catch (Exception except) {
            return new Log(left, right);
        }
        return new Log(left, right);
    }
}
