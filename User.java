package service;

import java.util.HashMap;
import java.util.Scanner;

public class User {
    private static HashMap<String, String> userDatabase = new HashMap<>();
    private String currentUser;

    public void loginOrRegister() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you already have an account? (yes/no)");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            login();
        } else if (choice.equalsIgnoreCase("no")) {
            register();
        } else {
            System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            loginOrRegister();
        }
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            System.out.println("Login successful! Welcome, " + username + ".");
            currentUser = username;
        } else {
            System.out.println("Invalid username or password. Restarting...");
            loginOrRegister(); // Restart the process
        }
    }

    private void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create a username:");
        String username = scanner.nextLine();

        if (userDatabase.containsKey(username)) {
            System.out.println("Username already exists. Please choose another.");
            register();
            return;
        }

        System.out.println("Create a password (Minimum 8 characters, at least 1 uppercase, 1 number, 1 special character):");
        String password = scanner.nextLine();

        if (!isValidPassword(password)) {
            System.out.println("Password does not meet requirements. Restarting registration...");
            register();
            return;
        }

        userDatabase.put(username, password);
        System.out.println("Registration successful! Welcome, " + username + ".");
        currentUser = username;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        if (!password.matches(".*[A-Z].*")) return false; // At least one uppercase letter
        if (!password.matches(".*[0-9].*")) return false; // At least one number
        if (!password.matches(".*[@#$%^&+=!].*")) return false; // At least one special character
        return true;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
