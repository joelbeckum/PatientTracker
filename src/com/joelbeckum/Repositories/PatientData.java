package com.joelbeckum.Repositories;

import com.joelbeckum.Patient;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientData extends Datasource {

    public List<Patient> getPatients() throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT id, name FROM patients ORDER BY name ASC")) {

            List<Patient> patients = new ArrayList<>();

            while (results.next()) {
                Patient patient = new Patient();

                patient.setId(results.getInt(1));
                patient.setName(results.getString(2));
                patients.add(patient);
            }
            return patients;
        } catch (IOException | SQLException e) {
            throw e;
        }
    }

    private boolean patientExists(String name) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT name FROM patients WHERE name = '" + name + "'")) {

            return results.next();
        } catch(IOException | SQLException e) {
            throw e;
        }
    }
}
