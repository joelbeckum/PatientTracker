package com.joelbeckum;

import java.util.Scanner;

public class Menu {
    private final NurseMenu nurseMenu;
    private final RoomMenu roomMenu;
    private final PatientMenu patientMenu;
    private final AssignmentMenu assignmentMenu;

    public Menu() {
        this.nurseMenu = new NurseMenu();
        this.roomMenu = new RoomMenu();
        this.patientMenu = new PatientMenu();
        this.assignmentMenu = new AssignmentMenu();
    }

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
                    assignmentMenu.launchAssignmentMenu();
                    break;

                case 2:
                    nurseMenu.launchNurseMenu();
                    break;

                case 3:
                    patientMenu.launchPatientMenu();
                    break;

                case 4:
                    roomMenu.launchRoomMenu();
                    break;

                case 5:
                    System.out.println("Placeholder text for displaying available rooms");
                    break;

                case 6:
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
        System.out.println("1 - to assign nurses and patients to rooms");
        System.out.println("2 - to add, remove, rename, or display nurses in the database");
        System.out.println("3 - to add, remove, update, or display patients in the database");
        System.out.println("4 - to add, remove, update, or display rooms in the database");
        System.out.println("5 - to display available rooms");
        System.out.println("6 - to exit the application");
    }
}
