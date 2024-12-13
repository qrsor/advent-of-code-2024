package pl.qrsor.adventofcode2024.day13;

import java.math.BigInteger;
import java.util.Objects;

public class Day13Problem2 {
    private static final BigInteger ADD_POSITION = BigInteger.valueOf(1000000).multiply(BigInteger.valueOf(10000000));

    /**
     * Solve a system of two linear equations with integer constraints
     *
     * @param eq1Coeffs Coefficients of the first equation [a1, b1]
     * @param eq2Coeffs Coefficients of the second equation [a2, b2]
     * @param constant1 Right-hand side constant of the first equation
     * @param constant2 Right-hand side constant of the second equation
     * @return Solutions for variables (X, Y), or null if no integer solution exists
     */
    public static BigInteger[] solveLinearSystem(int[] eq1Coeffs, int[] eq2Coeffs,
                                                 BigInteger constant1, BigInteger constant2) {
        // Calculate solution using elimination method
        BigInteger[] solution = solveElimination(eq1Coeffs, eq2Coeffs, constant1, constant2);

        // Verify the solution
        return verifySolution(solution, eq1Coeffs, eq2Coeffs, constant1, constant2)
                ? solution
                : null;
    }

    /**
     * Solve the system using elimination method
     */
    private static BigInteger[] solveElimination(int[] eq1Coeffs, int[] eq2Coeffs,
                                                 BigInteger constant1, BigInteger constant2) {
        // Extract coefficients
        BigInteger a1 = BigInteger.valueOf(eq1Coeffs[0]), b1 = BigInteger.valueOf(eq1Coeffs[1]);
        BigInteger a2 = BigInteger.valueOf(eq2Coeffs[0]), b2 = BigInteger.valueOf(eq2Coeffs[1]);

        // Calculate determinant
        BigInteger det = a1.multiply(b2).subtract(a2.multiply(b1));

        // Check if determinant is zero (no unique solution)
        if (BigInteger.ZERO.equals(det)) {
            return null;
        }

        BigInteger X = (constant1.multiply(b2).subtract(constant2.multiply(b1))).divide(det);

        BigInteger Y = (a1.multiply(constant2).subtract(a2.multiply(constant1))).divide(det);

        return new BigInteger[]{X, Y};
    }

    /**
     * Verify that the solution satisfies both original equations
     */
    private static boolean verifySolution(BigInteger[] solution,
                                          int[] eq1Coeffs, int[] eq2Coeffs,
                                          BigInteger constant1, BigInteger constant2) {
        // Check if solution is null
        if (solution == null || solution.length != 2) {
            return false;
        }

        BigInteger X = solution[0];
        BigInteger Y = solution[1];

        // Check both equations
        boolean check1 = X.multiply(BigInteger.valueOf(eq1Coeffs[0])).add(Y.multiply(BigInteger.valueOf(eq1Coeffs[1]))).equals(constant1);
        boolean check2 = X.multiply(BigInteger.valueOf(eq2Coeffs[0])).add(Y.multiply(BigInteger.valueOf(eq2Coeffs[1]))).equals(constant2);

        return check1 && check2 && X.compareTo(BigInteger.ZERO) >= 0 && Y.compareTo(BigInteger.ZERO) >= 0;
    }

    public static void main(String[] args) {
        var equations = ThreeRowNumberInput.extract("day13-problem1-input");
        var result = equations.stream()
                .map(equation -> solveLinearSystem(equation.eq1Coeffs(),
                        equation.eq2Coeffs(),
                        ADD_POSITION.add(BigInteger.valueOf(equation.constant1())),
                        ADD_POSITION.add(BigInteger.valueOf(equation.constant2()))))
                .filter(Objects::nonNull)
                .map((input) -> input[0].multiply(BigInteger.valueOf(3)).add(input[1]))
                .reduce(BigInteger::add);

        System.out.println("Result " + result);
    }
}
