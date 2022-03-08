package com.theatmoclub.validation;

public class UserValidation {

    /**
     * Validate Mark
     *
     * @param mark
     * @param answer
     * @param correctAnswer
     */
    public  int markCalculation(int mark, final String answer, final String correctAnswer) {

        if (correctAnswer.equalsIgnoreCase(answer)) {
            mark = mark+2;
        } else {
            mark = mark-1;
        }
        return mark;
    }

    /**
     * Check mark pass or fail
     *
     * @param mark
     * @param email
     */
    public boolean checkMark(int mark, final String email) {

        if (mark <= 6) {
            return false;
        } else {
            return true;
        }
    }
}
