package com.theatmoclub.service;

import com.theatmoclub.dao.AuthenticateDao;
import com.theatmoclub.exception.MailIdNotFoundException;
import com.theatmoclub.exception.PasswordNotFoundException;
import com.theatmoclub.model.User;

public class ServiceImplements implements Service {

    private static final AuthenticateDao AUTHENTICATE_DAO = new AuthenticateDao();

    /**
     * Check signUp admin or user
     *
     * @param choice
     * @param user
     */
    public boolean insertSignUpDetail(final int choice, final User user)  {
        boolean isSignUp = true;

        if (choice == 1) {
            isSignUp = AUTHENTICATE_DAO.insertAdminSignUpDetails(user);
        } else if (choice == 2) {
            isSignUp = AUTHENTICATE_DAO.insertUserSignUpDetails(user);
        }
        return isSignUp;
    }

    /**
     * Check email
     *
     * @param choice
     * @param email
     */
    public boolean checkEmail(final int choice, final String email) {
        boolean isEmailValid = true;

        try {
            if (choice == 1) {
                isEmailValid = AUTHENTICATE_DAO.checkAdminEmail(email);
            } else if (choice == 2) {
                isEmailValid = AUTHENTICATE_DAO.checkUserEmail(email);
            }
        } catch (Exception exception) {
            throw new MailIdNotFoundException("Mail Id Not Found");
        }
        return isEmailValid;
    }

    /**
     * Check password
     *
     * @param choice
     * @param password
     */
    public boolean checkPassword(final int choice, final String password) {
        boolean isPasswordValid = true;

        if (choice == 1) {
            isPasswordValid = AUTHENTICATE_DAO.checkAdminPassword(password);
        } else if (choice == 2) {
            isPasswordValid = AUTHENTICATE_DAO.checkUserPassword(password);
        } else {
             throw new PasswordNotFoundException("Password Not Found");
        }
        return isPasswordValid;
    }

}
