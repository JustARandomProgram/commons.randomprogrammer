package math.interfaces;

import math.function.Expression;

public interface Integratable<T extends Expression> {
    public T indefiniteIntegral();
    public double definiteIntegral(double min, double max);
}