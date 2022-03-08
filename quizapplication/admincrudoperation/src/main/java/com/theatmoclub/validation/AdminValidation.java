package com.theatmoclub.validation;

public class AdminValidation {

    /**
     * Check correctAnswer
     *
     * @param correctAnswer
     */
    public boolean checkAnswer(final String correctAnswer) {
        return ("a".equalsIgnoreCase(correctAnswer) || "b".equalsIgnoreCase(correctAnswer) || "c".equalsIgnoreCase(correctAnswer) || "d".equalsIgnoreCase(correctAnswer)) ? true : false;
    }

    /**
     * Check firstOption
     *
     * @param firstOption
     */
    public boolean checkFirstOption(final String firstOption) {
        return firstOption.matches("[a]{1}[.]{1}[a-zA-Z0-9\\s]{1,}") ? true :false;
    }

    /**
     * Check secondOption
     *
     * @param secondOption
     */
    public boolean checkSecondOption(final String secondOption) {
        return secondOption.matches("[b]{1}[.]{1}[a-zA-Z0-9\\s]{1,}") ? true : false;
    }

    /**
     * Check thirdOption
     *
     * @param thirdOption
     */
    public boolean checkThirdOption(final String thirdOption) {
        return thirdOption.matches("[c]{1}[.]{1}[a-zA-Z0-9\\s]{1,}") ? true : false;
    }

    /**
     * Check fourthOption
     *
     * @param fourthOption
     */
    public boolean checkFourthOption(final String fourthOption) {
        return fourthOption.matches("[d]{1}[.]{1}[a-zA-Z0-9\\s]{1,}") ? true : false;
    }

}
