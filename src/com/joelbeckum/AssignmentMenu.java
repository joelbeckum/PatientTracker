package com.joelbeckum;

import com.joelbeckum.Repositories.AssignmentData;
import com.joelbeckum.Repositories.NurseData;
import com.joelbeckum.Repositories.PatientData;
import com.joelbeckum.Repositories.RoomData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class AssignmentMenu {

    public void launchAssignmentMenu() {
        Scanner input = new Scanner(System.in);
        NurseData nurseData = new NurseData();
        PatientData patientData = new PatientData();
        RoomData roomData = new RoomData();
        AssignmentData assignmentData = new AssignmentData();
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
                    try {
                        System.out.println("Enter room number for assignment");
                        int roomNumber = input.nextInt();
                        input.nextLine();
                        if(!roomData.roomExists(roomNumber)) {
                            System.out.println("Action unsuccessful: Room " + roomNumber + " doesn't exist");
                            break;
                        }

                        System.out.println("Enter name of the nurse to be assigned to Room " + roomNumber);
                        String nurseName = input.nextLine();
                        if(!nurseData.nurseExists(nurseName)) {
                            System.out.println("Action unsuccessful: " + nurseName + " doesn't exist");
                            break;
                        }

                        assignmentData.assignNurse(roomNumber, nurseName);
                        System.out.println(nurseName + " successfully assigned to Room " + roomNumber);
                    } catch(IOException | SQLException e) {
                        System.out.println("Action unsuccessful: ");
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("Placeholder text for unassignNurse() method");
                    break;

                case 3:
                    try {
                        System.out.println("Enter room number for assignment");
                        int roomNumber = input.nextInt();
                        input.nextLine();
                        if(!roomData.roomExists(roomNumber)) {
                            System.out.println("Action unsuccessful: Room " + roomNumber + " doesn't exist");
                            break;
                        }

                        System.out.println("Enter last name, first name of the patient to be assigned to Room " + roomNumber);
                        String patientName = input.nextLine();
                        if(!patientData.patientExists(patientName)) {
                            System.out.println("Action unsuccessful: " + patientName + " doesn't exist");
                            break;
                        }

                        assignmentData.assignPatient(roomNumber, patientName);
                        System.out.println(patientName + " successfully assigned to Room " + roomNumber);
                    } catch(IOException | SQLException e) {
                        System.out.println("Action unsuccessful: ");
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter room number for patient removal");
                        int roomNumber = input.nextInt();
                        input.nextLine();
                        if(!roomData.roomExists(roomNumber)) {
                            System.out.println("Action unsuccessful: Room " + roomNumber + " doesn't exist");
                            break;
                        }

                        if(assignmentData.getAssignedPatient(roomNumber) == null) {
                            System.out.println("There is currently no patient assigned to Room " + roomNumber);
                            break;
                        }

                        String unassignedPatient = assignmentData.getAssignedPatient(roomNumber);
                        assignmentData.unassignPatient(roomNumber);
                        System.out.println(unassignedPatient + " successfully removed from Room " + roomNumber);
                    } catch(IOException | SQLException e) {
                        System.out.println("Action unsuccessful: ");
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    System.out.println("Placeholder text for displayRoomAssignments() method");
                    break;

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
