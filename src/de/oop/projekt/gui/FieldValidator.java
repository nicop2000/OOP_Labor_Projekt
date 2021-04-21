package de.oop.projekt.gui;


import de.oop.projekt.main.*;

public class FieldValidator {

    public static boolean intFieldValid(String text) {
        return text.matches("[0-9]+");
    }

    public static boolean dateFieldValid(String text) {
        return text.matches("([0-9]{1,2})\\.([0-9]{2})\\.([0-9]{4})");
    }

    public static boolean nameFieldValid(String text) {
        return text != "";
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



