package math.interfaces;

import math.function.Expression;

public interface Differentiable<T extends Expression> {
    public T derivative();
}