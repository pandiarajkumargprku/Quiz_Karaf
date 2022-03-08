package com.theatmoclub.service;

import com.theatmoclub.dao.CrudDao;
import com.theatmoclub.exception.DataNotFoundException;
import com.theatmoclub.exception.IdAlreayExistsException;
import com.theatmoclub.model.Quiz;

public class DaoServiceImplements implements Daoservice {

    private static final CrudDao CRUD_DAO = new CrudDao();

    /**
     * check questionNumber
     *
     * @param questionNumber
     * @param roundNumber
     */
    public  boolean checkQuestionNumber(int questionNumber, int roundNumber) {
        boolean isRoundNumber = true;

        if(roundNumber == 1) {
            isRoundNumber = CRUD_DAO.checkFirstRoundQuestionNumber(questionNumber);
        } else if(roundNumber == 2) {
            isRoundNumber = CRUD_DAO.checkSecondRoundQuestionNumber(questionNumber);
        } else if(roundNumber == 3) {
            isRoundNumber = CRUD_DAO.checkThirdRoundQuestionNumber(questionNumber);
        }
        return isRoundNumber;
    }

    /**
     * check insert or update to question
     * @param choice
     * @param quizTools
     * @param roundNumber
     */
    public boolean questionInsert(int choice, Quiz quizTools, int roundNumber) {
        boolean isQuestionInsert = true;

        if (choice == 1) {
            isQuestionInsert = DaoServiceImplements.insertQuestion(quizTools, roundNumber);
        } else if (choice == 2) {
            isQuestionInsert = DaoServiceImplements.updateQuestion(quizTools, roundNumber);
        }
        return isQuestionInsert;
    }

    /**
     * check round number to insert the question
     * @param quizTools
     * @param roundNumber
     */
    private static boolean insertQuestion(Quiz quizTools, int roundNumber) {
        boolean isQuestionInsert = true;

        if(roundNumber == 1) {
            isQuestionInsert = CRUD_DAO.insertFirstRound(quizTools);
        } else if(roundNumber == 2) {
            isQuestionInsert = CRUD_DAO.insertSecondRound(quizTools);
        } else if(roundNumber == 3) {
            isQuestionInsert = CRUD_DAO.insertThirdRound(quizTools);
        } else {
            throw new IdAlreayExistsException("Question Number Already Exits");
        }
        return isQuestionInsert;
    }

    /**
     * check round number to update the question
     * @param quizTools
     * @param roundNumber
     */
    private static boolean updateQuestion(Quiz quizTools, int roundNumber) {
        boolean isQuestionUpdate = true;

        if(roundNumber == 1) {
            isQuestionUpdate = CRUD_DAO.updateFirstRound(quizTools);
        } else if(roundNumber == 2) {
            isQuestionUpdate = CRUD_DAO.updateSecondRound(quizTools);
        } else if(roundNumber == 3) {
            isQuestionUpdate = CRUD_DAO.updateThirdRound(quizTools);
        } else {
            throw new DataNotFoundException("Question Not Found");
        }
        return isQuestionUpdate;
    }

    /**
     * check round number to delete the question
     * @param questionNumber
     * @param roundNumber
     */
    public boolean deleteQuestion(final int questionNumber, int roundNumber) {
        boolean isQuestionDelete = true;

        if(roundNumber == 1) {
            isQuestionDelete = CRUD_DAO.deleteFirstRound(questionNumber);
        } else if(roundNumber == 2) {
            isQuestionDelete = CRUD_DAO.deleteSecondRound(questionNumber);
        } else if(roundNumber == 3) {
            isQuestionDelete = CRUD_DAO.deleteThirdRound(questionNumber);
        } else {
            throw new DataNotFoundException("Question Not Found");
        }
        return isQuestionDelete;
    }
}
