package math.interfaces;

public interface NativeOperators<NumberType> extends Addition<NumberType>, Multiplication<NumberType> {
    public NumberType subtract(NumberType other);
    public NumberType divide(NumberType other);
    public NumberType pow(int power);
    public NumberType intMulti(int factor);
}
