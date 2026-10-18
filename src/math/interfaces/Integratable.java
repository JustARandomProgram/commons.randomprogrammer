package math.interfaces;

import math.function.Function;

public interface Integratable<T extends Function> {
    public T indefiniteIntegral();
    public double definiteIntegral(double min, double max);
}