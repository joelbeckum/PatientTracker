package com.joelbeckum.Repositories;

import com.joelbeckum.Exceptions.NurseAlreadyExistsException;
import com.joelbeckum.Exceptions.NurseNotFoundException;
import com.joelbeckum.Exceptions.RoomAlreadyExistsException;
import com.joelbeckum.Nurse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Datasource {
    private String getConnectionString() throws IOException {
        try (InputStream input = new FileInputStream("./src/resources/config.properties")) {

            Properties prop = new Properties();

            prop.load(input);

           return prop.getProperty("db.connectionString");

        } catch(IOException e) {
            throw e;
        }
    }

    public List<Nurse> getNurses() throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement =  conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT id, name FROM nurses ORDER BY name ASC")) {

            List<Nurse> nurses = new ArrayList<>();

            while(results.next()) {
                Nurse nurse = new Nurse();

                nurse.setId(results.getInt(1));
                nurse.setName(results.getString(2));
                nurses.add(nurse);
            }
            return nurses;
        } catch(IOException | SQLException e) {
            throw e;
        }
    }

    public void addNurse(String name) throws NurseAlreadyExistsException, IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT name FROM nurses WHERE name = '" + name + "'")) {

            if(!results.next()) {
                statement.execute("INSERT INTO nurses(name) VALUES('" + name + "')");
            } else {
                throw new NurseAlreadyExistsException(name);
            }

        } catch(IOException | SQLException | NurseAlreadyExistsException e) {
            throw e;
        }
    }

    public void removeNurse(String name) throws NurseNotFoundException, IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
        Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery("SELECT name FROM nurses WHERE name = '" + name + "'")) {

        if(results.next()) {
            statement.execute("DELETE FROM nurses WHERE name = '" + name + "'");
        } else {
            throw new NurseNotFoundException(name);
        }
        } catch(IOException | SQLException | NurseNotFoundException e) {
            throw e;
        }
    }

    public void renameNurse(String currentName, String newName) throws NurseNotFoundException, IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
        Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery("SELECT name FROM nurses WHERE name = '" + currentName + "'")) {
            if(results.next()) {
                statement.execute("UPDATE nurses SET name = '" + newName + "' WHERE name = '" + currentName + "'");
            } else {
                throw new NurseNotFoundException(currentName);
            }
        } catch(IOException | SQLException | NurseNotFoundException e) {
            throw e;
        }
    }


    public void addRoom(int roomNumber) throws RoomAlreadyExistsException, IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT roomNumber FROM rooms WHERE roomNumber = " + roomNumber)) {

            if(!results.next()) {
                statement.execute("INSERT INTO rooms(roomNumber) Values(" + roomNumber + ")");
            } else {
                throw new RoomAlreadyExistsException(roomNumber);
            }
        } catch(IOException | SQLException | RoomAlreadyExistsException e) {
            throw e;
        }
    }
}
