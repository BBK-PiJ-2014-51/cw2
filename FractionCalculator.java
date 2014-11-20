public class FractionCalculator{
	private Fraction currentValue = new Fraction(0);
	private String currentOperator = null;

	
	public static void main(String[] args) {
		FractionCalculator calc = new FractionCalculator();
		calc.printWelcomeMsg();
		calc.loop();
		calc.quit();
	}
	
	private void loop() {
		boolean validInput = true;
		while (validInput){
			 String inputString = System.console().readLine();
			if (isValidInput(inputString)){
				evaluate(this.currentValue, inputString); 
			} else{
				break;
			}
		}
	}

	private boolean isValidInput(String inputString) {
		// TODO Implement
		return false;
	}

	private void printWelcomeMsg() {
		System.out.println("Welcome to my fraction calculator for PiJ coursework 2.");
		System.out.println("By Caleb Clayton.");
	}
	
	private void quit() {
		// TODO Implement	
	}

	public Fraction evaluate(Fraction fraction, String inputString) {
		if (!inputString.contains("\\s"));
		String[] tokens = inputString.split("\\s");		
		this.currentValue = fraction;
		for (int i = 0; i < tokens.length; i++){
			char firstChar = tokens[i].charAt(0);
			
			if (Character.isDigit(firstChar)){ // if token begins with a number it must be a fraction
				this.applyNextFraction(tokens[i]);
			//if token begins with negative sign and has more than one char it must be a fraction 
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
		if (token.equals("a") || token.equals("abs")){
			this.currentValue = currentValue.absValue();
		} else if (token.equals("n")  || token.equals("neg")){
			this.currentValue = currentValue.negate();
		} else if (token.equals("c") || token.equals("clear")){
			this.reset();
		} else if (token.equals("q") || token.equals("quit")){
			this.quit(); //probably not what im going to do ultimately. 
		} else {
			
		}
	}

	private void reset() {
		this.currentValue = new Fraction(0);
		this.currentOperator = null;
	}

	private boolean isFraction(String token) {
		
		return true;
	}
	
	private boolean isCommand(String token) {
		
		return true;
	}
	
	private boolean isOperator(String token) {
		if(token.length() != 1) return false; //operators are always a single char
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