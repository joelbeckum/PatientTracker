package com.joelbeckum;

public class Room {
    private int id;
    private int roomNumber;
    private int assignedNurse;
    private int assignedPatient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getAssignedNurse() {
        return assignedNurse;
    }

    public void setAssignedNurse(int assignedNurse) {
        this.assignedNurse = assignedNurse;
    }

    public int getAssignedPatient() {
        return assignedPatient;
    }

    public void setAssignedPatient(int assignedPatient) {
        this.assignedPatient = assignedPatient;
    }
}
