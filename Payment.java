package service;

import java.util.Scanner;

public class Payment {
    public void calculateTotal(int seats, double ticketPrice, double beveragePrice) {
        double total = (seats * ticketPrice) + beveragePrice;
        System.out.printf("Total amount: ₹%.2f%n", total);
    }

    public void proceedPayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose UPI payment method: 1. QR Code 2. UPI Handle");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.println("Generating QR Code...");
            generateQRCode();
        } else if (choice == 2) {
            System.out.println("Enter your UPI handle (e.g., user@upi):");
            String upiHandle = scanner.nextLine();
            System.out.println("Payment successful via " + upiHandle);
        } else {
            System.out.println("Invalid choice. Try again.");
            proceedPayment();
        }
    }

    private void generateQRCode() {
        System.out.println("[QR Code Image Placeholder]"); // Add QR code logic here or placeholder
        System.out.println("Scan the QR code to complete payment.");
        System.out.println("Payment successful!");
    }

    public void sendReceipt(String phoneNumber) {
        System.out.println("Receipt sent to: " + phoneNumber);
    }
}
