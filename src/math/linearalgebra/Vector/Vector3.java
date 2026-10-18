package math.linearalgebra.Vector;

/**
 * @version 1.0
 * @author JustARandomProgram
 */
public class Vector3 extends Vector {
    public double x;
    public double y;
    public double z;

    //EXCEPTIONS
    public static class VectorDimensionsAreNotThreeException extends RuntimeException {
        public VectorDimensionsAreNotThreeException(String message) {
            super(message);
        }
    }

    /**
     * Creates a 3D vector with a given x value
     * @param x The x value
     */
    public Vector3(double x) {
        super(x,0,0);
        this.x = x;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Creates a 3D vector with a given x and y value
     * @param x The x value
     * @param y The y value
     */
    public Vector3(double x, double y) {
        super(x,y,0);
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    /**
     * Creates a 3D vector with a given x, y and z value
     * @param x The x value
     * @param y The y value
     * @param z The z value
     */
    public Vector3(double x, double y, double z) {
        super(x,y,z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Gets the cross product of 3D vectors
     * @param vec1 The first vector to multiply
     * @param vec2 The second vector to multuply
     * @return The cross product
     * @throws VectorDimensionsAreNotThreeException If the vector dimensions are not 3
     */
    public static Vector3 cross(Vector3 vec1, Vector3 vec2) {
        if ((vec1.dimensions != vec2.dimensions) || (vec1.dimensions != 3)) {throw new DimensionsAreNotEqualExeception("Vector Dimensions are not 3", new VectorDimensionsAreNotThreeException(""));} 
        return new Vector3(
            vec1.y*vec2.z-vec2.y*vec1.z,
            vec1.z*vec2.x-vec2.z*vec1.x,
            vec1.x*vec2.y-vec2.x*vec1.y
        );
    }
}
