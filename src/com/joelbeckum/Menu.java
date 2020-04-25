package com.joelbeckum;

import com.joelbeckum.Exceptions.RoomAlreadyExistsException;
import com.joelbeckum.Repositories.Datasource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public void launchUserMenu() {
        Scanner input = new Scanner(System.in);
        Datasource datasource = new Datasource();
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
                    NurseMenu nm = new NurseMenu();
                    nm.launchNurseMenu();
                    break;

                case 6:
                    System.out.println("launchPatientMenu method will go here");
                    break;

                case 7:
                    try {
                        System.out.println("Enter room number to add to the database");

                        int roomInput = input.nextInt();
                        datasource.addRoom(roomInput);
                    } catch(IOException | SQLException e) {
                        System.out.println("Room addition unsuccessful");
                        e.printStackTrace();
                    } catch(RoomAlreadyExistsException e) {
                        System.out.println("Room addition unsuccessful: " + e.getMessage());
                    }
                    break;

                case 8:
                    System.out.println("Placeholder text for displaying available rooms");
                    break;

                case 9:
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
        System.out.println("5 - add, remove, rename, or display nurses in the database");
        System.out.println("6 - to add, remove, update, or display patients in the database");
        System.out.println("7 - to add room to the database");
        System.out.println("8 - to display available rooms");
        System.out.println("9 - to exit the application");
    }
}
