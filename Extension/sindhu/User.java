package service;

import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private static HashMap<String, String> userDatabase = new HashMap<>();
    private String currentUser;

    public User() {
        loadUserDatabase();
    }

    public void loginOrRegister(Scanner scanner) {
        while (true) {
            System.out.println("Do you already have an account? (yes/no)");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("yes")) {
                login(scanner);
                break;
            } else if (choice.equalsIgnoreCase("no")) {
                register(scanner);
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            }
        }
    }

    private void login(Scanner scanner) {
        while (true) {
            System.out.println("Enter your username:");
            String username = scanner.nextLine().trim();

            System.out.println("Enter your password:");
            String password = scanner.nextLine().trim();
            
            String hashedPassword = hashPassword(password);

            if (userDatabase.containsKey(username) && userDatabase.get(username).equals(hashedPassword)) {
                System.out.println("Login successful! Welcome, " + username + ".");
                currentUser = username;
                break;
            } else {
                System.out.println("Invalid username or password. Would you like to try again? (yes/no)");
                String retry = scanner.nextLine().trim();
                if (!retry.equalsIgnoreCase("yes")) {
                    System.out.println("Returning to the main menu.");
                    break;
                }
            }
        }
    }

    private void register(Scanner scanner) {
        while (true) {
            System.out.println("Create a username:");
            String username = scanner.nextLine().trim();

            if (userDatabase.containsKey(username)) {
                System.out.println("Username already exists. Please choose another.");
                continue;
            }

            System.out.println("Create a password (Minimum 8 characters, at least 1 uppercase, 1 number, 1 special character):");
            String password = scanner.nextLine().trim();

            if (isValidPassword(password)) {
                String hashedPassword = hashPassword(password);
                userDatabase.put(username, hashedPassword);
                saveUserDatabase();  // Save updated database to file
                System.out.println("Registration successful! Welcome, " + username + ".");
                currentUser = username;
                break;
            } else {
                System.out.println("Password does not meet requirements. Please try again.");
            }
        }
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[0-9].*") &&
               password.matches(".*[@#$%^&+=!].*");
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadUserDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader("userDatabase.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] user = line.split(":");
                userDatabase.put(user[0], user[1]);
            }
        } catch (IOException e) {
            System.out.println("No existing user data found.");
        }
    }

    private void saveUserDatabase() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("userDatabase.txt"))) {
            for (String username : userDatabase.keySet()) {
                bw.write(username + ":" + userDatabase.get(username));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving user data.");
        }
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
