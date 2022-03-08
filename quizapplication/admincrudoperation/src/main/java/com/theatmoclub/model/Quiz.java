package com.theatmoclub.model;

public class Quiz {
    private int questionNumber;
    private String questions;
    private String firstOption;
    private String secondOption;
    private String thirdOption;
    private String fourthOption;
    private String correctAnswer;

    public Quiz(int questionNumber, String questions, String firstOption, String secondOption,
                String thirdOption, String fourthOption, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.questions = questions;
        this.firstOption = firstOption;
        this.secondOption = secondOption;
        this.thirdOption = thirdOption;
        this.fourthOption = fourthOption;
        this.correctAnswer = correctAnswer;
    }

    public Quiz() {

    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(String firstOption) {
        this.firstOption = firstOption;
    }

    public String getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(String secondOption) {
        this.secondOption = secondOption;
    }

    public String getThirdOption() {
        return thirdOption;
    }

    public void setThirdOption(String thirdOption) {
        this.thirdOption = thirdOption;
    }

    public String getFourthOption() {
        return fourthOption;
    }

    public void setFourthOption(String fourthOption) {
        this.fourthOption = fourthOption;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String toString() {
        return String.format("%s.%s\n%s\n%s\n%s\n%s", questionNumber, questions, firstOption, secondOption, thirdOption, fourthOption);
    }
}

