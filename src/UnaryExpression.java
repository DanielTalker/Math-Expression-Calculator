import java.util.List;

/**
 * The abstract class UnaryExpression serves as a base class for unary
 * mathematical expressions.
 * It extends the BaseExpression class and provides common functionality for
 * expressions with a single operand.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression operand;

    /**
     * Constructs a new UnaryExpression with the specified operand expression.
     *
     * @param operand the operand expression
     */
    public UnaryExpression(Expression operand) {
        this.operand = operand;
    }

    /**
     * Constructs a new UnaryExpression with the specified numerical operand.
     * The operand is automatically wrapped in a Num expression.
     *
     * @param number the numerical operand
     */
    public UnaryExpression(int number) {
        this.operand = new Num(number);
    }

    /**
     * Constructs a new UnaryExpression with the specified numerical operand.
     * The operand is automatically wrapped in a Num expression.
     *
     * @param number the numerical operand
     */
    public UnaryExpression(Double number) {
        this.operand = new Num(number);
    }

    /**
     * Constructs a new UnaryExpression with the specified variable operand.
     * The operand is wrapped in a Var expression.
     *
     * @param number the variable operand
     */
    public UnaryExpression(String number) {
        this.operand = new Var(number);
    }

    /**
     * Retrieves the operand of the unary expression.
     *
     * @return the operand expression
     */
    public Expression getOperand() {
        return this.operand;
    }

    /**
     * Retrieves the variables present in the unary expression.
     *
     * @return a list of variables present in the unary expression
     */
    @Override
    public List<String> getVariables() {
        return getOperand().getVariables();
    }

    /**
     * Returns a string representation of the unary expression.
     * The expression consists of the symbol specific to each subclass and
     * the operand expression enclosed in parentheses.
     *
     * @return a string representation of the unary expression
     */
    @Override
    public String toString() {
        return getSymbol() + "(" + operand.toString() + ")";
    }

    /**
     * Retrieves the symbol specific to each subclass of UnaryExpression.
     *
     * @return the symbol as a string
     */
    public abstract String getSymbol();

    /**
     * Retrieves the expression contained within the unary expression.
     *
     * @return the operand expression
     */
    public Expression getExpression() {
        return this.operand;
    }
}
