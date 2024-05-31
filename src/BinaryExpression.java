import java.util.ArrayList;
import java.util.List;

/**
 * The abstract class BinaryExpression serves as a base class for binary
 * mathematical expressions.
 * It extends the BaseExpression class and provides common functionality for
 * expressions with two operands.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression left;
    private Expression right;

    /**
     * Constructs a new BinaryExpression with the specified left and
     * right expressions.
     *
     * @param left  the left expression
     * @param right the right expression
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Constructs a new BinaryExpression with the specified left and
     * right numerical values.
     * The values are automatically wrapped in Num expressions.
     *
     * @param left  the left numerical value
     * @param right the right numerical value
     */
    public BinaryExpression(double left, double right) {
        this.left = new Num(left);
        this.right = new Num(right);
    }

    /**
     * Constructs a new BinaryExpression with the specified left numerical
     * value and right variable name.
     * The left value is wrapped in a Num expression, and the right variable
     * is wrapped in a Var expression.
     *
     * @param left  the left numerical value
     * @param right the right variable name
     */
    public BinaryExpression(int left, String right) {
        this.left = new Num(left);
        this.right = new Var(right);
    }

    /**
     * Constructs a new BinaryExpression with the specified left and right
     * variable names.
     * The variable names are wrapped in Var expressions.
     *
     * @param left  the left variable name
     * @param right the right variable name
     */
    public BinaryExpression(String left, String right) {
        this.left = new Var(left);
        this.right = new Var(right);
    }

    /**
     * Retrieves the left expression of the binary expression.
     *
     * @return the left expression
     */
    public Expression getLeft() {
        return this.left;
    }

    /**
     * Retrieves the right expression of the binary expression.
     *
     * @return the right expression
     */
    public Expression getRight() {
        return this.right;
    }

    /**
     * Retrieves the variables present in the binary expression.
     * This method combines the variables from both the left and right
     * expressions.
     *
     * @return a list of variables present in the binary expression
     */
    @Override
    public List<String> getVariables() {
        // Initialize lists for two expressions variables
        List<String> vars1 = new ArrayList<>(left.getVariables());
        List<String> vars2 = new ArrayList<>(right.getVariables());

        /* As long as the variable from the second list does not already exist
        in the first, we will insert it for the first time in order to unite
        the lists */
        for (String s : vars2) {
            if (!(vars1.contains(s))) {
                vars1.add(s);
            }
        }
        return vars1;
    }

    /**
     * Returns a string representation of the binary expression.
     * The expression is enclosed in parentheses and consists of the left
     * expression, the operator specific to each subclass,
     * and the right expression.
     *
     * @return a string representation of the binary expression
     */
    @Override
    public String toString() {
        String leftString = this.left.toString();
        String rightString = this.right.toString();
        return "(" + leftString + " " + getOperator() + " " + rightString + ")";
    }

    /**
     * Checks if the provided expression is equal to the specified
     * numerical value.
     *
     * @param exp the expression to compare
     * @param num the numerical value to compare against
     * @return true if the expression is equal to the numerical value,
     * false otherwise
     */
    public boolean isEquals(Expression exp, double num) {
        String str = Double.toString(num);
        return exp.toString().equals(str);
    }

    /**
     * Retrieves the operator symbol specific to each subclass
     * of BinaryExpression.
     *
     * @return the operator symbol as a string
     */

    public abstract String getOperator();
}
