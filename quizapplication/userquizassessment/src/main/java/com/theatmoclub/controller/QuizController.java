package com.theatmoclub.controller;

import com.theatmoclub.model.Quiz;
import com.theatmoclub.service.QuizDaoService;
import com.theatmoclub.service.QuizDaoServiceImplements;

import java.util.List;

public class QuizController {

    private static final QuizDaoService QUIZ_DAO_SERVICE = new QuizDaoServiceImplements();

    /**
     * All level questions get from the database
     *
     * @param level
     */
    public static List<Quiz> getRoundDetails(final int level) {
        return QUIZ_DAO_SERVICE.getRoundDetails(level);
    }

    /**
     * User mark insert in to database
     *
     * @param mark
     * @param email
     */
    public static boolean markInsert(final int mark, final String email) {
        return QUIZ_DAO_SERVICE.markInsert(mark, email);
    }
}
