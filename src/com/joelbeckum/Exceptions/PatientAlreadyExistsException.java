package com.joelbeckum.Exceptions;

public class PatientAlreadyExistsException extends Exception {
    public PatientAlreadyExistsException(String patientName) {
        super(patientName + " already exists");
    }
}

