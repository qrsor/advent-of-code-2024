package pl.qrsor.adventofcode2024.day13;

import java.util.Objects;

public class Day13Problem1 {
    /**
     * Solve a system of two linear equations with integer constraints
     *
     * @param eq1Coeffs Coefficients of the first equation [a1, b1]
     * @param eq2Coeffs Coefficients of the second equation [a2, b2]
     * @param constant1 Right-hand side constant of the first equation
     * @param constant2 Right-hand side constant of the second equation
     * @return Solutions for variables (X, Y), or null if no integer solution exists
     */
    public static int[] solveLinearSystem(int[] eq1Coeffs, int[] eq2Coeffs,
                                          int constant1, int constant2) {
        // Calculate solution using elimination method
        int[] solution = solveElimination(eq1Coeffs, eq2Coeffs, constant1, constant2);

        // Verify the solution
        return verifySolution(solution, eq1Coeffs, eq2Coeffs, constant1, constant2)
                ? solution
                : null;
    }

    /**
     * Solve the system using elimination method
     */
    private static int[] solveElimination(int[] eq1Coeffs, int[] eq2Coeffs,
                                          int constant1, int constant2) {
        // Extract coefficients
        int a1 = eq1Coeffs[0], b1 = eq1Coeffs[1];
        int a2 = eq2Coeffs[0], b2 = eq2Coeffs[1];

        // Calculate determinant
        int det = a1 * b2 - a2 * b1;

        // Check if determinant is zero (no unique solution)
        if (det == 0) {
            return null;
        }

        int X = (constant1 * b2 - constant2 * b1) / det;

        int Y = (a1 * constant2 - a2 * constant1) / det;

        return new int[]{X, Y};
    }

    /**
     * Verify that the solution satisfies both original equations
     */
    private static boolean verifySolution(int[] solution,
                                          int[] eq1Coeffs, int[] eq2Coeffs,
                                          int constant1, int constant2) {
        // Check if solution is null
        if (solution == null || solution.length != 2) {
            return false;
        }

        int X = solution[0];
        int Y = solution[1];

        // Check both equations
        boolean check1 = eq1Coeffs[0] * X + eq1Coeffs[1] * Y == constant1;
        boolean check2 = eq2Coeffs[0] * X + eq2Coeffs[1] * Y == constant2;

        return check1 && check2 && X >= 0 && Y >= 0 && X <= 100 && Y <= 100;
    }

    public static void main(String[] args) {
        var equations = ThreeRowNumberInput.extract("day13-problem1-input");
        var result = equations.stream()
                .map(equation -> solveLinearSystem(equation.eq1Coeffs(), equation.eq2Coeffs(), equation.constant1(), equation.constant2()))
                .filter(Objects::nonNull)
                .mapToInt((input) -> 3 * input[0] + input[1])
                .sum();

        System.out.println("Result " + result);
    }
}
