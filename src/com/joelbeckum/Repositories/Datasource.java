package com.joelbeckum.Repositories;

import com.joelbeckum.Exceptions.NurseAlreadyExistsException;
import com.joelbeckum.Exceptions.NurseNotFoundException;
import com.joelbeckum.Exceptions.RoomAlreadyExistsException;
import com.joelbeckum.Exceptions.RoomNotFoundException;
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
        if (nurseExists(name)) {
            throw new NurseAlreadyExistsException(name);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
                statement.execute("INSERT INTO nurses(name) VALUES('" + name + "')");
        } catch (IOException | SQLException e) {
            throw e;
        }
    }

    public void removeNurse(String name) throws NurseNotFoundException, IOException, SQLException {
        if(!nurseExists(name)) {
            throw new NurseNotFoundException(name);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("DELETE FROM nurses WHERE name = '" + name + "'");
        } catch(IOException | SQLException e) {
            throw e;
        }
    }

    public void renameNurse(String currentName, String newName) throws NurseNotFoundException, IOException, SQLException {
        if (!nurseExists(currentName)) {
            throw new NurseNotFoundException(currentName);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
                statement.execute("UPDATE nurses SET name = '" + newName + "' WHERE name = '" + currentName + "'");
        } catch(IOException | SQLException e) {
            throw e;
        }
    }

    private boolean nurseExists(String name)  throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
        Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery("SELECT name FROM nurses WHERE name = '" + name + "'")) {

            return results.next();
        } catch(IOException | SQLException e) {
            throw e;
        }
    }

    public void addRoom(int roomNumber) throws RoomAlreadyExistsException, IOException, SQLException {
        if(roomExists(roomNumber)) {
            throw new RoomAlreadyExistsException(roomNumber);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("INSERT INTO rooms(roomNumber) Values(" + roomNumber + ")");
        } catch(IOException | SQLException e) {
            throw e;
        }
    }

    public void removeRoom(int roomNumber) throws RoomNotFoundException, IOException, SQLException {
        if(!roomExists(roomNumber)) {
            throw new RoomNotFoundException(roomNumber);
        }

        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement()) {
            statement.execute("DELETE FROM rooms WHERE roomNumber = " + roomNumber);
        } catch(IOException | SQLException e) {
            throw e;
        }
    }

    private boolean roomExists(int roomNumber) throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT roomNumber FROM rooms WHERE roomNumber = " + roomNumber)) {

            return results.next();
        } catch(IOException | SQLException e) {
            throw e;
        }
    }
}
