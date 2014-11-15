public class FractionCalculator{
	private Fraction currentValue = new Fraction(0);
	private String currentOperator = null;

	public Fraction evaluate(Fraction fraction, String inputString) {
		if (!inputString.contains("\\s"));
		String[] tokens = inputString.split("\\s");		
		this.currentValue = fraction;
		for (int i = 0; i < tokens.length; i++){
			if (Character.isDigit(tokens[i].charAt(0))){ // if token begins with a number it must be a fraction
				Fraction nextFraction = this.parseFraction(tokens[i]);
				if (this.currentOperator != null){ // if an operator is stored in memory already, we should calculate
					this.currentValue = this.calculate(nextFraction);
					this.currentOperator = null; //reset operator to null
				} else { // if instead no operator is stored in memory, set current value to the parsed fraction
					this.currentValue = nextFraction;
				}
			} else { //if token does not begin with a digit, it must be an operator or command
				if (Character.isLetter(tokens[i].charAt(0))){ //commands begin with letters and are performed
					//this.processCommand(); //still needs implementing
				} else { //otherwise it must be an operator 
					this.currentOperator = tokens[i];
				}								
			}
		}
		return this.currentValue;
	}
	
	private Fraction calculate(Fraction nextFraction) {
		if(this.currentOperator.equals("+")){
			return currentValue.add(nextFraction);
		} else if(this.currentOperator.equals("-")){
			return currentValue.subtract(nextFraction);
		} else if(this.currentOperator.equals("*")){
			return currentValue.multiply(nextFraction);
		} else if(this.currentOperator.equals("/")){
			return currentValue.divide(nextFraction);
		} else{
			System.out.println("Invalid operator");
			return null;
		}
	}

	private Fraction parseFraction(String token){
		if (token.contains("/")) {
			String[] fractionComponents = token.split("/");
			return new Fraction(Integer.parseInt(fractionComponents[0]),Integer.parseInt(fractionComponents[1]));
		} else {
			return new Fraction(Integer.parseInt(token));
		}
	}

}