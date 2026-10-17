package math;

import math.interfaces.NativeOperators;

public class ComplexNumber implements NativeOperators<ComplexNumber> {
    public double real;
    public double imaginary;

    public static final ComplexNumber REAL_ONE = new ComplexNumber(1);
    public static final ComplexNumber IMAGINARY_ONE = new ComplexNumber(0,1);

    public ComplexNumber(double real) {
        this(real, 0);
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override 
    public ComplexNumber add(ComplexNumber complex) {
        return new ComplexNumber(real + complex.real, imaginary + complex.imaginary);
    }

    @Override 
    public ComplexNumber multiply(ComplexNumber complexNumber) {
        return new ComplexNumber((real * complexNumber.real) - (imaginary * complexNumber.imaginary), 
                                 (imaginary * complexNumber.real) - (real * complexNumber.imaginary));
    }

    public ComplexNumber scale(double factor) {
        return new ComplexNumber(factor * real, factor * imaginary);
    }

    @Override 
    public ComplexNumber neg() {
        return new ComplexNumber(-real, -imaginary);
    }

    public ComplexNumber conjug() {
        return new ComplexNumber(real, -imaginary);
    }

    @Override
    public ComplexNumber zero() {
        return new ComplexNumber(0, 0);
    }

    @Override
    public ComplexNumber reciprocal() {
        double sumOfSquares = real*real + imaginary*imaginary;
        return new ComplexNumber(real / sumOfSquares, -imaginary / sumOfSquares);
    }

    @Override
    public ComplexNumber one() {
        return new ComplexNumber(1, 1);
    }

    @Override
    public ComplexNumber subtract(ComplexNumber other) {
        return add(other.neg());
    }

    @Override
    public ComplexNumber divide(ComplexNumber other) {
        return multiply(other.reciprocal());
    }

    @Override
    public ComplexNumber pow(int power) {
        ComplexNumber product = new ComplexNumber(1);
        for (int i = 0; i < power; i++) {
            product = product.multiply(this);
        }
        return product;
    }

    @Override
    public ComplexNumber intMulti(int factor) {
        return scale(factor);
    }

    public double absoluteValue() {
        return Math.sqrt(imaginary*imaginary + real*real);
    }

    public ComplexNumber squareRoot() {
        if (real < 0) {
            return new ComplexNumber(0, Math.sqrt(-real));
        } else if (imaginary == 0) return new ComplexNumber(Math.sqrt(real));
        else {
            return new ComplexNumber(Math.sqrt((absoluteValue() + real)/2), 
                                     Math.signum(imaginary) * Math.sqrt((absoluteValue() - real)/2));
        }
    }

    @Override 
    public String toString() {
        return real + " + " + imaginary + "i";
    }
}
