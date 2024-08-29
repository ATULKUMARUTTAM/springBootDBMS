package com.atuluttam.springBootDBMS.Exception;

public class NoSuchStudentExistsException extends RuntimeException {
    public NoSuchStudentExistsException(String message) {
        super(message);
    }
}
