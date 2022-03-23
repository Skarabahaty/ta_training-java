package com.epam.training.maksim_skarabahaty.optional_task_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Task {

    /*

4.     Найти число, в котором количество различных цифр минимально. Если таких чисел несколько, найти первое из них.

5.     Найти количество чисел, содержащих только четные цифры, а среди оставшихся — количество чисел с равным числом четных и нечетных цифр.

6.     Найти число, цифры в котором идут в строгом порядке возрастания. Если таких чисел несколько, найти первое из них.

7.     Найти число, состоящее только из различных цифр. Если таких чисел несколько, найти первое из них.*/

    public static void main(String[] args) {

        String[] numbers = readValuesFromConsole();

        findLongestNumber(numbers);
        findShortestNumber(numbers); //task 1 end
        delimit();

        List<String> numbersList = Arrays.asList(numbers);

        printInIncreasingOrder(numbersList);
        printInDecreasingOrder(numbersList);
        delimit();//task 2 end

        calculateAverageAndPrintNumbersRelativeToAverage(numbers);//task 3 end


    }

    private static String[] readValuesFromConsole() {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        System.in
                )
        );

        String lines = null;
        try {
            lines = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert lines != null;

        return lines.trim().split("\\s+");
    }

    private static void findLongestNumber(String[] numbers) {

        String longestElement = "";
        int maxLength = 0;

        for (String number : numbers) {
            if (number.length() > maxLength) {
                maxLength = number.length();
                longestElement = number;
            }
        }

        System.out.println("Longest number = " + longestElement + " , length = " + maxLength);
    }

    private static void findShortestNumber(String[] numbers) {

        String shortestElement = numbers[0];
        int minLength = shortestElement.length();

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i].length() < minLength) {
                minLength = numbers[i].length();
                shortestElement = numbers[i];
            }
        }
        System.out.println("Shortest number = " + shortestElement + " , length = " + minLength);
    }

    private static void delimit() {
        System.out.println("-".repeat(50));
    }

    private static void printInIncreasingOrder(List<String> numbersList) {
        numbersList.sort(Comparator.comparingInt(String::length));
        System.out.println("Ordered incr. : " + numbersList);
    }

    private static void printInDecreasingOrder(List<String> numbersList) {
        numbersList.sort((leftString, rightString) -> rightString.length() - leftString.length());
        System.out.println("Ordered decr. : " + numbersList);
    }

    private static void calculateAverageAndPrintNumbersRelativeToAverage(String[] numbers) {

        int lengthsSum = 0;
        for (String number : numbers) {
            lengthsSum += number.length();
        }

        double averageLength = (double)lengthsSum / numbers.length;
        System.out.println("Average length = " + averageLength);
        delimit();

        System.out.println("Numbers, longer than average: ");
        for (String number : numbers) {
            if (number.length() > averageLength) {
                System.out.println(number + ", length = " + number.length());
            }
        }
        delimit();
        System.out.println("Numbers, shorter than average: ");
        for (String number : numbers) {
            if (number.length() < averageLength) {
                System.out.println(number + ", length = " + number.length());
            }
        }
        delimit();
    }
}
