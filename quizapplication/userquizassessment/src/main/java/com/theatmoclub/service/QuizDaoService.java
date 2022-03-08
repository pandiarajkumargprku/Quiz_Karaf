package com.theatmoclub.service;

import com.theatmoclub.model.Quiz;

import java.util.List;

public interface QuizDaoService {
    List<Quiz> getRoundDetails(int level);

    boolean markInsert(int mark, String email);
}
