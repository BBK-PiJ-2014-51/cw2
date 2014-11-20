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
		String[] tokens = inputString.split("\\s");	
		for (int i = 0; i < tokens.length; i++){
			if (!isValidToken(tokens[i])) return false;
		}
		return true;
	}
	
	private boolean isValidToken(String token){
		return (isFraction(token) || isOperator(token) || isCommand(token));
	}

	private void printWelcomeMsg() {
		System.out.println("Welcome to my fraction calculator for PiJ coursework 2.");
		System.out.println("By Caleb Clayton.");
	}
	
	private void quit() {
		// TODO Implement	
	}

	public Fraction evaluate(Fraction fraction, String inputString) {
		//if (!inputString.contains("\\s"));
		inputString = inputString.toLowerCase();
		String[] tokens = inputString.split("\\s");
		this.currentValue = fraction;
		for (int i = 0; i < tokens.length; i++){
			if (isOperator(tokens[i])) { //at this point input should be an operator 
				this.currentOperator = tokens[i];
			}else if (isFraction(tokens[i])){ 
				this.applyNextFraction(tokens[i]);
			} else if (isCommand(tokens[i])){ //commands begin with letters
				this.executeCommand(tokens[i]);
			}  else { //print error otherwise 								
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
		String[] fractionComponents = token.split("/");
		if (fractionComponents.length > 2) return false;
		//check that the fraction components contain only digits, with the exception of the first character,
		//which may be a negative sign
		for (int i = 0; i < fractionComponents.length; i++){
			if (fractionComponents[i].charAt(0) == '-') fractionComponents[i] = fractionComponents[i].substring(1);
			for(int j = 0; j < fractionComponents[i].length(); j++){
				if(!Character.isDigit(fractionComponents[i].charAt(j)))return false;
			}
		}
		return true;
	}
	
	private boolean isCommand(String token) {
		if (token.equals("a") || token.equals("abs") || token.equals("n") || token.equals("neg")
			|| token.equals("c") || token.equals("clear") || token.equals("q") || token.equals("quit")){
			return true;
		} else {
			return false;
		}		
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