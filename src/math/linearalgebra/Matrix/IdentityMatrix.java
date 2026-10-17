package math.linearalgebra.Matrix;

/**
 * @version 1.0
 * @author JustARandomProgram
 */
public class IdentityMatrix extends Matrix {
    public int order;

    /**
     * Creates an indentity matrix
     * @param order The order of the matrix
     */
    public IdentityMatrix(int order) {
        super(order, order);
        this.order = order;
        for (int i = 0; i < order; i++) {
            this.set(i, i, 1);
        }
    }
}
