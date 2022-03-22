package com.epam.training.maksim_skarabahaty.main_task;


import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Task5 {

    static HashMap<Integer, String> months = new HashMap<>();
    static {
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int monthNumber = 0;

        try {
            monthNumber = scanner.nextInt();
        } catch (RuntimeException e) {
            System.out.println("Not a number in input");
        }

        String month = months.get(monthNumber);

        System.out.println(
                Objects.requireNonNullElse(
                        month,
                        "Something went wrong"
                )
        );
    }
}
