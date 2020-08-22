package com.joelbeckum.Repositories;

import com.joelbeckum.AvailableRoomsReportRow;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportData extends Datasource {
    public List<AvailableRoomsReportRow> getAvailableRooms() throws IOException, SQLException {
        String query = "SELECT roomNumber, name " +
                "FROM rooms " +
                "LEFT JOIN nurses " +
                "ON nurses.id = rooms.assignedNurse " +
                "WHERE assignedPatient is null";

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement =  conn.createStatement();
             ResultSet results = statement.executeQuery(query)) {

            List<AvailableRoomsReportRow> availableRooms = new ArrayList<>();

            while(results.next()) {
                AvailableRoomsReportRow row = new AvailableRoomsReportRow();
                row.setRoomNumber(results.getInt(1));
                row.setAssignedNurse(results.getString(2));

                availableRooms.add(row);
            }
            return availableRooms;
        }
    }
}
