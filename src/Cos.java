// 322624206 Daniel Talker

import java.util.Map;

/**
 * The class Cos represents a cosine mathematical expression.
 * It extends the UnaryExpression class and implements the Expression interface.
 * It computes the cosine of the operand expression.
 */
public class Cos extends UnaryExpression implements Expression {

    /**
     * Constructs a new Cos expression with the specified expression
     * as the operand.
     *
     * @param expression the operand expression
     */
    public Cos(Expression expression) {
        super(expression);
    }

    /**
     * Constructs a new Cos expression with the specified numerical operand.
     * The operand is automatically wrapped in a Num expression.
     *
     * @param number the numerical operand
     */
    public Cos(double number) {
        super(number);
    }

    /**
     * Creates a new Cos expression with the variable specified by 'var'
     * replaced by the provided expression.
     *
     * @param var        the variable to be replaced
     * @param expression the expression to replace the variable with
     * @return a new Cos expression with the variable replaced
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(super.getOperand().assign(var, expression));
    }

    /**
     * Evaluates the cosine of the expression using the variable values
     * provided in the assignment and returns the result.
     * If the expression contains a variable that is not in the assignment,
     * an exception is thrown.
     *
     * @param assignment the assignment of variable values
     * @return the result of evaluating the cosine expression
     * @throws Exception if a variable is not found in the assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double value = super.getExpression().evaluate(assignment);
        return Math.cos(Math.toRadians(value));
    }

    /**
     * Computes the derivative of the Cos expression with respect to the
     * variable 'var'.
     * The derivative is calculated using the chain rule:
     * d/dx(cos(u(x))) = -sin(u(x)) * u'(x),
     * where u(x) is the operand expression and u'(x) is its derivative with
     * respect to 'var'.
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return the derivative of the Cos expression
     */

    @Override
    public Expression differentiate(String var) {
        Expression internalDerivative = super.getOperand().differentiate(var);
        return new Neg(new Mult(new Sin(super.getOperand()), internalDerivative));
    }

    /**
     * Retrieves the symbol representing the cosine function.
     *
     * @return the symbol "cos"
     */
    public String getSymbol() {
        return "cos";
    }

    /**
     * Simplifies the Cos expression.
     * If the operand expression can be evaluated to a numerical value,
     * a new Num expression
     * with that value is returned. Otherwise, the operand expression is
     * simplified recursively.
     *
     * @return a simplified expression
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            return new Cos(super.getOperand().simplify());
        }
    }
}
