package com.theatmoclub.exception;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }


    public static class ConnectionException extends CustomException {

        public ConnectionException(String message) {
            super(message);
        }
    }
}
