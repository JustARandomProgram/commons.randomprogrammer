package math;

public class Combinatorics {
    private static final double[] COEFFICIENTS = {
            +676.5203681218851,
            -1259.1392167224028,
            +771.32342877765313,
            -176.61502916214059,
            +12.507343278686905,
            -0.13857109526572012,
            +9.9843695780195716e-6,
            +1.5056327351493116e-7
    };

    public static double gamma(double z) {
        if (z < 0.5) return Math.PI / (Math.sin(Math.PI * z) * gamma(1 - z));
        z--;
        double result = 0.99999999999980993;
        for (int i = 0; i < COEFFICIENTS.length; i++) result += (COEFFICIENTS[i] / (z + i + 1));
        double t = z + COEFFICIENTS.length - 0.5;
        return Math.sqrt(Math.TAU) * Math.pow(t, z + 0.5) * Math.exp(-t) * result;
    }

    // public static double gamma(ComplexNumber z) {
    //     if (z.real < 0.5) return Math.PI / (Math.sin(Math.PI * z.real) * gamma(1 - z.real));
    //     z.subtract(ComplexNumber.REAL_ONE);
    //     ComplexNumber result = new ComplexNumber(0.99999999999980993);
    //     for (int i = 0; i < COEFFICIENTS.length; i++) result.scale(COEFFICIENTS[i] / (z.real + i + 1));
    // }

    public static long fact(int x) {
        if (x < 0) throw new ArithmeticException("Cannot take integer factorial of a negative integer");
        long product = 1;
        for (int i = 2; i <= x; i++) {
            product *= i;
        }
        return product;
    }

    public static double fact(double x) {
        if (x < 0 || Double.isNaN(x)) return Double.NaN;
        if (x == Math.rint(x)) {
            return fact((int)x);
        }
        return gamma(x + 1);
    }

    public static long permutations(int items, int chosenItems) {
        return fact(items) / fact(items - chosenItems) ;
    }

    public static long combinations(int items, int chosenItems) {
        return permutations(items, chosenItems) / fact(chosenItems);
    }
}
