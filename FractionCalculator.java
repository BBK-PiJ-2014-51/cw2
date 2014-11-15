public class FractionCalculator{
	private Fraction currentValue = new Fraction(0);
	private String currentOperator = null;

	public Fraction evaluate(Fraction fraction, String inputString) {
		if (!inputString.contains("\\s"));
		String[] tokens = inputString.split("\\s");		
		this.currentValue = fraction;
		for (int i = 0; i < tokens.length; i++){
			char firstChar = tokens[i].charAt(0);
			
			if (Character.isDigit(firstChar)){ // if token begins with a number it must be a fraction
				this.applyNextFraction(tokens[i]);
			//or if token begins with negative sign and has more than one char it must be a fraction 
			} else if(tokens[i].length() > 1 && firstChar == '-'){ 
				this.applyNextFraction(tokens[i]);
			} else if (Character.isLetter(firstChar)){ //commands begin with letters
				this.executeCommand(tokens[i].toLowerCase());
			} else if (isOperator(tokens[i])) { //at this point input should be an operator 
				this.currentOperator = tokens[i];
			} else { //print error otherwise 								
				System.out.println("ERROR: invalid token");
			}
		}
		return this.currentValue;
	}
	
	private void applyNextFraction(String token){
		Fraction nextFraction = this.parseFraction(token);
		if (this.currentOperator != null){ // if an operator is stored in memory already, we should calculate
			this.currentValue = this.calculate(nextFraction);
			this.currentOperator = null; //reset operator to null
		} else { // if instead no operator is stored in memory, set current value to the parsed fraction
			this.currentValue = nextFraction;
		}
	}
	
		
	private void executeCommand(String token) {
		char cmd = token.toLowerCase().charAt(0);
		
		if (cmd == 'a'){
			this.currentValue = currentValue.absValue();
		} else if (cmd == 'n'){
			this.currentValue = currentValue.negate();
		} else if (cmd == 'c'){
			this.reset();
		} else if (cmd == 'q'){
			this.quit(); //probably not what im going to do ultimately. 
		} else {
			
		}
	}

	private void quit() {
		
		
	}

	private void reset() {
		this.currentValue = new Fraction(0);
		this.currentOperator = null;
	}

	private boolean isOperator(String token) {
		switch(token.charAt(0)){
			case '+':
			case '-':
			case '*':
			case '/':
				return true;
			default:
				return false;
		}
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