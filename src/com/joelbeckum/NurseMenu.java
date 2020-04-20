package com.joelbeckum;

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
                        datasource.addNurse();
                    } catch(IOException | SQLException e) {
                        System.out.println("Record addition unsuccessful:");
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("Placeholder text for removing a nurse from the database");
                    break;

                case 3:
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

                case 4:
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
        System.out.println("3 - to display a list of nurses in the database");
        System.out.println("4 - to return to the main menu");
    }
}