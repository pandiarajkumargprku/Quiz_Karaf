package com.theatmoclub.view;

import com.theatmoclub.controller.Controller;
import com.theatmoclub.model.User;
import com.theatmoclub.validation.Validation;

import java.util.Scanner;

public class View {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Validation VALIDATE = new Validation();

    /**
     * check Admin or USer
     */
    public static void checkAdminOrUser() {
        System.out.println("Choose 1.Admin 2.User");

        try {
            int choice = Integer.parseInt(getChoice());

            switch (choice) {
                case 1:
                    View.admin(choice);
                    break;
                case 2:
                    View.user(choice);
                    break;
                default:
                    System.out.println("Enter valid number [1-2]");
                    checkAdminOrUser();
            }
        } catch (NumberFormatException exception) {
            System.out.println("Re-Enter valid choice");
            checkAdminOrUser();
        }
    }

    /**
     * Validate new admin or not.
     *
     * @param choice
     */
    public static void admin(final int choice) {
        System.out.println("Are you new Admin (yes / no) ?");
        final String isNewAdmin = View.SCANNER.next().trim();

        if ("yes".equalsIgnoreCase(isNewAdmin)) {
            View.signUp(choice);
        } else if ("no".equalsIgnoreCase(isNewAdmin)) {
            View.signIn(choice);
        } else {
            System.out.println("Enter Yes or No only");
            admin(choice);
        }
    }

    /**
     * User validation
     *
     * @param choice
     */
    public static void user(final int choice) {
        System.out.println("Are you new User ? (yes /no)");
        final String isNewUser = View.SCANNER.next().trim();

        if ("yes".equalsIgnoreCase(isNewUser)) {
            View.signUp(choice);
        } else if ("no".equalsIgnoreCase(isNewUser)) {
            View.signIn(choice);
        } else {
            System.out.println("Enter Yes or No only");
            user(choice);
        }
    }

    /**
     * Get choice from user
     */
    public static String getChoice() {
        return SCANNER.next().trim();
    }

    /**
     * Get name to the user
     */
    public static String getName() {
        System.out.println("Enter name:");
        final String name = SCANNER.next().trim();

        if (!VALIDATE.validateName(name)) {
            System.out.println("Re-Enter Valid Name");
            return View.getName();
        }
        return name;
    }

    /**
     * Get email to the user
     */
    public static String getEmail() {
        System.out.println("Enter email:");
        final String email = SCANNER.next().trim();

        if (!VALIDATE.validateEmail(email)) {
            System.out.println("Re-Enter Valid EmailId");
            return View.getEmail();
        }
        return email;
    }

    /**
     * Get password to the user
     */
    public static String getPassword() {
        System.out.println("Enter password");
        final String password = SCANNER.next().trim();

        if (!VALIDATE.validatePassword(password)) {
            System.out.println("Re-Enter Valid Password \n (Atleast one Numeric letter, special Characters, Uppercase & lowercase letter)");
            return View.getPassword();
        }
        return password;
    }

    /**
     * Get email from user
     *
     * @param choice
     */
    private static String getAdminEmailCheck(final int choice) {
        final String email = View.getEmail();

        if (Controller.checkEmail(choice, email)) {
            System.out.println("This Mail Id Already Exists \n Re-Enter Mail Id");
            return View.getAdminEmailCheck(choice);
        }
        return email;
    }

    /**
     * Get signUp detail
     *
     * @param choice
     */
    public static void signUp(final int choice) {
        final String name = View.getName();
        final String email = View.getAdminEmailCheck(choice);
        final String password = View.getPassword();
        final User UserDetails = new User(name, email, password);

        if (Controller.signUpInsert(choice, UserDetails)) {
            System.out.println("Sucessfully SignUp");
            checkContinue(choice);
        } else {
            System.out.println("SignUp failed");
            signUp(choice);
        }
    }

    /**
     * Checks continue
     *
     * @param choice
     */
    private static void checkContinue(final int choice) {
        System.out.println("Do You Want to continue");
        final String isContinue = SCANNER.next();

        if ("yes".equalsIgnoreCase(isContinue)) {
            signIn(choice);
        } else if ("no".equalsIgnoreCase(isContinue)) {
            System.out.println("...");
        } else {
            checkContinue(choice);
        }
    }

    /**
     * Get password then check password
     *
     * @param choice
     */
    private static boolean getPasswordCheck(final int choice) {
        final String password = View.getPassword();
        final boolean isValidPassword = Controller.checkPassword(choice, password);

        if (!isValidPassword) {
            System.out.println("Passwowrd is Wrong");
            View.getPasswordCheck(choice);
        }
        return isValidPassword;
    }

    /**
     * Which makes signIn function
     *
     * @param choice
     */
    public static void signIn(final int choice) {
        final String email = View.getUserEmailCheck(choice);
        final boolean isValidPassword = View.getPasswordCheck(choice);

        if (isValidPassword) {
            System.out.println("Successfully signIn");
            services(choice, email);
        } else {
            System.out.println("SignIn Failed");
            signIn(choice);
        }
    }

    /**
     * Get user email then email check
     *
     *  @param choice
     */
    private static String getUserEmailCheck(int choice) {
        final String email = View.getEmail();

        if (!Controller.checkEmail(choice, email)) {
            System.out.println("Email is wrong");
            View.getUserEmailCheck(choice);
        }
        return email;
    }

    /**
     * Services admin or user
     *
     * @param choice
     * @param email
     */
    private static void services(final int choice,final String email) {

        if (choice == 1) {
            AdminView.adminServices();
        } else if (choice == 2) {
            UserView.userServices(email);
        }
    }
}
