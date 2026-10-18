package math.linearalgebra.Matrix;

import math.interfaces.Addition;
import math.linearalgebra.Vector.Vector;

/**
 * @version 1.0
 * @author JustARandomProgram
 */
public class Matrix implements Addition<Matrix> {
    public int columns;
    public int rows;
    protected double[][] values;

    //EXCEPTIONS
    public static class MatrixIsNotInvertibleException extends RuntimeException {
        public MatrixIsNotInvertibleException(String message) {
            super(message);
        }
    }

    public static class MatrixIsNotSquareException extends RuntimeException {
        public MatrixIsNotSquareException(String message) {
            super(message);
        }
    }

    public static class InconsistentDimensionsException extends RuntimeException {
        public InconsistentDimensionsException(String message) {
            super(message);
        }
    }

    /**
     * Creates a blank matrix with dimensions
     * @param rows The amount of rows the matrix has
     * @param columns The amount of collumns the matrix has
     */
    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) throw new Vector.InvalidDimensionsException("rows or columns cannot be zero or less");
        this.columns = columns;
        this.rows = rows;
        this.values = new double[rows][columns];
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                this.values[row][column] = 0;
            }
        }
    }

    /**
     * Creates a matrix with given values
     * @param values The values to give to the matrix
     * @throws InconsistentDimensionsException If the number of columns, per row, are not equal
     */
    public Matrix(double[][] values) {
        this.rows = values.length;
        int amountOfColumns = values[0].length;
        for (int i = 1; i < values.length; i++) {
            if (amountOfColumns != values[i].length) {
                throw new InconsistentDimensionsException("The dimensions of the matrix is not consistent");
            }
        }
        this.columns = amountOfColumns;
        this.values = values;
    }

    /**
     * Creates a matrix from an array of vectors
     * @param vectors The array of vectors
     */
    public Matrix(Vector[] vectors) {
        this.rows = vectors[0].dimensions;
        this.columns = vectors.length;
        this.values = new double[this.rows][this.columns];
        for (int column = 0; column < this.columns; column++) {
            if (vectors[column].dimensions == this.rows) {
                for (int row = 0; row < this.rows; row++) {
                    this.values[row][column] = vectors[column].get(row);
                }
            }
            else {
                throw new InconsistentDimensionsException("The dimensions of the matrix is not consistent");
            }
        }
    }

    /**
     * Multiples the matrices
     * @param matrixA The first matrix you're multiplying
     * @param matrixB The second matrix you're multiplying
     * @return The product of the matrices
     */
    public Matrix multiply(Matrix matrix) {
        if (columns != matrix.rows) throw new Vector.InvalidDimensionsException("The number of columns in matrixA isn't equal to the number of rows in matrixB");
        double[][] newValues = new double[rows][matrix.columns];

        Vector[] rowsAsVectors = new Vector[rows];
        for (int row = 0; row < rows; row++) {
            rowsAsVectors[row] = getRow(row);
        }

        Vector[] columnsAsVectors = new Vector[matrix.columns];
        for (int column = 0; column < matrix.columns; column++) {
            columnsAsVectors[column] = matrix.getColumn(column);
        }

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < matrix.columns; column++) {
                newValues[row][column] = rowsAsVectors[row].dot(columnsAsVectors[column]);
            }
        }

        return new Matrix(newValues);
    }

    /**
     * Adds a matrix to the original matrix
     * @param matrixB The second matrix you're adding
     * @return The sum of the matrices
     */
    public Matrix add(Matrix matrix) {
        if (!(rows == matrix.rows && columns == matrix.columns)) throw new Vector.DimensionsAreNotEqualExeception("The dimensions of the matrices are not equal");

        double[][] newValues = new double[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                newValues[row][column] = values[row][column] + matrix.values[row][column];
            }
        }

        return new Matrix(newValues);
    }

    public Matrix zero() {
        return new Matrix(rows, columns);
    }

    /**
     * Converts the vector into a matrix
     * @return The converted matrix
     */
    public Matrix convertToMatrix(Vector vector) {
        double[][] values = new double[vector.dimensions][1];
        for (int i = 0; i < vector.dimensions; i++) {
            values[i][0] = vector.get(i);
        }
        return new Matrix(values);
    }

    // OBJECT METHODS

    /**
     * Gets the sub matrix, given a row and column
     * @param row The row to get rid of
     * @param column The column to get rid of
     * @return The sub matrix
     */
    public Matrix subMatrix(int row, int column) {
        Matrix subMatrix = new Matrix(this.rows - 1, this.columns - 1);
        int currentRowToWrite = 0, currentColumnToWrite = 0;
        for (int currentRow = 0; currentRow < this.rows; currentRow++) {
            if (currentRow == row) continue;
            currentColumnToWrite = 0;
            for (int currentColumn = 0; currentColumn < this.columns; currentColumn++) {
                if (currentColumn == column) continue;
                subMatrix.set(currentRowToWrite,currentColumnToWrite,this.get(currentRow,currentColumn));
                currentColumnToWrite++;
            }
            currentRowToWrite++;
        }
        return subMatrix;
    }

    /**
     * Transposes the matrix
     * @return The matrix transposed
     */
    public Matrix transpose() {
        Matrix newMatrix = new Matrix(this.columns,this.rows);
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                newMatrix.set(column, row, this.get(row, column));
            }
        }
        return newMatrix;
    }

    /**
     * Scales the matrix by a factor
     * @param factor How much you're scaling the matrix
     * @return The scaled matrix
     */
    public Matrix scale(double factor) {
        double[][] newValues = new double[this.rows][this.columns];
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                newValues[row][column] = this.values[row][column] * factor;
            }
        }
        return new Matrix(newValues);
    }

    /**
     * Negates each number in the matrix
     * @return The result of this operation
     */
    public Matrix neg() {
        double[][] newValues = new double[this.rows][this.columns];
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                newValues[row][column] = -this.values[row][column];
            }
        }
        return new Matrix(newValues);
    }

    // GETTERS

    /**
     * Gets a row from a matrix
     * @param row The row of the matrix
     * @return The row of the matrix, as a vector
     */
    public Vector getRow(int row) {
        return new Vector(this.values[row]);
    }

    /**
     * Gets a column from a matrix
     * @param column The column of the matrix
     * @return The column of the matrix, as a vector
     */
    public Vector getColumn(int column) {
        double[] values = new double[this.rows];
        for (int row = 0; row < this.values.length; row++) {
            values[row] = this.values[row][column];
        }
        return new Vector(values);
    }

    /**
     * Gets the value from a Matrix
     * @param row The row where the value is (Starts from 0)
     * @param column The column where the value is (Starts from 0)
     * @return The value you're getting from the Matrix
     */
    public double get(int row, int column) {
        return this.values[row][column];
    }

    // SETTERS

    /**
     * Sets the value of a row
     * @param row Which row you're setting
     * @param newValues The values you're setting
     * @throws InconsistentDimensionsException If the length of {@code newValues} isn't equal to the amount of rows
     */
    public void setRow(int row, double[] newValues) {
        if (newValues.length != this.columns) {
            throw new InconsistentDimensionsException("The dimensions of the matrix is not consistent");
        }
        else {
            this.values[row] = newValues;
        }
    }

    /**
     * Sets the value of a column
     * @param column Which column you're setting
     * @param newValues The values you're setting
     * @throws InconsistentDimensionsException If the length of {@code newValues} isn't equal to the amount of columns
     */
    public void setColumn(int column, double[] newValues) {
        if (newValues.length != this.rows) {
            throw new InconsistentDimensionsException("The dimensions of the matrix is not consistent");
        }
        else {
            for (int row = 0; row < this.values.length; row++) {
                this.values[row][column] = newValues[row];
            }
        }
    }

    /**
     * Sets a value to a Matrix
     * @param row The row where the value is
     * @param columns The column where the value is
     * @param newValue The value you're setting to the Matrix
     */
    public void set(int row, int columns, double newValue) {
        this.values[row][columns] = newValue;
    }

    @Override
    public String toString() {
        String finalString = "";
        for (double[] row : this.values) {
            for (double value : row) {
                finalString += Double.toString(value) + " ";
            }
            // finalString += currentRow.substring(0,currentRow.length()-2) + "]\n";
            finalString += "\n";
        }
        return finalString.substring(0,finalString.length()-2);
    }
}

