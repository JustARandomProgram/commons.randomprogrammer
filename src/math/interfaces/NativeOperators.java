package math.interfaces;

public interface NativeOperators<T> extends Addition<T>, Multiplication<T> {
    public T subtract(T other);
    public T divide(T other);
    public T pow(int power);
    public T intMulti(int factor);
}
