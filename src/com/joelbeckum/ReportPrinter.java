package com.joelbeckum;

import java.util.List;

public class ReportPrinter {

    public void printAvailableRoomsReport(List<AvailableRoomsReportRow> availableRoomsList) {

        if(availableRoomsList.isEmpty()) {
            System.out.println("There are currently no empty rooms available");
            return;
        }

        System.out.println("Available room:\tAssigned nurse:");

        for (AvailableRoomsReportRow row : availableRoomsList) {

            String formattedNurseName = row.getAssignedNurse();
            if(formattedNurseName == null) {
                formattedNurseName = "No nurse assigned";
            }

            System.out.println("Room\t" + row.getRoomNumber() + "\t-\t" + formattedNurseName);
        }
    }

    public void printNurseCaseloadReport(String nurseName, List<NurseCaseloadReportRow> caseloadList) {

        if(caseloadList.isEmpty()) {
            System.out.println(nurseName + " is not currently assigned to any cases");
            return;
        }

        System.out.println(nurseName + "'s current case assignments:");

        for (NurseCaseloadReportRow row : caseloadList) {

            String formattedPatientName = row.getAssignedPatient();
            if(formattedPatientName == null) {
                formattedPatientName = "No patient assigned";
            }

            System.out.println("Room " + row.getAssignedRoom() + "\t-\t" + formattedPatientName);
        }
    }
}
