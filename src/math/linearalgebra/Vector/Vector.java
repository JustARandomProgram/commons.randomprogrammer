package math.linearalgebra.Vector;

/**
 * @version 1.0
 * @author JustARandomProgram
 */
public class Vector {
    private float[] values;
    public final int dimensions;

    //EXCEPTIONS
    public static class DimensionsAreNotEqualExeception extends RuntimeException {
        public DimensionsAreNotEqualExeception(String message) {
            super(message);
        }
        public DimensionsAreNotEqualExeception(String message, Throwable err) {
            super(message, err);
        }
    }

    public static class InvalidDimensionsException extends RuntimeException {
        public InvalidDimensionsException(String message) {
            super(message);
        }
    }

    /**
     * Creates a vector with given values
     * @param values The values of the vector
     */
    public Vector(float... values) {
        this.values = values;
        this.dimensions = values.length;
    }

    // Arithimetic Methods
    /**
     * Adds 2 vectors of the same dimension
     * @param vec1 The first vector to add
     * @param vec2 The second vector to add
     * @return The sum of the vectors
     * @throws DimensionsAreNotEqualException If the dimensions of the vectors are not equal
     */
    public static Vector add(Vector vec1, Vector vec2) {
        if (vec1.dimensions != vec2.dimensions) {throw new DimensionsAreNotEqualExeception("Dimension of the vectors are not equal");}
        float[] newValues = new float[vec1.dimensions];
        for (int i = 0; i < newValues.length; i++) {
            newValues[i] = vec1.values[i] + vec2.values[i];
        }
        return new Vector(newValues);
    }

    /**
     * Gets the vector with the opposite direction but same magnitude of the original vector
     * @return The negated vector
     */
    public Vector neg() {
        float[] newValues = new float[this.dimensions];
        for (int i = 0; i < newValues.length; i++) {
            newValues[i] = -this.values[i];
        }
        return new Vector(newValues);
    }

    /**
     * Scales the vector by a factor
     * @param factor How much you're scaling the vector
     * @return The scaled vector
     */
    public Vector scale(float factor) {
        float[] newValues = new float[this.dimensions];
        for (int i = 0; i < this.dimensions; i++) {
            newValues[i] = this.values[i] / factor;
        }
        return new Vector(newValues);
    }

    // Vector Methods
    
    /**
     * Gets the unit of the vector
     * @return The unit of the vector
     */
    public Vector unit() {
        float magnitude = this.magnitude();
        return this.scale(1/magnitude);
    }

    /**
     * Gets the length of the vector
     * @return The length of the vector
     */
    public float magnitude() {
        float magnitude = 0;
        for (float d : this.values) {
            magnitude += d*d;
        }
        return (float)Math.sqrt(magnitude);
    }

    /**
     * Calculates the dot product between two vectors
     * @param vec1 The first vector to multiply
     * @param vec2 The second vector to multiply
     * @return The dot product
     * @throws DimensionsAreNotEqualException If the dimensions of the vectors are not equal
     */
    public static float dot(Vector vec1, Vector vec2) {
        if (vec1.dimensions != vec2.dimensions) throw new DimensionsAreNotEqualExeception("Dimension of the vectors are not equal");
        float dotProduct = 0;
        for (int i = 0; i < vec1.dimensions; i++) {
            dotProduct += vec1.values[i] * vec2.values[i];
        }
        return dotProduct;
    }

    /**
     * Calculates the angle between two vectors
     * @param vec1
     * @param vec2
     * @return The angle between the vectors
     */
    public static float angle(Vector vec1, Vector vec2) {
        return (float)Math.acos(Vector.dot(vec1.unit(), vec2.unit()));
    }
    
    @Override
    public String toString() {
        // return Arrays.toString(this.values);
        String finalString = "[";
        for (float d : this.values) {
            finalString += Float.toString(d) + ", ";
        }
        return finalString.substring(0,finalString.length()-2) + "]";
    }

    /**
     * Gets a value from a vector
     * @param index Where the value is located
     * @return The value
     */
    public float get(int index) {
        return this.values[index];
    }
}