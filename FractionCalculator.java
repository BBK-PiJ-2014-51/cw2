public class FractionCalculator{
	private Fraction currentValue = new Fraction(0);
	private String currentOperator = null;

	public Fraction evaluate(Fraction fraction, String inputString) {
		if (!inputString.contains("\\s"));
		String[] tokens = inputString.split("\\s");		
		this.currentValue = fraction;
		Fraction result = null;
		for (int i = 0; i < tokens.length; i++){
			if (Character.isDigit(tokens[i].charAt(0))){ // if token begins with a number it must be a fraction
				if (this.currentOperator != null){ // if an operator is stored in memory already, we should calculate
					
					
					
					this.currentOperator = null; //reset operator to null
				} else { // if no operator stored in memory, set current value to the parsed fraction
					
					
					
				}
			} else { //if token does not begin with a digit, it must be an operator or command
				
				
				
			}
		}
		return result;
	}
	

}