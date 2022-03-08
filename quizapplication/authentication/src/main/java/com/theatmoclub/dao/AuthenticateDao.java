package com.theatmoclub.dao;

import com.theatmoclub.dbconnection.DatabaseConnection;
import com.theatmoclub.model.User;
import com.theatmoclub.exception.CustomException.ConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticateDao {
    /**
     * Check signUp in admin table
     *
     * @param email
     */
    public boolean checkAdminEmail(final String email) {
        final String sqlQuery = "select email from admin_table where email = ?";

        return checkMailCredentials(email, sqlQuery);
    }

    /**
     * Check signIn in user table
     *
     * @param email
     */
    public boolean checkUserEmail(final String email) {
        final String sqlQuery = "select email from user_table where email = ?";

        return checkMailCredentials(email, sqlQuery);
    }

    /**
     * Check email and password
     *
     * @param email
     * @param sqlQuery
     */
    private boolean checkMailCredentials(final String email, final String sqlQuery) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(sqlQuery);) {
            prepareStatement.setString(1, email);

            try (ResultSet resultSet = prepareStatement.executeQuery();) {

                while (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException exception) {
            throw new ConnectionException("Connection Failed");
        }
        return false;
    }

    /**
     * Check admin password
     *
     * @param password
     */
    public boolean checkAdminPassword(final String password) {
        final String sqlQuery = "select email from admin_table where password = ?";

        return checkPasswordCredentials(password, sqlQuery);
    }

    /**
     * Check user password
     *
     * @param password
     */
    public boolean checkUserPassword(final String password) {
        final String sqlQuery = "select email from user_table where password = ?";

        return checkPasswordCredentials(password, sqlQuery);
    }

    /***
     * Check password credentials
     *
     * @param password
     * @param sqlQuery
     */
    private boolean checkPasswordCredentials(String password, String sqlQuery) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(sqlQuery);) {
            prepareStatement.setString(1, password);

            try (ResultSet resultSet = prepareStatement.executeQuery();) {

                while (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException exception) {
            throw new ConnectionException("Connection Failed");
        }
        return false;
    }

    /**
     * Insert admin details in admin table
     *
     * @param user
     */
    public boolean insertAdminSignUpDetails(final User user) {
        final String sqlQuery = "Insert Into admin_table(name, email, password) values(?, ?, ?)";

        return insertSignUpDetails(sqlQuery, user);
    }

    /**
     * Insert user details into user table
     *
     * @param user
     */
    public boolean insertUserSignUpDetails(final User user) {
        final String sqlQuery = "Insert Into user_table(name, email, password) values(?, ?, ?)";

        return insertSignUpDetails(sqlQuery, user);
    }

    /**
     * Insert signup details in to database
     *
     * @param sqlQuery
     * @param user
     */
    private boolean insertSignUpDetails(final String sqlQuery, final User user) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(sqlQuery);) {

            prepareStatement.setString(1, user.getName());
            prepareStatement.setString(2, user.getEmail());
            prepareStatement.setString(3, user.getPassword());

            prepareStatement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            throw new ConnectionException("Connection Failed");
        }
    }
}
