package com.joelbeckum.Repositories;

import com.joelbeckum.Exceptions.RoomAlreadyExistsException;
import com.joelbeckum.Exceptions.RoomNotFoundException;

import java.io.IOException;
import java.sql.*;

public class RoomData extends Datasource {

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
