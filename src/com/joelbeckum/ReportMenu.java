package com.joelbeckum;

import com.joelbeckum.Repositories.NurseData;
import com.joelbeckum.Repositories.ReportData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ReportMenu {

    public void launchReportMenu() {
        Scanner input = new Scanner(System.in);
        NurseData nurseData = new NurseData();
        ReportData reportData = new ReportData();
        ReportPrinter reportPrinter = new ReportPrinter();
        boolean shouldExit = false;
        printReportMenu();

        while(!shouldExit) {
            System.out.println("Choose your action: (0 to show a list of available actions)");
            int action = input.nextInt();
            input.nextLine();

            switch(action) {

                case 0:
                    printReportMenu();
                    break;

                case 1:
                    try {
                        List<AvailableRoomsReportRow> availableRoomsList = reportData.getAvailableRooms();
                        reportPrinter.printAvailableRoomsReport(availableRoomsList);
                    } catch(IOException | SQLException e) {
                        System.out.println("Report unsuccessful:");
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("placeholder text for caseload reports");
                    break;

                case 3:
                    System.out.println("Returning to main menu");
                    shouldExit = true;
                    break;


                default:
                    System.out.println("Invalid action: please select from available actions");
                    break;
            }
        }
    }

    private void printReportMenu() {
        System.out.println("Available actions: press\n");
        System.out.println("0 - to display a list of available actions");
        System.out.println("1 - to display available rooms");
        System.out.println("2 - to display case load for selected nurse");
        System.out.println("3 - to return to the main menu");
    }
}
