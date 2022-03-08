package com.theatmoclub.validation;

public class Validation {

    /**
     * Validate user name
     */
    public boolean validateName(final String name) {

        return name.matches("[A-Z][a-zA-Z\\s]*$") ? true : false;
    }

    /**
     * Validate user email
     */
    public boolean validateEmail(final String email) {
        return email.matches("^[A-Za-z0-9_-]*@[^-][A-Za-z0-9-]*(\\.[A-Za-z]{2,3})$") ? true : false;
    }

    /**
     * Validate user password
     */
    public boolean validatePassword(final String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$") ? true : false;
    }

    /**
     * Check correctAnswer
     *
     * @param correctAnswer
     */
    public boolean checkAnswer(final String correctAnswer) {
        return ("a".equalsIgnoreCase(correctAnswer) || "b".equalsIgnoreCase(correctAnswer) || "c".equalsIgnoreCase(correctAnswer) || "d".equalsIgnoreCase(correctAnswer)) ? true : false;
    }
}
