package com.epam.training.maksim_skarabahaty.main_task;

public class Task4 {

    public static void main(String[] args) {

        int[] digitsArray = new int[args.length];
        int digitsSum = 0;
        int digitsProduct = 1;

        for (int i = 0; i < digitsArray.length; i++) {
            digitsArray[i] = Integer.parseInt(args[i]);
            digitsSum += digitsArray[i];
            digitsProduct *= digitsArray[i];
        }

        System.out.println("Sum of digits = " + digitsSum + " , product of digits = " + digitsProduct);

    }
}
