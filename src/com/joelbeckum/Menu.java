package com.joelbeckum;

import com.joelbeckum.Repositories.Datasource;

import java.util.Scanner;

public class Menu {

    public void launchUserMenu() {
        Scanner input = new Scanner(System.in);
        boolean shouldExit = false;
        System.out.println("Welcome to PatientTracker!");
        printMenu();

        while(!shouldExit) {
            System.out.println("\nChoose your action: (0 to show a list of available actions)");
            int action = input.nextInt();
            input.nextLine();

            switch(action) {

                case 0:
                    printMenu();
                    break;

                case 1:
                    System.out.println("Placeholder text for assigning a patient to a room");
                    break;

                case 2:
                    System.out.println("Placeholder text for removing a patient from a room");
                    break;

                case 3:
                    System.out.println("Placeholder text for assigning a nurse to a room");
                    break;

                case 4:
                    System.out.println("Placeholder text for removing a nurse from a room");
                    break;

                case 5:
                    Datasource ds = new Datasource();
                    ds.addNurse();
                    break;

                case 6:
                    System.out.println("Placeholder text for removing a nurse from the database");
                    break;

                case 7:
                    System.out.println("Placeholder text for adding a patient to the database");
                    break;

                case 8:
                    System.out.println("Placeholder text for removing a patient from the database");
                    break;

                case 9:
                    System.out.println("Placeholder text for displaying available rooms");
                    break;

                case 10:
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
        System.out.println("Available actions: \npress");
        System.out.println("0 - to print a list of available actions");
        System.out.println("1 - to assign a patient to a room");
        System.out.println("2 - to remove a patient from a room");
        System.out.println("3 - to assign a nurse to a room");
        System.out.println("4 - to remove a nurse from a room");
        System.out.println("5 - to add a nurse to the database");
        System.out.println("6 - to remove a nurse from the database");
        System.out.println("7 - to add a patient to the database");
        System.out.println("8 - to remove a patient from the database");
        System.out.println("9 - to display available rooms");
        System.out.println("10 - to exit the application");
    }
}
