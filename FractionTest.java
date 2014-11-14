/**
 * Created by keith for the second coursework assignment.
 * extended by caleb clayton for cw2
 */
public class FractionTest {
    public static void main(String[] args) {

    // test divide by zero - should print an error and exit
    new Fraction(1, 0); 
    
    // test multiply
	Fraction f = new Fraction(3,10);
	Fraction g = new Fraction(1,2);
	Fraction h = new Fraction(3,5);
	if (!f.equals(g.multiply(h))) System.out.println("Multiply failed");
	
	Fraction a = new Fraction(-3,10);
	Fraction b = new Fraction(1,2);
	Fraction c = new Fraction(-3,5);
	if (!f.equals(g.multiply(h))) System.out.println("Multiply failed");
	
	a = new Fraction(3,10);
	b = new Fraction(-1,-2);
	c = new Fraction(3,5);
	if (!a.equals(b.multiply(c))) System.out.println("Multiply failed");
	
	a = new Fraction(-3,-10);
	b = new Fraction(-1,2);
	c = new Fraction(3,-5);
	if (!a.equals(b.multiply(c))) System.out.println("Multiply failed");
	
	// test equals
	test(new Fraction(1, 2),new Fraction(1, 2),"error test 1");
	test(new Fraction(1, 2),new Fraction(3, 6),"error test 2");
	test(new Fraction(-1, 2),new Fraction(1, -2),"error test 3");
	test(new Fraction(-1, -2),new Fraction(1, 2),"error test 4");
	test(new Fraction(4, -8),new Fraction(1, 2),"error test 5 (should fail)"); //should fail

    // test division
	a = new Fraction(3,1);
	b = new Fraction(1,2);
	c = new Fraction(1,6);
	if (!a.equals(b.divide(c))) System.out.println("Division test 1 failed");

	a = new Fraction(3);
	b = new Fraction(-1,2);
	c = new Fraction(1,-6);
	if (!a.equals(b.divide(c))) System.out.println("Division test 2 failed");
	
	a = new Fraction(5,4);
	b = new Fraction(5,6);
	c = new Fraction(2,3);
	if (!a.equals(b.divide(c))) System.out.println("Division test 3 failed");
	
	// test addition
	a = new Fraction(5,6);
	b = new Fraction(1,2);
	c = new Fraction(1,3);
	if (!a.equals(b.add(c))) System.out.println("Addition test 1 failed");
	
	a = new Fraction(1,2);
	b = new Fraction(1,6);
	c = new Fraction(1,3);
	if (!a.equals(b.add(c))) System.out.println("Addition test 2 failed");
	
	// test subtraction
	a = new Fraction(1,6);
	b = new Fraction(1,2);
	c = new Fraction(1,3);
	if (!a.equals(b.subtract(c))) System.out.println("Subtraction test 1 failed");
	
	a = new Fraction(5,8);
	b = new Fraction(3,4);
	c = new Fraction(1,8);
	if (!a.equals(b.subtract(c))) System.out.println("Subtraction test 2 failed");
	
	// test absolute value
	a = new Fraction(5,8);
	b = new Fraction(5,-8);
	if (!a.equals(b.absValue())) System.out.println("Absolute value test 1 failed");
	b = new Fraction(-5,8);
	if (!a.equals(b.absValue())) System.out.println("Absolute value test 2 failed");
	
	
	// test negate
	a = new Fraction(5,8);
	b = new Fraction(5,-8);
	if (!a.equals(b.negate())) System.out.println("Negate test 1 failed");
	a = new Fraction(-5,8);
	b = new Fraction(-5,-8);
	if (!a.equals(b.negate())) System.out.println("Negate test 2 failed");
	
	
    }

    static void test(Fraction f1, Fraction f2, String msg){
    	if (! f1.equals(f2))
		System.out.println(msg);
    }
}
