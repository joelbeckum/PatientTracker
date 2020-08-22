package com.joelbeckum.Repositories;

import com.joelbeckum.Exceptions.PatientAlreadyExistsException;
import com.joelbeckum.Exceptions.PatientNotFoundException;
import com.joelbeckum.Patient;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientData extends Datasource {

    public List<Patient> getPatients() throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT id, name, prescriptions, treatments FROM patients ORDER BY name ASC")) {

            List<Patient> patients = new ArrayList<>();

            while (results.next()) {
                Patient patient = new Patient();

                patient.setId(results.getInt(1));
                patient.setName(results.getString(2));
                patients.add(patient);
            }
            return patients;
        }
    }

    public void addPatient(String name, String prescription, String treatment) throws PatientAlreadyExistsException, IOException, SQLException {
        if (patientExists(name)) {
            throw new PatientAlreadyExistsException(name);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("INSERT INTO patients(name, prescriptions, treatments) VALUES('" + name + "', '" + prescription + "', '" + treatment + "')");
        }
    }

    public void removePatient(String name) throws PatientNotFoundException, IOException, SQLException {
        if(!patientExists(name)) {
            throw new PatientNotFoundException(name);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("DELETE FROM patients WHERE name = '" + name + "'");
        }
    }

    public void updatePatient(String currentName, String newName, String prescription, String treatment) throws PatientNotFoundException, IOException, SQLException {
        if (!patientExists(currentName)) {
            throw new PatientNotFoundException(currentName);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("UPDATE patients SET name = '" + newName + "', prescriptions = '" + prescription + "', treatments = '" + treatment + "' WHERE name = '" + currentName + "'");
        }
    }

    public boolean patientExists(String name) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT name FROM patients WHERE name = '" + name + "'")) {

            return results.next();
        }
    }
}
