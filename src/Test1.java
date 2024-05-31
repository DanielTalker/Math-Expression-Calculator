/**
 * The type Test1.
 */
public class Test1 {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
//        Expression e7 = new Pow(new Var("x"), new Num(4));
//        System.out.println(e7);
//        Expression de11111 = (e7.differentiate("x"));
//        System.out.println(de11111 + "   !!!!"); // we expect to see 4*(x^3)
//// but seeing: ((x ^ 4.0) * ((1.0 * (4.0 / x)) + (0.0 * log(e, x))))
//// is also fine, as it is equivalent (we will improve it in the next part).
//
//        Expression zero1 = new Pow(new Num(0), new Num(0));
//        //String s = zero.toString();
//        System.out.println(zero1);
//        Expression zero = new Div(new Num(1), new Num(0));
//        //String s = zero.toString();
//        System.out.println(zero);
//        Expression log = new Log(new Var("x"), new Num(4));
//        System.out.println(log.differentiate("x").simplify());
        Expression pow = new Pow(new Var("x"), new Num(4));
        System.out.println(pow);
        System.out.println(pow.differentiate("x"));
        System.out.println(pow.differentiate("x").simplify());
        System.out.println(" ");
        Expression e = new Log(new Var("x"), new Var("x"));
        System.out.println(e);
        System.out.println(e.differentiate("x"));
        System.out.println(e.differentiate("x").simplify());
        System.out.println(" ");
        Expression e1 = new Minus(new Mult(new Num(2), new Var("x")), new Var("x"));
        System.out.println(e1);
        System.out.println(e1.differentiate("x"));
        System.out.println(e1.differentiate("x").simplify());
        System.out.println(" ");
        Expression e2 = new Pow(new Var("x"), new Var("x"));
        System.out.println(e2);
        System.out.println(e2.differentiate("x"));
        System.out.println(e2.differentiate("x").simplify());
        System.out.println(" ");
        e2 = new Div(new Var("x"), new Var("x"));
        System.out.println(e2);
        System.out.println(e2.differentiate("x"));
        System.out.println(e2.differentiate("x").simplify());
        System.out.println(" ");
        e2 = new Neg(new Div(new Var("x"), new Var("x")));
        System.out.println(e2);
        System.out.println(e2.differentiate("x"));
        System.out.println(e2.differentiate("x").simplify());
        System.out.println(" ");
        e2 = new Plus(new Pow(new Var("x"), new Num(2)), new Mult(new Num(3), new Var("x")));
        System.out.println(e2);
        System.out.println(e2.differentiate("x"));
        System.out.println(e2.differentiate("x").simplify());
        System.out.println(" ");
        e2 = new Plus(new Num(3), new Var("x"));
        System.out.println(e2);
        System.out.println(e2.simplify());
        System.out.println(e2.differentiate("x"));
        System.out.println(e2.differentiate("x").simplify());
    }
}
