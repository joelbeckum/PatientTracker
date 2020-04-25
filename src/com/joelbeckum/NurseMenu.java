package com.joelbeckum;

import com.joelbeckum.Exceptions.NurseAlreadyExistsException;
import com.joelbeckum.Exceptions.NurseNotFoundException;
import com.joelbeckum.Repositories.Datasource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class NurseMenu {

    public void launchNurseMenu() {
        Scanner input = new Scanner(System.in);
        Datasource datasource = new Datasource();
        boolean shouldExit = false;
        printNurseMenu();

        while(!shouldExit) {
            System.out.println("Choose your action: (0 to show a list of available actions)");
            int action = input.nextInt();
            input.nextLine();

            switch(action) {

                case 0:
                    printNurseMenu();
                    break;

                case 1:
                    try {
                        System.out.println("Enter the name of the new nurse");

                        String nurseInput = input.nextLine();
                        datasource.addNurse(nurseInput);
                        System.out.println(nurseInput + " added to the database");
                    } catch(IOException | SQLException e) {
                        System.out.println("Record addition unsuccessful:");
                        e.printStackTrace();
                    } catch(NurseAlreadyExistsException e) {
                        System.out.println("Addition unsuccessful: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Enter the name of the nurse to be removed");

                        String nurseInput = input.nextLine();
                        datasource.removeNurse(nurseInput);
                        System.out.println(nurseInput + " removed from the database");
                    } catch(IOException | SQLException e) {
                        System.out.println("Removal unsuccessful: ");
                        e.printStackTrace();
                    } catch(NurseNotFoundException e) {
                        System.out.println("Removal unsuccessful: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Enter name of nurse to be updated");
                        String currentNurseInput = input.nextLine();
                        System.out.println("Enter new name for " + currentNurseInput);
                        String newNurseInput = input.nextLine();

                        datasource.renameNurse(currentNurseInput, newNurseInput);
                        System.out.println(currentNurseInput + " was renamed to " + newNurseInput);
                    } catch(IOException | SQLException e) {
                        System.out.println("Action unsuccessful: ");
                        e.printStackTrace();
                    } catch(NurseNotFoundException e) {
                        System.out.println("Action unsuccessful: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Nurses on record:");
                        for (Nurse nurse : datasource.getNurses()) {
                            System.out.println(nurse.getName());
                        }
                    } catch(IOException | SQLException e) {
                        System.out.println("Query failed:");
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    System.out.println("Returning to main menu");
                    shouldExit = true;
                    break;

                default:
                    System.out.println("Invalid action: please select from available actions");
                    break;
            }
        }
    }

    private void printNurseMenu() {
        System.out.println("Available actions: press\n");
        System.out.println("0 - to display a list of available actions");
        System.out.println("1 - to add a nurse to the database");
        System.out.println("2 - to remove a nurse from the database");
        System.out.println("3 - to rename a nurse in the database");
        System.out.println("4 - to display a list of nurses in the database");
        System.out.println("5 - to return to the main menu");
    }
}
