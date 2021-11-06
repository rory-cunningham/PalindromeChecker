
package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.*;
import java.util.logging.*;

/**
 * The PalindromeChecker program implements an application that
 * simply checks and stores potential palindromes.
 * It implements simple logging and is covered by basic unit tests.
 * Comments have been left to a minimum in order to improve readability
 * as the program should be easy to follow.
 *
 *
 * @author  Rory Cunningham
 * @version 1.0
 * @since   2021-11-01
 */

public class PalindromeChecker {

    private static ArrayList<UserData> storeOfPalindromes = new ArrayList<UserData>();
    private static Log my_log = null;

    public PalindromeChecker(){}

    public static String askForUserName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username:");
        return scanner.nextLine();
    }

    public static String askForUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your potential Palindrome:");
        String input =  scanner.nextLine();
        while(!checkInputValidity(input))
        {
            System.out.println("Please enter a valid palindrome with no spaces or numbers:");
            input = scanner.nextLine();
        }
        return input;
    }

    public static boolean checkInputValidity(String input) {
        char[] ch = input.toCharArray();
        for (char c : ch) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private static void checkIfPalindrome(UserData userData){
        StringBuilder reverse = new StringBuilder();
        for (int i = userData.getUserInput().length() - 1; i >= 0; i-- ) {
            reverse.append(userData.getUserInput().charAt(i));
        }

        if (userData.getUserInput().equals(reverse.toString())) {
            System.out.println("Entered text is a palindrome.");
            userData.setPalindrome(true);
        }

        else {
            System.out.println("Entered text isn't a palindrome.");
            userData.setPalindrome(false);
        }
        addPalindromeToCache(userData);
    }

    private static void addPalindromeToFile(UserData userData) {
        if(createOrFindFile())
        {
            try {

                FileOutputStream fileOut = new FileOutputStream("src/main/resources/palindrome.txt");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(userData);
                objectOut.close();
                System.out.println("Your input was successfully saved to a file!");

            } catch (Exception ex) {
                ex.printStackTrace();
                my_log.logger.severe("Error,storage file cannot be found!");
            }
        }
        else{
            try {

                FileOutputStream fileOut = new FileOutputStream("src/main/resources/palindrome.txt", true);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(userData);
                objectOut.close();
                System.out.println("Your input was successfully written to a file");

            } catch (Exception ex) {
                ex.printStackTrace();
                my_log.logger.severe("Error,file cannot be found!");
            }
        }


    }

    private static boolean createOrFindFile() {
        Path path = Paths.get("src/main/resources/palindrome.txt");
        if (Files.notExists(path)) {
            Path newFilePath = Paths.get("src/main/resources/palindrome.txt");
            try {
                Files.createFile(newFilePath);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                my_log.logger.severe("Error,file cannot be found!");
            }
        }
        return false;
    }

    private static void addPalindromeToCache(UserData userData){
        boolean inputExists = false;
        for (UserData storeOfPalindrome : storeOfPalindromes) {
            if (userData.getUserInput().equals(storeOfPalindrome.getUserInput())) {
                inputExists = true;
                break;
            }
        }

        if(!inputExists)
        {
            storeOfPalindromes.add(userData);
            addPalindromeToFile(userData);

        }
    }

    public static void populateCache(){
        Path path = Paths.get("src/main/resources/palindrome.txt");
        if (Files.exists(path)) {
            try (FileInputStream fis = new FileInputStream("src/main/resources/palindrome.txt");
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                UserData user = (UserData) ois.readObject();
                storeOfPalindromes.add(user);

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                my_log.logger.severe("Error,file cannot be found!");
            }
        }
    }

    private static void initializeLogging() {
        try {
            my_log = new Log("src/main/resources/log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        my_log.logger.setLevel(Level.WARNING);
    }

    public static void main(String[] args) {
        initializeLogging();
        populateCache();
        UserData userData = new UserData();
        userData.setUserName(askForUserName());
        userData.setUserInput(askForUserInput());
        checkIfPalindrome(userData);
    }


}
