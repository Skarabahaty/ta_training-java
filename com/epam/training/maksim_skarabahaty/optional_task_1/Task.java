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


    public static final String DIGITS_SEPARATOR = "";
    public static final String EMPTY_STRING = "";
    public static final String DIGITS = "[0123456789]";

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

        /*
        5.Найти количество чисел, содержащих только четные цифры,
        а среди оставшихся — количество чисел с равным числом четных и нечетных цифр.
         */
        checkForCondition5(numbersInString);
        delimiter();//task 5 end

        checkForCondition6(numbersInString);
        delimiter();//task 6 end

        checkForCondition7(numbersInString);
        delimiter();//task 7 end
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

        String longestElement = EMPTY_STRING;
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
        System.out.println("Increasing order : " + strings);
    }

    private static void printInDecreasingOrder(String[] numbersInString) {
        String[] localString = Arrays.copyOf(numbersInString, numbersInString.length);
        List<String> strings = Arrays.asList(localString);
        strings.sort((leftString, rightString) -> rightString.length() - leftString.length());
        System.out.println("Decreasing order : " + strings);
    }

    private static double getAverageLength(String[] numbers) {
        int lengthsSum = 0;
        for (String number : numbers) {
            lengthsSum += number.length();
        }

        double averageLength = (double) lengthsSum / numbers.length;
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
        String numberWithLeastDifferentDigits = EMPTY_STRING;

        for (String numberInString : numbersInString) {

            String digits = DIGITS;
            Pattern pattern = Pattern.compile(digits);
            Matcher matcher = pattern.matcher(numberInString);
            int localCounter = 0;

            while (matcher.find()) {
                String group = matcher.group();
                localCounter++;
                digits = digits.replace(group, DIGITS_SEPARATOR);

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

        String[] localArray = Arrays.copyOf(numbersInString, numbersInString.length);

        int localCounterForAllEvenDigits = 0;

        for (int i = 0; i < localArray.length; i++) {
            String[] digitsOfNumber = localArray[i].split(DIGITS_SEPARATOR);
            boolean areAllEven = true;
            for (String digitOfNumber : digitsOfNumber) {

                int number = Integer.parseInt(digitOfNumber);
                if (number % 2 != 0) {
                    areAllEven = false;
                    break;
                }
            }

            if (areAllEven) {
                localCounterForAllEvenDigits++;
                localArray = removeElementFromArray(localArray, i);
            }
        }

        int localCounterForEqualOddsAndEvens = 0;
        for (String numberInString : localArray) {

            if (numberInString.length() % 2 == 0) {
                String[] digitsInNumber = numberInString.split(DIGITS_SEPARATOR);

                int localEvensCounter = 0;
                for (String digit : digitsInNumber) {
                    int number = Integer.parseInt(digit);
                    if (number % 2 == 0) {
                        localEvensCounter++;
                    }
                }
                if (localEvensCounter == digitsInNumber.length / 2) {
                    localCounterForEqualOddsAndEvens++;
                }
            }
        }

        System.out.println("Amount of numbers with all even digits = " + localCounterForAllEvenDigits);
        System.out.println("Amount of rest numbers with equal evens and odds = " + localCounterForEqualOddsAndEvens);
    }

    private static String[] removeElementFromArray(String[] localArray, int index) {
        String[] returnArray = new String[localArray.length - 1];
        if (index >= 0) {
            System.arraycopy(localArray, 0, returnArray, 0, index);
            System.arraycopy(localArray, index + 1, returnArray, index, localArray.length - index - 1);
        }
        return returnArray;
    }

    private static void checkForCondition6(String[] numbersInString) {

        String returnString = EMPTY_STRING;

        for (String numberInString : numbersInString) {

            String[] digitsInNumber = numberInString.split(DIGITS_SEPARATOR);

            int firstDigit = Integer.parseInt(digitsInNumber[0]);
            boolean isIncreasing = true;

            for (int i = 1; i < digitsInNumber.length; i++) {

                int digit = Integer.parseInt(digitsInNumber[i]);
                if (digit <= firstDigit) {
                    isIncreasing = false;
                    break;
                }
                firstDigit = digit;
            }

            if (isIncreasing) {
                returnString = numberInString;
                break;
            }
        }

        if (returnString.equals(EMPTY_STRING)) {
            System.out.println("No numbers with increasing digits");
        } else {
            System.out.println("Number, where digits increasing = " + returnString);
        }
    }

    private static void checkForCondition7(String[] numbersInString) {

        String returnString = EMPTY_STRING;

        for (String numberInString : numbersInString) {

            StringBuilder forDigitsSearch = new StringBuilder(EMPTY_STRING);
            String[] digitsInNumber = numberInString.split(DIGITS_SEPARATOR);

            for (String digit : digitsInNumber) {
                if (forDigitsSearch.indexOf(digit) == -1) {
                    forDigitsSearch.append(digit);
                } else {
                    break;
                }
            }

            if (forDigitsSearch.toString().equals(numberInString)) {
                returnString = numberInString;
                break;
            }
        }
        if (returnString.equals(EMPTY_STRING)) {
            System.out.println("No digits with all different digits");
        } else {
            System.out.println("Number with all different digits = " + returnString);
        }
    }
}
