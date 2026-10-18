package math.interfaces;

import math.function.Function;

public interface Differentiable<T extends Function> {
    public T derivative();
}