package de.oop.projekt.gui;


import de.oop.projekt.main.*;

import java.util.Arrays;

public class Helper {

    public static boolean intFieldValid(String text) {
        return text.matches("[0-9]+");
    }

    public static boolean dateFieldValid(String text) {
        return text.matches("([0-9]{1,2})\\.([0-9]{1,2})\\.([0-9]{4})");
    }

    public static boolean nameFieldValid(String text) {
        return text != "";
    }

    public static Date stringToDate(String dateAsString) {
        //turns string from user-input to Date
        //if user-input is not a valid format, default Date 1.1.1970 is returned
        if (!Helper.dateFieldValid(dateAsString)) {
            return new Date(1, 1, 1970);
        }
        String[] dateAsArray = dateAsString.split("\\.");
        System.out.println(Arrays.toString(dateAsArray));
        int day = Integer.parseInt(dateAsArray[0]);
        int month = Integer.parseInt(dateAsArray[1]);
        int year = Integer.parseInt(dateAsArray[2]);
        return new Date(day, month, year);
    }

    public static void main(String[] args) {
        System.out.print(intFieldValid("Hallo") + "\n");
        System.out.print(intFieldValid("Hallo1") + "\n");
        System.out.print(intFieldValid("123") + "\n");
        System.out.print(intFieldValid("23%") + "\n");
        System.out.print(intFieldValid("Hallo") + "\n");
        System.out.println(intFieldValid("") + "\n\n");
        System.out.println("DATECHECK");
        System.out.println(dateFieldValid("7.12.2000"));
        System.out.println(dateFieldValid("07.12.2000"));
        System.out.println(dateFieldValid("712.2000"));
        System.out.println(dateFieldValid("7122000"));
        System.out.println(dateFieldValid(". . ."));
        System.out.println(dateFieldValid("7.122000"));

        TestSubject p = new TestSubject("n", "b", null, "m");



    }
}



