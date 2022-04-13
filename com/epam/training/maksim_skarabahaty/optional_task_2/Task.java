package com.epam.training.maksim_skarabahaty.optional_task_2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        int[][] matrix = generateMatrix();
        fillMatrix(matrix);
        printMatrix(matrix);
        int[][] cloneOfMatrix = matrix.clone();

        int[][] linesByColumn = sortLinesByColumn(cloneOfMatrix);
        printMatrix(linesByColumn);
        printMatrix(matrix);


        int[][] columnsByLine = sortColumnsByLine(cloneOfMatrix);
        printMatrix(columnsByLine);
        printMatrix(matrix);


        findAmountOfIncreasingValues(matrix);

        findAmountOfDecreasingValues(matrix);

        findSumBetweenFirstPositiveAndSecondPositive(matrix);

        findMaxElementAndRemoveLinesAndColumnsWithIt(matrix);

/*

4. Найти максимальный элемент в матрице и удалить из матрицы все строки и столбцы, его содержащие
*/


    }

    private static void findMaxElementAndRemoveLinesAndColumnsWithIt(int[][] matrix) {

        int maxElement = findMaxElement(matrix);

        boolean[] doesLineContainMaxValue = new boolean[matrix.length];
        doesLineContainMaxValue(doesLineContainMaxValue, matrix, maxElement);

        boolean[] doesColumnContainMaxValue = new boolean[matrix.length];
        doesColumnContainMaxValue(doesColumnContainMaxValue, matrix, maxElement);

        int lineNumberForNewMatrix = getLineNumberForNewMatrix(matrix, doesLineContainMaxValue);

        int columnNumberForNewMatrix = getLineNumberForNewMatrix(matrix, doesColumnContainMaxValue);

        int[][] newMatrix = new int[lineNumberForNewMatrix][columnNumberForNewMatrix];

        int lineCounter = 0;
        for (int i = 0; i < matrix.length; i++) {

            if (! doesLineContainMaxValue[i]) {
                int columnCounter = 0;
                for (int j = 0; j < matrix[i].length; j++) {

                    if (!doesColumnContainMaxValue[j]) {
                        newMatrix[lineCounter][columnCounter] = matrix[i][j];
                        columnCounter++;
                    }
                }
                lineCounter++;
            }
        }

        printMatrix(newMatrix);
    }

    private static int getLineNumberForNewMatrix(int[][] matrix, boolean[] doesLineContainMaxValue) {
        int lineNumberForNewMatrix = matrix.length;
        for (boolean isContainMaxValue : doesLineContainMaxValue) {
            if (isContainMaxValue) {
                lineNumberForNewMatrix--;
            }
        }
        return lineNumberForNewMatrix;
    }

    private static int[][] generateMatrix() {
        System.out.println("Enter the matrix dimension: ");
        int matrixDimension = scanner.nextInt();
        return new int[matrixDimension][matrixDimension];
    }

    private static void fillMatrix(int[][] matrix) {

        System.out.println("Matrix will be filled with random numbers from -X to X");
        System.out.println("Enter X: ");
        int border = scanner.nextInt();

        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(-border, border + 1);
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%2d ", anInt);
            }
            System.out.println();
        }
        System.out.println("-".repeat(50));
    }

    private static int[][] sortLinesByColumn(int[][] matrix) {

        int columnNumber;
        do {
            System.out.println("Enter the number of column (starting with ZERO), needed to sort: ");
            columnNumber = scanner.nextInt();
        } while (columnNumber > matrix.length || columnNumber < 0);

        boolean isSorted = true;
        do {
            for (int i = 0; i < matrix.length - 1; i++) {
                isSorted = true;

                for (int j = 0; j < matrix.length - i - 1; j++) {
                    if (matrix[j][columnNumber] > matrix[j + 1][columnNumber]) {
                        isSorted = false;

                        int[] temp = Arrays.copyOf(matrix[j], matrix[j].length);
                        matrix[j] = matrix[j + 1];
                        matrix[j + 1] = temp;
                    }
                }
            }
        } while (!isSorted);

        return matrix;
    }

    private static int[][] sortColumnsByLine(int[][] matrix) {

        int[][] localMatrix = Arrays.copyOf(matrix, matrix.length);

        int lineNumber;
        do {
            System.out.println("Enter the number of line (starting with ZERO), needed to sort: ");
            lineNumber = scanner.nextInt();
        } while (lineNumber > localMatrix.length || lineNumber < 0);

        boolean isSorted = true;
        do {
            for (int i = 0; i < localMatrix.length - 1; i++) {
                isSorted = true;

                for (int j = 0; j < localMatrix.length - i - 1; j++) {
                    if (localMatrix[lineNumber][j] > localMatrix[lineNumber][j + 1]) {
                        isSorted = false;

                        for (int k = 0; k < localMatrix.length; k++) {
                            int temp = localMatrix[k][j];
                            localMatrix[k][j] = localMatrix[k][j + 1];
                            localMatrix[k][j + 1] = temp;
                        }
                    }
                }
            }
        } while (!isSorted);

        return localMatrix;
    }

    private static void findAmountOfIncreasingValues(int[][] matrix) {

        int overallCounter = 1;
        for (int[] ints : matrix) {

            int lineCounter = 1;
            int inLineCounter = 1;
            for (int j = 0; j < ints.length - 1; j++) {

                if (ints[j] < ints[j + 1]) {
                    inLineCounter++;
                    lineCounter = inLineCounter;
                } else {
                    lineCounter = inLineCounter;
                    inLineCounter = 1;
                }
            }
            if (lineCounter > overallCounter) {
                overallCounter = lineCounter;
            }
        }

        System.out.println("Largest amount of increasing elements = " + overallCounter);
        System.out.println("-".repeat(50));
    }

    private static void findAmountOfDecreasingValues(int[][] matrix) {

        int overallCounter = 1;
        for (int[] ints : matrix) {

            int lineCounter = 1;
            int inLineCounter = 1;
            for (int j = 0; j < ints.length - 1; j++) {

                if (ints[j] > ints[j + 1]) {
                    inLineCounter++;
                    lineCounter = inLineCounter;
                } else {
                    lineCounter = inLineCounter;
                    inLineCounter = 1;
                }
            }
            if (lineCounter > overallCounter) {
                overallCounter = lineCounter;
            }
        }

        System.out.println("Largest amount of decreasing elements = " + overallCounter);
        System.out.println("-".repeat(50));

    }

    private static void findSumBetweenFirstPositiveAndSecondPositive(int[][] matrix) {


        for (int i = 0; i < matrix.length; i++) {

            int sumForLine = 0;
            int indexOfFirstPositive = -1;
            int indexOfSecondPositive = -1;

            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] >= 0) {

                    if (indexOfFirstPositive == -1) {
                        indexOfFirstPositive = j;

                    } else {
                        indexOfSecondPositive = j;
                        break;
                    }
                }
            }

            if (indexOfSecondPositive == -1 || indexOfSecondPositive - indexOfFirstPositive == 1) {
                System.out.printf("Sum for line %d = %d%n", i, sumForLine);

            } else {
                for (int k = indexOfFirstPositive + 1; k < indexOfSecondPositive - indexOfFirstPositive; k++) {
                    sumForLine += matrix[i][k];
                }
                System.out.printf("Sum for line %d = %d%n", i, sumForLine);
            }
        }
    }

    private static void doesColumnContainMaxValue(boolean[] doesColumnContainMaxValue, int[][] matrix, int maxElement) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == maxElement) {
                    doesColumnContainMaxValue[j] = true;
                }
            }
        }

    }

    private static void doesLineContainMaxValue(boolean[] doesLineContainMaxValue, int[][] matrix, int maxElement) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == maxElement) {
                    doesLineContainMaxValue[i] = true;
                    break;
                }
            }
        }
    }

    private static int findMaxElement(int[][] matrix) {

        int maxElement = Integer.MIN_VALUE;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt > maxElement) {
                    maxElement = anInt;
                }
            }
        }
        return maxElement;
    }
}
