package com.joelbeckum.Repositories;

import com.joelbeckum.Exceptions.NurseAlreadyExistsException;
import com.joelbeckum.Exceptions.NurseNotFoundException;
import com.joelbeckum.Nurse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseData extends Datasource {
    public List<Nurse> getNurses() throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement =  conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT id, name " +
                                                            "FROM nurses " +
                                                            "ORDER BY name ASC")) {

            List<Nurse> nurses = new ArrayList<>();

            while(results.next()) {
                Nurse nurse = new Nurse();

                nurse.setId(results.getInt(1));
                nurse.setName(results.getString(2));
                nurses.add(nurse);
            }
            return nurses;
        }
    }

    public void addNurse(String name) throws NurseAlreadyExistsException, IOException, SQLException {
        if (nurseExists(name)) {
            throw new NurseAlreadyExistsException(name);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("INSERT INTO nurses(name) " +
                                  "VALUES('" + name + "')");
        }
    }

    public void removeNurse(String name) throws NurseNotFoundException, IOException, SQLException {
        if(!nurseExists(name)) {
            throw new NurseNotFoundException(name);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("DELETE FROM nurses " +
                                  "WHERE name = '" + name + "'");
        }
    }

    public void renameNurse(String currentName, String newName) throws NurseNotFoundException, IOException, SQLException {
        if (!nurseExists(currentName)) {
            throw new NurseNotFoundException(currentName);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("UPDATE nurses " +
                                  "SET name = '" + newName + "' " +
                                  "WHERE name = '" + currentName + "'");
        }
    }

    public boolean nurseExists(String name) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT name " +
                                                            "FROM nurses " +
                                                            "WHERE name = '" + name + "'")) {

            return results.next();
        }
    }
}
