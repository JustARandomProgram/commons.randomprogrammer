package math.linearalgebra.Vector;

/**
 * @version 1.0
 * @author JustARandomProgram
 */
public class Vector2 extends Vector {
    public float x;
    public float y;

    /**
     * Creates a 2D vector with a given x value
     * @param x The x value
     */
    public Vector2(float x) {
        super(x,0);
        this.x = x;
        this.y = 0;
    }

    /**
     * Creates a 2D vector with a given x and y value
     * @param x The x value
     * @param y The y value
     */
    public Vector2(float x, float y) {
        super(x,y);
        this.x = x;
        this.y = y;
    }
}