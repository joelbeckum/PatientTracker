package com.joelbeckum.Exceptions;

public class NurseAlreadyExistsException extends Exception {
    public NurseAlreadyExistsException(String nurseName) {
        super(nurseName + " already exists");
    }
}

