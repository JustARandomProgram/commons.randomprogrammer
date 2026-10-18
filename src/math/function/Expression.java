package math.function;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import math.interfaces.Substitutable;

public class Expression<TermType> implements Substitutable {
    public List<TermType> substitutables = new ArrayList<>();
    public List<Operator> operators = new LinkedList<>();

    @Override
    public double evaluate(double x) {return x;}

    @Override
    public double evaluate(double... values) {return values[0];}

    public void addOperation(Operator operation) {
        operators.add(operation);
    }
}
