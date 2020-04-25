package com.joelbeckum.Exceptions;

public class RoomAlreadyExistsException extends Exception {
    public RoomAlreadyExistsException(int roomNumber) {
        super("Room " + roomNumber + " already exists");
    }
}
