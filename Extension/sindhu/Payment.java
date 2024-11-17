package service;

import java.util.Scanner;

public class Payment {

    // Method to proceed with the payment
    public void proceedPayment(Scanner scanner) {
        String method = "";
        boolean validPaymentMethod = false;

        System.out.println("Proceeding to payment...");

        // Validate payment method input
        while (!validPaymentMethod) {
            System.out.println("Enter payment method (Credit/Debit/UPI):");
            method = scanner.nextLine().trim();

            // Check for valid method
            if (method.equalsIgnoreCase("Credit") || method.equalsIgnoreCase("Debit") || method.equalsIgnoreCase("UPI")) {
                validPaymentMethod = true;
            } else {
                System.out.println("Invalid payment method. Please enter Credit, Debit, or UPI.");
            }
        }

        System.out.println("Enter payment details:");
        String details = scanner.nextLine().trim();

        // Simulate payment processing
        boolean paymentSuccess = processPayment(details);

        if (paymentSuccess) {
            System.out.println("Payment successful! Thank you for your purchase.");
        } else {
            System.out.println("Payment failed! Please try again.");
        }
    }

    // Simulate payment processing
    private boolean processPayment(String paymentDetails) {
        // Here you can add actual payment gateway integration.
        // For now, it's simulated to always return true (successful payment).
        return true; // Simulate success
    }

    // Method to calculate the total amount for the booking
    public double calculateTotal(int seatCount, double ticketPrice, double beveragePrice) {
        double totalAmount = (seatCount * ticketPrice) + beveragePrice;
        return totalAmount;
    }

    // Method to send the receipt to the user via phone number
    public void sendReceipt(String phoneNumber) {
        // Validate phone number format (basic example)
        if (phoneNumber.matches("\\d{10}")) {
            System.out.println("Receipt has been sent to " + phoneNumber);
        } else {
            System.out.println("Invalid phone number format. Please enter a valid 10-digit number.");
        }
    }
}
