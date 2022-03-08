package com.theatmoclub.service;

import com.theatmoclub.dao.QuizDao;
import com.theatmoclub.model.Quiz;
import com.theatmoclub.exception.AccessFailedException;

import java.util.List;

public class QuizDaoServiceImplements implements QuizDaoService {

    private static final QuizDao QUIZ_DAO = new QuizDao();

    /**
     * Mark Insert into dataBase
     *
     * @param mark
     * @param email
     */
    public boolean markInsert(final int mark, final String email) {

        if (QUIZ_DAO.checkUserEmail(email)) {
            QUIZ_DAO.markInsert(mark, email);
        } else {
            return false;
        }
        return true;
    }

    /**
     * GetRoundDetails from dataBase
     *
     * @param level
     */
    public List<Quiz> getRoundDetails(final int level) {

        if (level == 1) {
            return QUIZ_DAO.getFirstRoundDetails();
        } else if (level == 2) {
            return QUIZ_DAO.getSecondRoundDetails();
        } else if (level == 3) {
            return QUIZ_DAO.getThirdRoundDetails();
        } else {
            throw new AccessFailedException("Access Failed");
        }
    }
}
