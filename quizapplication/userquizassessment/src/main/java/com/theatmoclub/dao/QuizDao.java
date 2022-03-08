package com.theatmoclub.dao;

import com.theatmoclub.dbconnection.DatabaseConnection;
import com.theatmoclub.exception.CustomException.ConnectionException;
import com.theatmoclub.model.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDao {

    /**
     * Get firstRoundDetails
     */
    public List<Quiz> getFirstRoundDetails() {
        final String sqlQuery = "select question_number, questions, first_option,second_option, third_option, fourth_option, correct_answer from first_round_table";

        return QuizDao.getDetails(sqlQuery);
    }

    /**
     * Get secondRoundDetails
     */
    public List<Quiz> getSecondRoundDetails() {
        final String sqlQuery = "select question_number, questions, first_option,second_option, third_option, fourth_option, correct_answer from second_round_table";

        return QuizDao.getDetails(sqlQuery);

    }

    /**
     * Get thirdRoundDetails
     */
    public List<Quiz> getThirdRoundDetails() {
        final String sqlQuery = "select question_number, questions, first_option,second_option, third_option, fourth_option, correct_answer from third_round_table";

        return QuizDao.getDetails(sqlQuery);
    }

    /**
     * Get questions,options and correctAnswer from dataBase
     *
     * @param sqlQuery
     */
    public static List<Quiz> getDetails(final String sqlQuery) {

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery);) {
            List<Quiz> questionDetails = new ArrayList<Quiz>();

            while (resultSet.next()) {
                final Quiz questionAnswer = new Quiz();

                questionAnswer.setQuestionNumber(resultSet.getInt(1));
                questionAnswer.setQuestions(resultSet.getString(2));
                questionAnswer.setFirstOption(resultSet.getString(3));
                questionAnswer.setSecondOption(resultSet.getString(4));
                questionAnswer.setThirdOption(resultSet.getString(5));
                questionAnswer.setFourthOption(resultSet.getString(6));
                questionAnswer.setCorrectAnswer(resultSet.getString(7));

                questionDetails.add(questionAnswer);
            }
            return questionDetails;
        } catch (SQLException exception) {
            throw new ConnectionException("Connection Failed");
        }
    }

    /**
     * Mark insert into dataBase
     *
     * @param score
     * @param email
     */
    public boolean markInsert(final int score, final String email) {
        final String sqlQuery = "Update user_table SET score = ? where email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(sqlQuery);) {
            prepareStatement.setInt(1, score);
            prepareStatement.setString(2, email);

            prepareStatement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            throw new ConnectionException("Connection Failed");
        }
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
}
