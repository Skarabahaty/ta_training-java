package com.epam.training.maksim_skarabahaty.main_task;

import java.util.Random;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Enter digits amount: ");
        int digitsAmount = scanner.nextInt();

        int[] digitsArray = new int[digitsAmount];

        for (int i = 0; i < digitsAmount; i++) {
            digitsArray[i] = random.nextInt(0, 11);
            System.out.println(digitsArray[i]);
        }

        for (int digit : digitsArray) {
            System.out.print(digit + " ");
        }


    }
}
