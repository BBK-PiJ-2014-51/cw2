public class FractionCalculatorTest{
	
	FractionCalculator calc = new FractionCalculator();

	public static void main(String[] args) {
		FractionCalculatorTest test = new FractionCalculatorTest();
		test.testExpressionString("1/3 + 1/3", new Fraction(2,3), "Test 01: Simple addition");
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