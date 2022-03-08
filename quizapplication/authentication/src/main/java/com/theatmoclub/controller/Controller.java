package com.theatmoclub.controller;

import com.theatmoclub.model.User;
import com.theatmoclub.service.Service;
import com.theatmoclub.service.ServiceImplements;

public class Controller {

    private static final Service SERVICE = new ServiceImplements();

    /**
     * Checks the email of user and admin from database
     *
     * @param choice
     * @param email
     */
    public static boolean checkEmail(final int choice, final String email) {
        return SERVICE.checkEmail(choice, email);
    }

    /**
     * Checks the password of user and admin from database
     *
     * @param choice
     * @param password
     */
    public static boolean checkPassword(final int choice, final String password) {
        return SERVICE.checkPassword(choice, password);
    }

    /**
     * SignUp admin and user details
     *
     * @param choice
     * @param user
     */
    public static boolean signUpInsert(final int choice, final User user) {
        return SERVICE.insertSignUpDetail(choice, user);
    }
}
