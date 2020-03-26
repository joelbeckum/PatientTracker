package com.joelbeckum;

import java.util.Scanner;

public class Menu {

    public void launchUserMenu() {
        Scanner input = new Scanner(System.in);
        boolean shouldExit = false;
        System.out.println("Welcome to PatientTracker!");
        printMenu();

        while(!shouldExit) {
            System.out.println("\nChoose your action: (6 to show a list of available actions)");
            int action = input.nextInt();
            input.nextLine();

            switch(action) {
                case 1:
                    System.out.println("Placeholder text for assigning a patient");
                    break;

                case 2:
                    System.out.println("Placeholder text for removing a patient");
                    break;

                case 3:
                    System.out.println("Placeholder text for assigning a nurse");
                    break;

                case 4:
                    System.out.println("Placeholder text for removing a nurse");
                    break;

                case 5:
                    System.out.println("Placeholder text for displaying available rooms");
                    break;

                case 6:
                    printMenu();
                    break;

                case 7:
                    System.out.println("Thank you for using PatientTracker!");
                    shouldExit = true;
                    break;

                default:
                    System.out.println("Invalid action - please select from available actions");
                    break;
            }
        }


    }

    private void printMenu() {
//        System.out.println("\nAvailable actions: \npress");
//        System.out.println("1 - to assign a patient to a room\n" +
//                           "2 - to remove a patient from a room\n" +
//                           "3 - to assign a nurse to a room\n" +
//                           "4 - to remove a nurse from a room\n" +
//                           "5 - to display available rooms\n" +
//                           "6 - to print a list of available actions\n" +
//                           "7 - to exit the application");
        System.out.println("Available actions: \npress");
        System.out.println("1 - to assign a patient to a room");
        System.out.println("2 - to remove a patient from a room");
        System.out.println("3 - to assign a nurse to a room");
        System.out.println("4 - to remove a nurse from a room");
        System.out.println("5 - to display available rooms");
        System.out.println("6 - to print a list of available actions");
        System.out.println("7 - to exit the application");
    }
}
