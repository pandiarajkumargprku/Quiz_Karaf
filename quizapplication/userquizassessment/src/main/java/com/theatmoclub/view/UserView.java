package com.theatmoclub.view;

import com.theatmoclub.controller.QuizController;
import com.theatmoclub.model.Quiz;
import com.theatmoclub.validation.UserValidation;
import com.theatmoclub.validation.Validation;

import java.util.List;
import java.util.Scanner;

public class UserView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserValidation USER_VALIDATE = new UserValidation();
    public static final Validation VALIDATE = new Validation();

    /**
     * User services
     *
     * @param email
     */
    public static void userServices(final String email) {
        int mark = 0;
        int level = 1;

        levelDetails(mark, level, email);
    }

    /**
     * Level details
     *
     * @param mark
     * @param level
     * @param email
     */
    public static void levelDetails(int mark, int level, final String email) {
        int lastLevel = 3;

        System.out.println("Level"+level);
        System.out.println("Total Questions 5");
        System.out.println("Marks:2 Mark for each question");
        System.out.println("Negative Marks:1 mark per wrong answer");
        System.out.println("Start Quiz");
        System.out.println("Contents of the Table:");

        List<Quiz> details = QuizController.getRoundDetails(level);

        for (int index = 0; index < details.size(); index++) {
            Quiz questionAnswer = details.get(index);
            System.out.println(questionAnswer);
            String answer = getCorrectAnswer();
            mark = USER_VALIDATE.markCalculation(mark, answer, questionAnswer.getCorrectAnswer());
        }
        System.out.println("Your Score is" + mark);
        boolean isPass = USER_VALIDATE.checkMark(mark, email);

        if (!isPass) {
            System.out.println("your score is below average");
            System.out.println("Do you Want to Continue");
            markInsert(mark, email);
            checkContinue(email);
        } else if (isPass) {
            level = level+1;

            if (level <= lastLevel) {
                levelDetails(mark, level, email);
            } else {
                markInsert(mark, email);
            }
        }
    }

    /**
     * Continue or not
     *
     */
    private static void checkContinue(String email) {
        System.out.println("Do You Want to continue");
        String isContinue = SCANNER.next();

        if ("yes".equalsIgnoreCase(isContinue)) {
            userServices(email);
        } else if ("no".equalsIgnoreCase(isContinue)) {
            System.out.println("Thank You for Attend the Quiz");
        }
    }

    /**
     *  Mark insert into dataBase
     *
     *  @param mark
     *  @param email
     */
    public static void markInsert(int mark, final String email) {
        boolean isMarkUpdated = QuizController.markInsert(mark, email);

        if (isMarkUpdated) {
            System.out.println("Your mark was Updated Successfully");
        } else {
            System.out.println("Check your EMail");
        }
    }

    /**
     * Get correctAnswer to the user
     */
    public static String getCorrectAnswer() {
        System.out.println("Enter correctAnswer");
        final String correctAnswer = SCANNER.next();

        if (!VALIDATE.checkAnswer(correctAnswer)) {
            System.out.println("Re-Enter Correct Answer [a, b, c, d]");
            return getCorrectAnswer();
        }
        return correctAnswer;
    }
}



