package com.epam.training.maksim_skarabahaty.optional_task_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {


    public static void main(String[] args) {

        String[] numbersInString = readValuesFromConsole();


        findLongestNumber(numbersInString);
        findShortestNumber(numbersInString);
        delimiter();//task 1 end


        printInIncreasingOrder(numbersInString);
        printInDecreasingOrder(numbersInString);
        delimiter();//task 2 end


        double averageLength = getAverageLength(numbersInString);
        printNumbersRelativeToAverage(numbersInString, averageLength);
        delimiter();//task 3 end


        checkForCondition4(numbersInString);
        delimiter();//task 4 end

        /*5.Найти количество чисел, содержащих только четные цифры,
        а среди оставшихся — количество чисел с равным числом четных и нечетных цифр.
         */
        checkForCondition5(numbersInString);




    /*

6. Найти число, цифры в котором идут в строгом порядке возрастания.
 Если таких чисел несколько, найти первое из них.

7. Найти число, состоящее только из различных цифр.
 Если таких чисел несколько, найти первое из них.*/
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

    private static void delimiter() {
        System.out.println("-".repeat(50));
    }

    private static void printInIncreasingOrder(String[] numbersInString) {
        String[] localString = Arrays.copyOf(numbersInString, numbersInString.length);
        List<String> strings = Arrays.asList(localString);
        strings.sort(Comparator.comparingInt(String::length));
        System.out.println("Ordered incr. : " + strings);
    }

    private static void printInDecreasingOrder(String[] numbersInString) {
        String[] localString = Arrays.copyOf(numbersInString, numbersInString.length);
        List<String> strings = Arrays.asList(localString);
        strings.sort((leftString, rightString) -> rightString.length() - leftString.length());
        System.out.println("Ordered decr. : " + strings);
    }

    private static double getAverageLength(String[] numbers) {
        int lengthsSum = 0;
        for (String number : numbers) {
            lengthsSum += number.length();
        }

        double averageLength = (double)lengthsSum / numbers.length;
        System.out.println("Average length = " + averageLength);
        delimiter();
        return averageLength;
    }

    private static void printNumbersRelativeToAverage(String[] numbers, double averageLength) {

        System.out.println("Numbers, longer than average: ");
        for (String number : numbers) {
            if (number.length() > averageLength) {
                System.out.println(number + ", length = " + number.length());
            }
        }
        delimiter();
        System.out.println("Numbers, shorter than average: ");
        for (String number : numbers) {
            if (number.length() < averageLength) {
                System.out.println(number + ", length = " + number.length());
            }
        }
    }

    private static void checkForCondition4(String[] numbersInString) {

        int minDigitsCounter = Integer.MAX_VALUE;
        String numberWithLeastDifferentDigits = "";

        for (String numberInString : numbersInString) {

            String digits = "[0123456789]";
            Pattern pattern = Pattern.compile(digits);
            Matcher matcher = pattern.matcher(numberInString);
            int localCounter = 0;

            while (matcher.find()) {
                String group = matcher.group();
                localCounter++;
                digits = digits.replace(group, "");

                pattern = Pattern.compile(digits);
                matcher = pattern.matcher(numberInString);
            }

            if (localCounter < minDigitsCounter) {
                minDigitsCounter = localCounter;
                numberWithLeastDifferentDigits = numberInString;
            }
        }

        System.out.println("Number with least different numbers: " + numberWithLeastDifferentDigits);
    }

    private static void checkForCondition5(String[] numbersInString) {
        /*5.Найти количество чисел, содержащих только четные цифры,
        а среди оставшихся — количество чисел с равным числом четных и нечетных цифр.
         */

        String[] localArray = Arrays.copyOf(numbersInString, numbersInString.length);
        List<String> localArrayList = Arrays.asList(localArray);

        int localCounter = 0;

        for (int i = 0; i < localArray.length; i++) {
            String[] digitsOfNumber = localArray[i].split("");
            boolean areAllEven = true;
            for (String digitOfNumber : digitsOfNumber) {

                int number = Integer.parseInt(digitOfNumber);
                if (number % 2 != 0) {
                    areAllEven = false;
                    break;
                }
            }

            if (areAllEven) {
                localCounter++;
            }
        }
        for (String numberInString : localArray) {


        }


        System.out.println("Amount of number with all even digits = " + localCounter);

    }
}
