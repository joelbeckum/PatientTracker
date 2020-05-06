package com.joelbeckum.Exceptions;

public class PatientNotFoundException extends Exception {
    public PatientNotFoundException(String patientName) {
        super(patientName + " does not exist");
    }
}