package com.joelbeckum;

import com.joelbeckum.Exceptions.PatientAlreadyExistsException;
import com.joelbeckum.Repositories.PatientData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class PatientMenu {

    public void launchPatientMenu() {
        Scanner input = new Scanner(System.in);
        PatientData patientData = new PatientData();
        boolean shouldExit = false;
        printPatientMenu();

        while(!shouldExit) {
            System.out.println("Choose your action: (0 to show a list of available actions)");
            int action = input.nextInt();
            input.nextLine();

            switch(action) {

                case 0:
                    printPatientMenu();
                    break;

                case 1:
                    try {
                        System.out.println("Enter last name, first name of the new patient");
                        String patientInput = input.nextLine();
                        System.out.println("Enter " + patientInput + "'s prescriptions separated by commas");
                        String prescriptionInput = input.nextLine();
                        System.out.println("Enter " + patientInput + "'s treatments separated by commas");
                        String treatmentInput = input.nextLine();

                        patientData.addPatient(patientInput, prescriptionInput, treatmentInput);
                        System.out.println(patientInput + " added to the database");
                    } catch(IOException | SQLException e) {
                        System.out.println("Record addition unsuccessful:");
                        e.printStackTrace();
                    } catch(PatientAlreadyExistsException e) {
                        System.out.println("Addition unsuccessful: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Placeholder for removePatient() method");
                    break;

                case 3:
                    System.out.println("Placeholder for updatePatient() method");
                    break;

                case 4:
                    try {
                        System.out.println("Patients on record:");
                        for (Patient patient : patientData.getPatients()) {
                            System.out.println(patient.getName());
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

    public void printPatientMenu() {
        System.out.println("Available actions: press\n");
        System.out.println("0 - to display a list of available actions");
        System.out.println("1 - to add a patient to the database");
        System.out.println("2 - to remove a patient from the database");
        System.out.println("3 - to update a patient in the database");
        System.out.println("4 - to display a list of patients in the database");
        System.out.println("5 - to return to the main menu");
    }
}
