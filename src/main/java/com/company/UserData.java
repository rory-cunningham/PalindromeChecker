package com.company;

import java.io.Serializable;

/**
 * The UserData class is used to represent potential
 * users of the PalindromeChecker.
 *
 * @author  Rory Cunningham
 * @since   2021-11-01
 */

public class UserData implements Serializable {
    private String userInput;
    private String userName;
    private boolean isPalindrome;

    public UserData() {
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isPalindrome() {
        return isPalindrome;
    }

    public void setPalindrome(boolean palindrome) {
        isPalindrome = palindrome;
    }
}
