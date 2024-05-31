import java.util.Map;
import java.util.TreeMap;

/**
 * The ExpressionsTest class is a test class that demonstrates the usage of
 * expressions in Java.
 * It evaluates and differentiates mathematical expressions using a tree-like
 * structure.
 */
public class ExpressionsTest {
    /**
     * The main method serves as the entry point of the application.
     *
     * @param args the command-line arguments
     * @throws Exception if an error occurs during execution
     */
    public static void main(String[] args) throws Exception {
        // Create an expression representing the mathematical expression:
        // ((2 * x) + (sin(4 * y))) + (e ^ x)
        Expression e = new Plus(new Plus(new Mult(new Num(2),
                new Var("x")),
                new Sin(new Mult(new Num(4), new Var("y")))),
                new Pow(new Var("e"), new Var("x")));

        // Convert the expression to a string representation
        String s1 = e.toString();
        System.out.println(s1);
        // Create a map of variable assignments
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);

        // Evaluate the expression with the variable assignments
        System.out.println(e.evaluate(assignment));

        // Differentiate the expression with respect to 'x'
        Expression eDiff = e.differentiate("x");
        String s2 = eDiff.toString();
        System.out.println(s2);

        // Evaluate the differentiated expression with the variable assignments
        System.out.println(e.differentiate("x").evaluate(assignment));

        // Simplify the differentiated expression
        System.out.println(e.differentiate("x").simplify());
    }
}
