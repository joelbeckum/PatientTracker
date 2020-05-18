package com.joelbeckum;

import com.joelbeckum.Repositories.NurseData;
import com.joelbeckum.Repositories.PatientData;
import com.joelbeckum.Repositories.RoomData;

import java.util.Scanner;

public class AssignmentMenu {

    public void launchAssignmentMenu() {
        Scanner input = new Scanner(System.in);
        NurseData nurseData = new NurseData();
        PatientData patientData = new PatientData();
        RoomData roomData = new RoomData();
        boolean shouldExit = false;
        printAssignmentMenu();

        while(!shouldExit) {
            System.out.println("Choose your action: (0 to show a list of available actions)");
            int action = input.nextInt();
            input.nextLine();

            switch(action) {
                case 0:
                    printAssignmentMenu();
                    break;

                case 1:
                    System.out.println("Placeholder text for assignNurse() method");
                    break;

                case 2:
                    System.out.println("Placeholder text for unassignNurse() method");
                    break;

                case 3:
                    System.out.println("Placeholder text for assignPatient() method");
                    break;

                case 4:
                    System.out.println("Placeholder text for unassignPatient() method");
                    break;

                case 5:
                    System.out.println("Placeholder text for displayRoomAssignments() method");

                case 6:
                    System.out.println("Returning to main menu");
                    shouldExit = true;
                    break;

                default:
                    System.out.println("Invalid action: please select from available actions");
                    break;
            }
        }
    }

    private void printAssignmentMenu() {
        System.out.println("Available actions: press\n");
        System.out.println("0 - to display a list of available actions");
        System.out.println("1 - to assign a nurse to an available room");
        System.out.println("2 - to remove an assigned nurse from a room");
        System.out.println("3 - to assign a patient to an available room");
        System.out.println("4 - to remove an assigned patient from a room");
        System.out.println("5 - to display current room assignments");
        System.out.println("6 - to return to the main menu");
    }
}
