import java.util.Scanner;

public class FractionCalculator{
	private Fraction currentValue = new Fraction(0);
	private String currentOperator = null;
	
	public static void main(String[] args) {
		FractionCalculator calc = new FractionCalculator();
		calc.loop();
	}
	
	public Fraction evaluate(Fraction fraction, String inputString) {
		inputString = inputString.toLowerCase();
		if (userQuits(inputString)){
			//remove potentially invalid input after user has requested exit
			inputString = this.trimInputAfterQuit(inputString);
		}
		String[] tokens = inputString.split("\\s");
		this.currentValue = fraction;
		if (isValidInput(inputString)){							
			for (int i = 0; i < tokens.length; i++){
				if (isOperator(tokens[i])) {
					this.currentOperator = tokens[i];
				}else if (isFraction(tokens[i])){ 
					this.applyNextFraction(tokens[i]);
				} else if (isCommand(tokens[i]) && !userQuits(tokens[i])){
					this.executeCommand(tokens[i]);
				} else if (userQuits(tokens[i])){
					System.out.println("Quitting the program. Returning final result.");
					break;
				}
			}
		}
		return this.currentValue;
	}
	
	private String trimInputAfterQuit(String inputString) {
		return inputString.substring(0, inputString.indexOf(" q") + 2);
	}

	private void loop() {
		Scanner scnr = new Scanner(System.in);
		String inputString = "";
		printWelcomeMsg();
		while (true){
			// for an end of input exception just print the word "Goodbye" and exit the program
			if (!scnr.hasNextLine()) {
				System.out.println("Goodbye");
				this.reset();
				break;
			} else{
				inputString = scnr.nextLine().toLowerCase();
			}
			
			if (userQuits(inputString)){
				//remove potentially invalid input after user has requested exit
				inputString = this.trimInputAfterQuit(inputString);
			}
			
			if (isValidInput(inputString)){
				evaluate(this.currentValue, inputString);
				System.out.println(currentValue.toString());
			}
			if (userQuits(inputString)) break;
		}
		scnr.close();
	}
	
	private void printWelcomeMsg() {
		System.out.println("Welcome to my fraction calculator for PiJ coursework 2.");
		System.out.println("By Caleb Clayton.");
	}

	
	/**
	 * Resets the calculator to initial state.
	 */
	private void reset() {
		System.out.println("Resetting calculator.");
		this.currentValue = new Fraction(0);
		this.currentOperator = null;
	}

	/**
	 * Performs arithmetic operation based on the value current in memory,
	 * the operator currently in memory, and the given Fraction.
	 * @param nextFraction
	 * @return
	 */
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
	
	/**
	 * Applies a fraction, given as a string, to the calculator.
	 * If an operator is in memory a calculation is performed via calculate(),
	 * the fraction returned from calculate is then stored in memory.
	 * If no operator is in memory then the given fraction is stored.
	 * @param token
	 */
	private void applyNextFraction(String token){
		Fraction nextFraction = this.parseFraction(token);
		if (this.currentOperator != null){ // if an operator is stored in memory already, we should calculate
			this.currentValue = this.calculate(nextFraction);
			this.currentOperator = null; //reset operator to null
		} else { // if instead no operator is stored in memory, set current value to the parsed fraction
			this.currentValue = nextFraction;
		}
	}
	
	/**
	 * Executes the appropriate command, which is given as a string, on the value stored in memory	
	 * @param token
	 */
	private void executeCommand(String token) {
		if (token.equals("a") || token.equals("abs")){
			this.currentValue = currentValue.absValue();
		} else if (token.equals("n")  || token.equals("neg")){
			this.currentValue = currentValue.negate();
		} else if (token.equals("c") || token.equals("clear")){
			this.reset();
		}
	}
	
	/**
	 * Splits a fraction passed as a string into a numerator and denominator
	 * and then instantiates a new Fraction object. 
	 * @param token
	 * @return
	 */
	private Fraction parseFraction(String token){
		if (token.contains("/")) {
			String[] fractionComponents = token.split("/");
			return new Fraction(Integer.parseInt(fractionComponents[0]),Integer.parseInt(fractionComponents[1]));
		} else {
			return new Fraction(Integer.parseInt(token));
		}
	}
	
	private boolean userQuits(String input){
		return (input.contains("q") || input.contains("Q") || input.contains("quit"));
	}

	/**
	 * Validates a fraction given as a string.
	 * Fractions are valid if they are non-empty strings, containing no more than two tokens that
	 * are delimited with a '/' and the tokens contain only numbers, with the exception of the first
	 * character which may indicate a negative number.
	 * @param token
	 * @return
	 */
	private boolean isFraction(String token) {
		if (token.length() == 0) return false;
		String[] fractionComponents = token.split("/");
		if (fractionComponents.length > 2) return false;
		//check that the fraction components contain only digits, with the exception of the first character, which may be a negative sign
		for (int i = 0; i < fractionComponents.length; i++){
			if (fractionComponents[i].charAt(0) == '-') fractionComponents[i] = fractionComponents[i].substring(1);
			for(int j = 0; j < fractionComponents[i].length(); j++){
				if(!Character.isDigit(fractionComponents[i].charAt(j))) return false;
			}
		}
		return true;
	}
	
	/**
	 * Validates command by comparing the given string against a list of valid commands.
	 * @param token
	 * @return
	 */
	private boolean isCommand(String token) {
		if (token.equals("a") || token.equals("abs") || token.equals("n") || token.equals("neg")
			|| token.equals("c") || token.equals("clear") || token.equals("q") || token.equals("quit")){
			return true;
		} else {
			return false;
		}		
	}
	
	/**
	 * Validates operator by comparing the given string against a list of valid commands.
	 * @param token
	 * @return
	 */
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
	
	/**
	 * Checks all tokens in a given input string for validity.
	 * A string is valid if all its tokens are valid.
	 * @param inputString
	 * @return boolean
	 */
	private boolean isValidInput(String inputString) {
		if (inputString.isEmpty()){
			System.out.println("Error: empty line.");
			this.reset();
			return false;
		}
		String[] tokens = inputString.split("\\s");	
		for (int i = 0; i < tokens.length; i++){
			if (i < tokens.length - 1){	
				if (isOperator(tokens[i]) && isOperator(tokens[i+1])){
					System.out.println("Error: you cannot supply two operators consecutively.");
					this.reset();
					return false;
				}
			}
			if (!isValidToken(tokens[i])){
				System.out.println("Error: invalid token " + tokens[i]);
				this.reset();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks a given token for validity.
	 * A token is valid if it is recognized as an operator, command, or fraction. 
	 * @param token
	 * @return
	 */
	private boolean isValidToken(String token){
		return (isFraction(token) || isOperator(token) || isCommand(token));
	}
}