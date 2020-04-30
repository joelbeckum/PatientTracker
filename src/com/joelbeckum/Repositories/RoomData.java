package com.joelbeckum.Repositories;

import com.joelbeckum.Exceptions.RoomAlreadyExistsException;
import com.joelbeckum.Exceptions.RoomNotFoundException;
import com.joelbeckum.Room;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomData extends Datasource {

    public List<Room> getRooms() throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(getConnectionString());
             Statement statement =  conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT id, roomNumber FROM rooms ORDER BY roomNumber ASC")) {

            List<Room> rooms = new ArrayList<>();

            while(results.next()) {
                Room room = new Room();

                room.setId(results.getInt(1));
                room.setRoomNumber(results.getInt(2));
                rooms.add(room);
            }
            return rooms;
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
