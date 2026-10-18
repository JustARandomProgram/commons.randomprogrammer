package math.function;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import math.ComplexNumber;
import math.interfaces.Differentiable;
import math.interfaces.Integratable;

public class Polynomial extends Expression<PolynomialTerm> implements Differentiable<Polynomial>, Integratable<Polynomial> {
    protected static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###############");
    public Map<Integer, Double> coefficients = new HashMap<>();
    public int degree = 0;

    public void addTerm(PolynomialTerm term) {
        try {
            coefficients.put(term.exponent, coefficients.get(term.exponent) + term.coefficient);
        } catch (NullPointerException e) {
            coefficients.put(term.exponent, term.coefficient);
        }
        for (PolynomialTerm otherTerm : substitutables) {
            if (term.exponent == otherTerm.exponent) {
                otherTerm.coefficient += term.coefficient;
                return;
            }
        }
        if (term.exponent > degree) {
            degree = term.exponent;
        }
        substitutables.add(term);
    }

    public void addTerms(PolynomialTerm... terms) {
        for (PolynomialTerm term : terms) {
            addTerm(term);
        }
    }

    public double evaluate(double x) {
        double sum = 0;
        for (PolynomialTerm polynomialTerm : substitutables) {
            sum += polynomialTerm.evaluate(x);
        }
        return sum;
    }

    public ComplexNumber[] getRoots() {
        standardForm();
        ComplexNumber[] roots = new ComplexNumber[degree];
        if (degree == 2) {
            double
                a = coefficients.get(2),
                b = coefficients.get(1),
                c = coefficients.get(0),
                discriminant = b*b - 4*a*c;

            roots[0] = new ComplexNumber(discriminant).squareRoot().add(new ComplexNumber(-b)).divide(new ComplexNumber(2*a));
            roots[1] = new ComplexNumber(discriminant).squareRoot().neg().add(new ComplexNumber(-b)).divide(new ComplexNumber(2*a));
        } else if (degree == 3) {
            ComplexNumber
                a = new ComplexNumber(coefficients.get(3)),
                b = new ComplexNumber(coefficients.get(2)),
                c = new ComplexNumber(coefficients.get(1)),
                d = new ComplexNumber(coefficients.get(0));
        }
        return roots;
    }

    @Override 
    public Polynomial derivative() {
        standardForm();
        Polynomial deriviative = new Polynomial();
        for (int i = 0; i < substitutables.size()-1; i++) {
            PolynomialTerm term = substitutables.get(i);
            deriviative.addTerm(new PolynomialTerm().coefficient(term.coefficient*term.exponent).exponent(term.exponent-1));
        }
        return deriviative;
    }

    public Polynomial indefiniteIntegral(double constant) {
        standardForm();
        Polynomial indefIntegral = new Polynomial();
        for (int i = 0; i < substitutables.size(); i++) {
            PolynomialTerm term = substitutables.get(i);
            indefIntegral.addTerm(new PolynomialTerm().coefficient(term.coefficient/(term.exponent+1)).exponent(term.exponent+1));
        }
        indefIntegral.addTerm(new PolynomialTerm().coefficient(constant).exponent(0));
        return indefIntegral;
    }
    
    public double definiteIntegral(double min, double max) {
        Polynomial integral = indefiniteIntegral(0);
        return integral.evaluate(max) - integral.evaluate(min);
    }

    public void standardForm() {
        Collections.sort(substitutables);
    }

    @Override 
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < substitutables.size(); i++) {
            PolynomialTerm term = substitutables.get(i);
            String termString = term.toString();
            if (term.coefficient < 0) {
                finalString.append("- " + termString.substring(1, termString.length()) + " ");
                // finalString.append(termString.substring(1, termString.length()) + " ");
            } else if (i > 0) {
                finalString.append("+ " + termString + " ");
            } else {
                finalString.append(termString + " ");
            }
        }
        return finalString.toString();
    }
}
