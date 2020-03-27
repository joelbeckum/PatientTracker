package com.joelbeckum.Repositories;

import com.joelbeckum.Nurse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {

    private String getConnectionString() throws IOException {

        try (InputStream input = new FileInputStream("C:\\Users\\kille\\Documents\\PatientTracker\\src\\resources\\config.properties")) {

            Properties prop = new Properties();

            prop.load(input);

           return prop.getProperty("db.connectionString");

        } catch(IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public List<Nurse> getNurses() throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement =  conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM nurses ORDER BY name ASC")) {

            List<Nurse> nurses = new ArrayList<>();

            while(results.next()) {
                Nurse nurse = new Nurse();

                nurse.setId(results.getInt(1));
                nurse.setName(results.getString(2));
            }

            return nurses;
        } catch(IOException | SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            throw e;
        }
    }
}
