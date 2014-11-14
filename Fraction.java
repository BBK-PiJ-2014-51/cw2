/**
 * Created by keith for the second coursework assignment.
 * extended by caleb clayton (part time cs) for cw2
 * 
 */

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int num, int denom) {
        if (denom == 0) {
            System.out.println("Invalid fraction with denominator 0"); 
            // this should use exceptions
            return;
        }
        int gcd = myGcd(num, denom);
        setNumerator(num / gcd);
        setDenominator(denom / gcd);
    }

    public Fraction(int num){
        setNumerator(num);
        setDenominator(1);
    }
    
    @Override
    public String toString() {
        return (getDenominator() ==1) ? String.valueOf(getNumerator()) : "" + getNumerator() + '/' + getDenominator();
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int num) {
        numerator = num;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int num) {
        denominator = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (getDenominator() != fraction.getDenominator()) return false;
        if (getNumerator() != fraction.getNumerator()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    public Fraction multiply(Fraction other) {
        int num = this.getNumerator() * other.getNumerator();
        int denom = this.getDenominator() * other.getDenominator();
        return new Fraction(num, denom);
    } 
    
    /* fractions can be divided by flipping the denominator and 
     * numerator of one of the fractions and then multiplying 
     */
    public Fraction divide(Fraction other) {	
		return this.multiply(new Fraction(other.getDenominator(), other.getNumerator()));
    }
    
    /* fractions with the denominator can be added simply by adding the numerators
     * and retaining the denominator.
     */
    public Fraction add(Fraction other) {
    	int commonDenominator = other.getDenominator() * this.denominator; // find a common denominator
    	int thisMultiplier = commonDenominator / this.getDenominator();   // compute multipliers to adjust numerators 
    	int otherMultiplier = commonDenominator / other.denominator;
    	int nextNumerator = (this.numerator * thisMultiplier) + (other.getNumerator() * otherMultiplier);
    	return new Fraction(nextNumerator, commonDenominator);
    }
    
    /* same as add() but subtracts the adjusted numerators instead of adding them */
    public Fraction subtract(Fraction other) {
    	int commonDenominator = other.getDenominator() * this.denominator; // find a common denominator
    	int thisMultiplier = commonDenominator / this.getDenominator();   // compute multipliers to adjust numerators 
    	int otherMultiplier = commonDenominator / other.denominator;
    	int nextNumerator = (this.numerator * thisMultiplier) - (other.getNumerator() * otherMultiplier);
    	return new Fraction(nextNumerator, commonDenominator);		
    }
    
    
    /* square both numerator and denominator to obtain a postive number,
     * take the sqrt of the result to find the original absolute value
     */
    public Fraction absValue() {
        int numerator = (int) Math.sqrt(this.numerator * this.numerator);
        int denominator = (int) Math.sqrt(this.denominator * this.denominator);
        return new Fraction(numerator, denominator);
    }

    /* changing the sign of the numerator by multiplying by -1 
     * will change the sign of the fraction 
     */
	public Fraction negate() {
		return new Fraction(this.numerator * -1, this.denominator);
    }
       
    private int myGcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}