package math.interfaces;

import math.function.Expression;
import math.function.Term;

public interface Differentiable<ExpressionType extends Expression<Term>> {
    public ExpressionType derivative();
}