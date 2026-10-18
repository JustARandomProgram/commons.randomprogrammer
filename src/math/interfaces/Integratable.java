package math.interfaces;

public interface Integratable<ExpressionType> {
    public ExpressionType indefiniteIntegral(double constant);
    public double definiteIntegral(double min, double max);
}