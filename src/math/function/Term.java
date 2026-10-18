package math.function;

import math.interfaces.Substitutable;

public class Term implements Substitutable {

    @Override
    public double evaluate(double x) {return x;}

    @Override
    public double evaluate(double... values) {return values[0];}
    
}
