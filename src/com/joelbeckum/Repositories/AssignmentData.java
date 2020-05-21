package com.joelbeckum.Repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AssignmentData extends Datasource {

    public void assignPatient(int roomNumber, String patientName) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("UPDATE rooms SET assignedPatient = '" + patientName + "' WHERE roomNumber = " + roomNumber);
        } catch(IOException | SQLException e) {
            throw e;
        }
    }
}
