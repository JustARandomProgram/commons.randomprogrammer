package math.interfaces;

import math.function.Expression;

public interface Integratable<ExpressionType extends Expression<ExpressionType>> {
    public ExpressionType indefiniteIntegral();
    public double definiteIntegral(double min, double max);
}