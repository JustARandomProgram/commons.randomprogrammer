package math.linearalgebra.Matrix;

/**
 * @version 1.0
 * @author JustARandomProgram
 */
public class SquareMatrix extends Matrix {
    public int order;

    /**
     * Creates a square matrix, given the order
     * @param order The order of the matrix, or it's dimensions
     */
    public SquareMatrix(int order) {
        super(order,order);
        this.order = order;
    }

    /**
     * Creates a square matrix, given the values
     * @param values The values to give to the matrix
     * @throws InconsistentDimensionsException If the number of columns and rows are not equal
     */
    public SquareMatrix(double[][] values) {
        super(values);
        this.order = values.length;
        for (double[] row : values) {
            if (row.length != this.order) throw new InconsistentDimensionsException("The given values are not square");
        }
    }

    /**
     * Gets the sub matrix, given a row and column
     * @param rowToRemove The row to get rid of
     * @param columnToRemove The column to get rid of
     * @return The sub matrix
     */
    @Override
    public SquareMatrix subMatrix(int rowToRemove, int columnToRemove) {
        SquareMatrix subMatrix = new SquareMatrix(this.order - 1);
        int currentRowToWrite = 0, currentColumnToWrite = 0;
        for (int currentRow = 0; currentRow < this.order; currentRow++) {
            if (currentRow == rowToRemove) continue;
            currentColumnToWrite = 0;
            for (int currentColumn = 0; currentColumn < this.order; currentColumn++) {
                if (currentColumn == columnToRemove) continue;
                subMatrix.set(currentRowToWrite,currentColumnToWrite,this.get(currentRow, currentColumn));
                currentColumnToWrite++;
            }
            currentRowToWrite++;
        }
        return subMatrix;
    }

    /**
     * Calculates the inverse of the matrix
     * @return The inverted matrix
     * @throws MatrixIsNotInvertibleException If the matrix has no inverse (determinant is 0)
     * @see https://www.geeksforgeeks.org/maths/adjoint-of-matrix/
     */
    public Matrix inverse() {
        SquareMatrix adjoint = new SquareMatrix(this.order);
        double determinant = this.determinant();
        if (determinant == 0) throw new MatrixIsNotInvertibleException("The matrix has no inverse");
        for (int row = 0; row < this.order; row++) {
            for (int column = 0; column < this.order; column++) {
                adjoint.set(row, column, (double)(this.subMatrix(row, column).determinant() * Math.pow(-1, row + column)));
            }
        }
        return adjoint.transpose().scale(1/determinant);
    }

    /**
     * Calculates the determinant of the matrix
     * @return The determinant of the matrix
     * @see https://www.geeksforgeeks.org/dsa/determinant-of-a-matrix/
     */
    public double determinant() {
        if (this.order == 1) {
            return this.get(0, 0);
        }
        else if (this.order == 2) {
            return this.get(0, 0) * this.get(1, 1) - this.get(0, 1) * this.get(1, 0);
        }
        double[][] matrixValues = new double[this.order][this.order];
        for (int row = 0; row < this.order; row++) {
            matrixValues[row] = this.values[row].clone();
        }
        double num1, num2;
        double total = 1;
        double determinant = 1;
        int index;
        double[] temp = new double[this.order+1];

        for (int i = 0; i < this.order; i++) {
            index = i;
            while (index < this.order && matrixValues[index][i] == 0) {
                index++;
            }
            if (index == this.order) {
                continue;
            }
            if (index != i) {
                for (int j = 0; j < this.order; j++) {
                    double tempSwap = matrixValues[index][j];
                    matrixValues[index][j] = matrixValues[i][j];
                    matrixValues[i][i] = tempSwap;
                }
                determinant *= Math.pow(-1, index-1);
            } 
            for (int j = 0; j < this.order; j++) {
                temp[j] = matrixValues[i][j];
            }
            for (int j = i + 1; j < this.order; j++) {
                num1 = temp[i];
                num2 = matrixValues[j][i];

                for (int k = 0; k < this.order; k++) {
                    matrixValues[j][k] = num1 * matrixValues[j][k] - num2 * temp[k];
                }
                total *= num1;
            }
        }

        for (int i = 0; i < this.order; i++) {
            determinant *= matrixValues[i][i];
        }
        return determinant / total;
    }
}
