package math.function;

public class PolynomialTerm extends Term implements Comparable<PolynomialTerm> {
    public double coefficient;
    public int exponent;

    public PolynomialTerm() {
        this.coefficient = 0;
        this.exponent = 0;
    }

    public PolynomialTerm coefficient(double coefficient) {
        this.coefficient = coefficient;
        return this;
    }

    public PolynomialTerm exponent(int exponent) {
        this.exponent = exponent;
        return this;
    }

    public double evaluate(double x) {
        return coefficient * Math.pow(x, exponent);
    }

    @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        if (exponent == 1) {
            finalString.append("x");
        } else if (exponent != 0) {
            finalString.append("x^"+exponent);
        } 
        if (Math.abs(coefficient) != 1 || exponent == 0) {
            finalString.insert(0, Polynomial.DECIMAL_FORMAT.format(coefficient));
        }
        return finalString.toString();
    }

    @Override
    public int compareTo(PolynomialTerm otherTerm) {
        if (exponent < otherTerm.exponent) {
            return 1;
        } else {
            return -1;
        }
    }
}
