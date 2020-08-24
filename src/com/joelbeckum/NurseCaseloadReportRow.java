package com.joelbeckum;

public class NurseCaseloadReportRow {
    private String nurseName;
    private int assignedRoom;
    private String assignedPatient;

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public int getAssignedRoom() {
        return assignedRoom;
    }

    public void setAssignedRoom(int assignedRoom) {
        this.assignedRoom = assignedRoom;
    }

    public String getAssignedPatient() {
        return assignedPatient;
    }

    public void setAssignedPatient(String assignedPatient) {
        this.assignedPatient = assignedPatient;
    }
}
