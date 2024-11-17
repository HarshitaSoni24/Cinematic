package service;

import java.util.Scanner;

public class Payment {
    private double totalAmount;

    public void calculateTotal(int seatCount, double ticketPrice, double beveragePrice) {
        totalAmount = (seatCount * ticketPrice) + beveragePrice;
        System.out.println("Total amount to be paid: ₹" + totalAmount);
    }

    public void proceedPayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose UPI payment method:");
        System.out.println("1. QR Code");
        System.out.println("2. UPI Handle");
        int paymentMethod = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (paymentMethod == 1) {
            System.out.println("Generating QR Code...");
            // Simulating QR Code generation
            System.out.println("[QR Code Image Placeholder]");
            System.out.println("Scan the QR code to complete payment.");
        } else if (paymentMethod == 2) {
            System.out.println("Please enter your UPI handle (e.g., username@upi):");
            String upiHandle = scanner.nextLine();
            System.out.println("Proceeding with payment using UPI handle: " + upiHandle);
        } else {
            System.out.println("Invalid choice. Please select 1 for QR Code or 2 for UPI Handle.");
            return;
        }

        System.out.println("Would you like to confirm the payment after scanning the QR code? (yes/no)");
        String confirmPayment = scanner.nextLine();
        if (confirmPayment.equalsIgnoreCase("yes")) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment cancelled. Please try again.");
            return; // Exit if user cancels the payment process
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void sendReceipt(String phoneNumber) {
        System.out.println("Receipt sent to: " + phoneNumber);
        // Simulating receipt sent to phone number.
        System.out.println("Receipt Details: ");
        System.out.println("Movie: Pushpa");
        System.out.println("Seats: 1A 1B");
        System.out.println("Total Price: ₹" + totalAmount);
        System.out.println("Thank you for using Cinematic!");
    }
}
