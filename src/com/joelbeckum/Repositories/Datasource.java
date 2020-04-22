package com.joelbeckum.Repositories;

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

    public void addNurse(String name) throws Exception, IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT name FROM nurses WHERE name = '" + name + "'")) {

            if(results.next() == false) {
                statement.execute("INSERT INTO nurses(name) VALUES('" + name + "')");
            } else {
                throw new Exception(name + " already exists");
            }

        } catch(IOException | SQLException e) {
            throw e;
        } catch(Exception e) {
            throw e;
        }
    }

    public void addRoom(int roomNumber) throws Exception, IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT roomNumber FROM rooms WHERE roomNumber = " + roomNumber)) {

            if(results.next() == false) {
                statement.execute("INSERT INTO rooms(roomNumber) Values(" + roomNumber + ")");
            } else {
                throw new Exception("Room " + roomNumber + " is already in the database");
            }
        } catch(IOException | SQLException e) {
            throw e;
        } catch(Exception e) {
            throw e;
        }
    }
}
