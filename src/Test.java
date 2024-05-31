import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Test.
 */
public class Test {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public  static  void main(String[] args) throws Exception {

        Expression e = new Sin(
                new Pow(
                        new Mult(
                                new Plus(
                                        new Mult(new Num(2), new Var("x")),
                                        new Var("y")),
                                new Num(4)),
                        new Var("x")));
        String s1 = e.toString();
        System.out.println(s1);
        Map<String, Double> assignment1 = new TreeMap<String, Double>();
        assignment1.put("x", 2.0);
        assignment1.put("y", 4.0);
        List<String> vars1 = e.getVariables();
        for (String v : vars1) {
            System.out.println(v);
        }
        double value1 = e.evaluate(assignment1);
        System.out.println("The result is: " + value1);

        Expression e2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        String s = e2.toString();
        System.out.println(s);
        List<String> vars = e2.getVariables();
        for (String v : vars) {
            System.out.println(v);
        }

        Expression e3 = e2.assign("y", e2);
        System.out.println(e3);
        // (x + ((x + y)^2))^2
        e3 = e3.assign("x", new Num(1));
        System.out.println(e3);
        // (1 + ((1 + y)^2))^2

        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 4.0);
        double value = e2.evaluate(assignment);
        System.out.println("The result is: " + value);
        Expression e7 = new Pow(new Var("x"), new Num(4));
        System.out.println(e7);
        Expression de11111 = (e7.differentiate("x")).simplify();
        System.out.println(de11111 + "   !!!!"); // we expect to see 4*(x^3)
// but seeing: ((x ^ 4.0) * ((1.0 * (4.0 / x)) + (0.0 * log(e, x))))
// is also fine, as it is equivalent (we will improve it in the next part).
        Expression e11 = new Log(new Var("x"), new Var("x"));
        Expression de11 = (e11.differentiate("x")).simplify();
        System.out.println("!!!   " + de11);
        System.out.println(" ");
        Expression e12 = new Log(new Plus(new Var("x"), new Num(2)), new Plus(new Num(2.0), new Var("x")));
        Expression de12 = e12.simplify();
        System.out.println(de12);
        System.out.println(" ");
        Expression e13 = new Div(new Pow(new Var("x"), new Num(3)), new Var("x"));
        Expression de13 = e13.simplify();
        System.out.println(de13);
        System.out.println(" ");
        Expression bus = new Minus(new Pow(new Var("x"), new Var("x")), new Num(2));
        Expression dbus = bus.differentiate("x").simplify();
        System.out.println(dbus);
        System.out.println(" ");
        Expression angry = new Pow(new Pow(new Var("x"), new Var("x")), new Num(2));
        Expression angryDriver = angry.differentiate("x").simplify();
        System.out.println(angryDriver + " niv");
        Expression n = new Log(new Var("x"), new Var("x"));
        Expression dn = (n.differentiate("x")).simplify();
//        Expression dn = n.simplify();
        System.out.println(dn + "   !!?!!");
        Expression angry2 = new Div(new Plus(new Pow(new Var("x"), new Num(2)), new Var("x")), new Var("x"));
        Expression angryDriver2 = angry2.differentiate("x").simplify();
        System.out.println(angryDriver2 + " niv");





//        Expression e1 = new Plus(new Var("x"), new Var("y"));
//        Expression e2 = new Plus(new Num(1), new Num(2));
//        String s1 = e1.toString();
//        System.out.println(s1);
//        Expression de11 = e1.differentiate("x").simplify();
//        System.out.println("dif: " + de11);
//        System.out.println(" ");
//        Map<String, Double> assignment = new TreeMap<String, Double>();
//        assignment.put("x", 2.0);
//        assignment.put("y", 4.0);
//        double value = e1.evaluate(assignment);
//        System.out.println("The result is: " + value);
//        String s2 = e2.toString();
//        System.out.println(s2);
//        Expression de2 = e2.differentiate("x");
//        System.out.println("dif: " + de2);
//        System.out.println(" ");
//        List<String> vars = e1.getVariables();
//        for (String v : vars) {
//            System.out.println(v);
//        }
//        Expression e3 = e1.assign("y", e2);
//        String s3 = e3.toString();
//        System.out.println(s3);
//        Expression de3 = e3.differentiate("x").simplify();
//        System.out.println("dif: " + de3);
//        System.out.println(" ");
//        Expression e4 = new Minus(new Var("x"), new Var("y"));
//        Expression e5 = new Minus(new Num(1), new Num(2));
//        String s4 = e4.toString();
//        System.out.println(s4);
//        Expression de4 = e4.differentiate("x");
//        System.out.println("dif: " + de4);
//        System.out.println(" ");
//        String s5 = e5.toString();
//        System.out.println(s5);
//        Expression de5 = e5.differentiate("x");
//        System.out.println("dif: " + de5);
//        System.out.println(" ");
//        Expression e6 = e2.assign("y", e5);
//        String s6 = e6.toString();
//        System.out.println(s6);
//        Expression de6 = e6.differentiate("x");
//        System.out.println("dif: " + de6);
//        System.out.println(" ");
//        Expression e7 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
//        String s7 = e7.toString();
//        System.out.println(s7);
//        Expression de7 = e7.differentiate("x");
//        System.out.println("dif: " + de7);
//        System.out.println(" ");
//        Expression e8 = new Cos(new Var("x"));
//        String s8 = e8.toString();
//        System.out.println(s8);
//        Expression de8 = e8.differentiate("x");
//        System.out.println("dif: " + de8);
//        System.out.println(" ");
//        Map<String, Double> ass = new TreeMap<String, Double>();
//        ass.put("x", 360.0);
//        double val = e8.evaluate(ass);
//        System.out.println("The result is: " + val);
//        Expression e9 = new Sin(
//                new Pow(
//                        new Mult(
//                                new Plus(
//                                        new Mult(new Num(2), new Var("x")),
//                                        new Var("y")),
//                                new Num(4)),
//                        new Var("x")));
//        String s9 = e9.toString();
//        System.out.println(s9);
//        Expression de9 = e9.differentiate("x");
//        System.out.println("dif: " + de9);
//        System.out.println(" ");
//        Expression e10 = new Neg(new Var("x"));
//        String s10 = e10.toString();
//        System.out.println(s10);
//        Expression de10 = e10.differentiate("x");
//        System.out.println("dif: " + de10);

    }

}