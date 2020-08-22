package com.joelbeckum.Repositories;

import java.io.IOException;
import java.sql.*;

public class AssignmentData extends Datasource {

    public void assignPatient(int roomNumber, String patientName) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT id FROM patients WHERE name = '" + patientName + "'")) {
            int patientID = results.getInt(1);
            statement.execute("UPDATE rooms SET assignedPatient = " + patientID + " WHERE roomNumber = " + roomNumber);
        }
    }

    public String getAssignedPatient(int roomNumber) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT assignedPatient FROM rooms WHERE roomNumber = " + roomNumber)) {

            int assignedPatientID = results.getInt(1);

            if (assignedPatientID == 0) {
                return null;
            }

            ResultSet assignedPatientResults = statement.executeQuery("SELECT name FROM patients WHERE id = " + assignedPatientID);

            String assignedPatient = assignedPatientResults.getString(1);

            return assignedPatient;
        }
    }

    public void unassignPatient(int roomNumber) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("UPDATE rooms SET assignedPatient = NULL WHERE roomNumber = " + roomNumber);
        }
    }

    public void assignNurse(int roomNumber, String nurseName) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT id FROM nurses WHERE name = '" + nurseName + "'")) {
            int nurseID = results.getInt(1);
            statement.execute("UPDATE rooms SET assignedNurse = " + nurseID + " WHERE roomNumber = " + roomNumber);
        }
    }

    public String getAssignedNurse(int roomNumber) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT assignedNurse FROM rooms WHERE roomNumber = " + roomNumber)) {

            int assignedNurseID = results.getInt(1);

            if (assignedNurseID == 0) {
                return null;
            }

            ResultSet assignedNurseResults = statement.executeQuery("SELECT name FROM nurses WHERE id = " + assignedNurseID);

            String assignedNurse = assignedNurseResults.getString(1);

            return assignedNurse;
        }
    }

    public void unassignNurse(int roomNumber) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("UPDATE rooms SET assignedNurse = NULL WHERE roomNumber = " + roomNumber);
        }
    }


}
