package service;

import java.util.Scanner;

public class PhoneNo {

    public static void main(String[] args) {
        // Create scanner object to be used throughout the program
        try (Scanner scanner = new Scanner(System.in)) {

            // Call the method to get and validate the phone number
            String validPhoneNumber = getPhoneNumber(scanner);
            System.out.println("Validated Phone Number: " + validPhoneNumber);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to ask for phone number and validate its length
    public static String getPhoneNumber(Scanner scanner) {
        String phoneNumber;

        // Loop until a valid 10-digit phone number is entered
        while (true) {
            System.out.print("Enter your 10-digit phone number (without spaces or dashes): ");
            phoneNumber = scanner.nextLine();

            // Check if the phone number is exactly 10 digits and contains only numbers
            if (phoneNumber.matches("\\d{10}")) {
                System.out.println("Phone number is valid.");
                return phoneNumber; // Return the validated phone number
            } else {
                System.out.println("Invalid phone number! Please enter exactly 10 digits.");
                System.out.print("Would you like to try again? (yes/no): ");
                String retry = scanner.nextLine().trim();
                if (retry.equalsIgnoreCase("no")) {
                    System.out.println("Exiting phone number validation.");
                    return null; // Exit the method if the user chooses not to retry
                }
            }
        }
    }
}
