package com.theatmoclub.view;

import com.theatmoclub.controller.CrudController;
import com.theatmoclub.model.Quiz;
import com.theatmoclub.validation.AdminValidation;

import java.util.Scanner;

public class AdminView {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final AdminValidation ADMIN_VALIDATION = new AdminValidation();
    /**
     * adminServices
     */
    public static void adminServices() {
        System.out.println("1.Insert \n2.Update \n3.Delete \nEnter Your Choice");

        try {
            int choice = Integer.parseInt(getChoice());

            if (choice == 1) {
                AdminView.insertQuestion(choice);
            } else if (choice == 2) {
                AdminView.updateQuestion(choice);
            } else if (choice == 3) {
                AdminView.deleteQuestion(choice);
            } else if(choice == 4) {
                 System.out.println("Enter Number [1,2,3]");
                AdminView.adminServices();
            }
        } catch (Exception e) {
            System.out.println("Enter the valid Input");
            AdminView.adminServices();
        }
    }

    /**
     * Get choice from user
     */
    public static String getChoice() {
        return SCANNER.next().trim();
    }

    /**
     * insert questions
     *
     * @param choice
     * @return
     */
    private static boolean insertQuestion(int choice) {
        final int questionNumber = getQuestionNumber(choice);
        final int roundNumber = getRoundNumber(choice, questionNumber);
        final String questions = getQuestions();
        final String firstOption = getFirstOption();
        final String secondOption = getSecondOption();
        final String thirdOption = getThirdOption();
        final String fourthOption = getFourthOption();
        final String correctAnswer = getCorrectAnswer();
        final Quiz QuizTools = new Quiz(questionNumber, questions, firstOption, secondOption, thirdOption, fourthOption, correctAnswer);
        final boolean isInserted = CrudController.questionInsert(choice, QuizTools, roundNumber);

        if (isInserted && choice == 1) {
            System.out.println("Successfully Inserted");
            checkContinue();
        } else if (!isInserted && choice == 1) {
            System.out.println("Insertion failed");
            checkContinue();
        }
        return isInserted;
    }

    /**
     * updateQuestion
     *
     * @param choice
     */
    private static void updateQuestion(int choice) {
        boolean isUpdated = AdminView.insertQuestion(choice);

        if (isUpdated) {
            System.out.println("Successfully updated");
            checkContinue();
        } else {
            System.out.println("updated Failed");
            checkContinue();
        }
    }

    /**
     * deleteQuestions
     *
     * @param choice
     */
    private static void deleteQuestion(int choice) {
        final int questionNumber = getQuestionNumber(choice);
        final int roundNumber = getRoundNumber(choice, questionNumber);
        final boolean isQuestionPresent = CrudController.deleteQuestion(questionNumber, roundNumber);

        if (isQuestionPresent) {
            System.out.println("Successfully deleted");
            checkContinue();
        } else {
            System.out.println("Deleted Failed");
            checkContinue();
        }
    }

    /**
     * Continue or not
     */
    private static void checkContinue() {
        System.out.println("Do you Want to continue ? (Yes /No)");
        final String isContinue = SCANNER.next().trim();

        if ("yes".equalsIgnoreCase(isContinue)) {
            adminServices();
        } else if ("no".equalsIgnoreCase(isContinue)) {
            System.out.println("Thank You");
        }
    }

    /**
     * Get questionNumber to the user
     */
    public static int getQuestionNumber(final int choice) {
        System.out.println("Enter question number");
        int questionNumber = 0;
        try {
            questionNumber = Integer.parseInt(SCANNER.next());
        } catch(NumberFormatException exception) {
            System.out.println("Re-Enter valid QuestionNumber");
            return getQuestionNumber(choice);
        }
        return questionNumber;
    }

    /**
     * get roundNumber
     *
     * @param choice
     * @param questionNumber
     */
    public static int getRoundNumber(int choice, int questionNumber) {
        System.out.println("Enter Round Number");
        int roundNumber = Integer.parseInt(SCANNER.next());
        boolean isQuestionNumber = CrudController.checkQuestionNumber(questionNumber, roundNumber);

        if(!isQuestionNumber && choice == 1) {
            System.out.println("Question Number Already Exists");
            getQuestionNumber(choice);
        } else if(isQuestionNumber && (choice == 2 || choice == 3)) {
            System.out.println("Question Number Not Found");
            getQuestionNumber(choice);
        }
        return roundNumber;
    }

    /**
     * Get question to the user
     */
    public static String getQuestions() {
        System.out.println("Enter Questions");
        final String question = SCANNER.next().trim();

        return question;
    }

    /**
     * Get firstOption to the user
     */
    public static String getFirstOption() {
        System.out.println("Enter first option");
        final String firstOption = SCANNER.next().trim();

        if (!ADMIN_VALIDATION.checkFirstOption(firstOption)) {
            System.out.println("Re-Enter first option e.g[a.india]");
            return getFirstOption();
        }
        return firstOption;
    }

    /**
     * Get secondOption to the user
     */
    public static String getSecondOption() {
        System.out.println("Enter second option");
        final String secondOption = SCANNER.next().trim();

        if (!ADMIN_VALIDATION.checkSecondOption(secondOption)) {
            System.out.println("Re-Enter Second option e.g[b.india]");
            return getSecondOption();
        }
        return secondOption;
    }

    /**
     * Get thirdOption to the user
     */
    public static String getThirdOption() {
        System.out.println("Enter third option");
        final String thirdOption = SCANNER.next().trim();

        if (!ADMIN_VALIDATION.checkThirdOption(thirdOption)) {
            System.out.println("Re-Enter third option e.g[c.india]");
            return getThirdOption();
        }
        return thirdOption;
    }

    /**
     * Get fourthOption to the user
     */
    public static String getFourthOption() {
        System.out.println("Enter fourthOption");
        final String fourthOption = SCANNER.next().trim();

        if (!ADMIN_VALIDATION.checkFourthOption(fourthOption)) {
            System.out.println("Re-Enter fourth option e.g[d.india]");
            return getFourthOption();
        }
        return fourthOption;
    }

    /**
     * Get correctAnswer to the user
     */
    public static String getCorrectAnswer() {
        System.out.println("Enter correctAnswer");
        final String correctAnswer = SCANNER.next();

        if (!ADMIN_VALIDATION.checkAnswer(correctAnswer)) {
            System.out.println("Re-Enter Correct Answer [a, b, c, d]");
            return getCorrectAnswer();
        }
        return correctAnswer;
    }

}
