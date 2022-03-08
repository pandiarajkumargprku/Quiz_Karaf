package com.theatmoclub.service;

import com.theatmoclub.model.User;

public interface Service {
    boolean checkEmail(int choice, String email);

    boolean checkPassword(int choice, String password);

    boolean insertSignUpDetail(int choice, User user);
}
