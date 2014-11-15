public class FractionCalculatorTest{
	
	FractionCalculator calc = new FractionCalculator();

	public static void main(String[] args) {
		FractionCalculatorTest test = new FractionCalculatorTest();
		//basic two operand tests
		test.testExpressionString("1/3 + 1/3", new Fraction(2,3), "Test 01: Simple addition");
		test.testExpressionString("1/2 - 1/4", new Fraction(1,4), "Test 02: Simple subtraction");
		test.testExpressionString("1/2 * 1/2", new Fraction(1,4), "Test 03: Simple multiplication");
		test.testExpressionString("1/4 / 1/3", new Fraction(3,4), "Test 04: Simple division");
		//basic tests with commands
		test.testExpressionString("1/3 - 2/3 N", new Fraction(1,3), "Test 05: Negate Function");
		test.testExpressionString("1/2 - 1/4 c", new Fraction(0,1), "Test 06: Clear Function");
		test.testExpressionString("1/2 * -1/2 abs", new Fraction(1,4), "Test 07: Absolute Value Function");
		//test.testExpressionString("1/4 / 1/3 q", null, "Test 08: Quit"); //test quit?
	
	}
	
	/* tests expressions through a FractionCalculator's evaluate method */
	private void testExpressionString(String stringInput, Fraction expectedAnswer, String testId ){
		Fraction answer = calc.evaluate(new Fraction(0), stringInput);
		if (! answer.equals(expectedAnswer)){
			System.out.println(testId + " FAILED: " + stringInput + " evaluated to: " + answer.toString() + ". Expected answer: " + expectedAnswer.toString());  	
		} else {
			System.out.println(testId + " Passed.");
		}
	}
	
}