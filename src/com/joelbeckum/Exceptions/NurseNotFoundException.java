package com.joelbeckum.Exceptions;

public class NurseNotFoundException extends Exception {
    public NurseNotFoundException(String nurseName) {
        super(nurseName + " does not exist");
    }
}