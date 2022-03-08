package com.theatmoclub.service;

import com.theatmoclub.model.Quiz;

public interface Daoservice {

    boolean questionInsert(int choice, Quiz quizTools, int roundNumber);

    boolean checkQuestionNumber(int questionNumber, int roundNumber);

    boolean deleteQuestion(int questionNumber, int roundNumber);
}
