package service;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private String username;
    private String password;
    private int loyaltyPoints;
    private List<String> bookingHistory; // Stores booking details

    // Constructor to initialize profile
    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
        this.loyaltyPoints = 0; // Initially 0 points
        this.bookingHistory = new ArrayList<>();
    }

    // Method to add loyalty points
    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
        System.out.println("You have earned " + points + " loyalty points. Total points: " + loyaltyPoints);
    }

    // Method to redeem loyalty points for a discount
    public boolean redeemPoints(int pointsRequired) {
        if (loyaltyPoints >= pointsRequired) {
            loyaltyPoints -= pointsRequired;
            System.out.println("Points redeemed successfully. Remaining points: " + loyaltyPoints);
            return true;
        }
        System.out.println("Not enough points to redeem.");
        return false;
    }

    // Add a booking to the history
    public void addBooking(String bookingDetails) {
        bookingHistory.add(bookingDetails);
    }

    // Method to display booking history
    public void displayBookingHistory() {
        System.out.println("Booking History:");
        for (String booking : bookingHistory) {
            System.out.println(booking);
        }
    }

    // Method to display user profile
    public void displayProfile() {
        System.out.println("\nUser Profile:");
        System.out.println("Username: " + username);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        // You can add more fields if necessary (e.g., email, booking history)
    }

    // Getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}
