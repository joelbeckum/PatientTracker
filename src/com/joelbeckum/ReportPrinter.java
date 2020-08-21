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
            System.out.println("Room\t" + row.getRoomNumber() + "\t-\t" + row.getAssignedNurse());
        }
    }
}
