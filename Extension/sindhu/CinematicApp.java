package service;

import java.util.Scanner;

public class CinematicApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) { // Properly closing the scanner resource

            // Location selection
            Location location = new Location();
            location.chooseLocation(scanner);

            // Movie selection
            Movie movie = new Movie();
            movie.displayMovies();
            int movieId = movie.selectMovie(scanner);
            double ticketPrice = movie.getMoviePrice(movieId);

            // User login or registration
            User user = new User();
            user.loginOrRegister(scanner);

            // Create UserProfile instance after successful login/registration
            String username = user.getCurrentUser(); // Assuming the current user is set after login/registration
            String password = "dummy_password";  // You can store or retrieve actual password data here
            UserProfile userProfile = new UserProfile(username, password);

            // Display user profile information
            userProfile.displayProfile();

            // Loyalty points: Display available points
            System.out.println("Your loyalty points: " + userProfile.getLoyaltyPoints());
            System.out.println("Would you like to redeem points for a discount? (yes/no)");
            String redeemChoice = scanner.nextLine().trim();
            if (redeemChoice.equalsIgnoreCase("yes")) {
                System.out.println("Enter points to redeem:");
                int points = Integer.parseInt(scanner.nextLine());
                userProfile.redeemPoints(points);
            }

            // Theater selection based on city
            String city = location.getCity();
            Theatre theater = new Theatre(city);
            theater.selectTheater(scanner);

            // Display available seats and prompt to either book or cancel a ticket
            theater.displayAvailableSeats();
            System.out.println("Would you like to (1) Book a ticket or (2) Cancel your booking?");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                // Book a ticket
                System.out.println("Enter the seat you want to book:");
                String seat = scanner.nextLine();
                if (theater.bookSeat(seat)) {
                    System.out.println("Seat " + seat + " booked successfully.");
                } else {
                    System.out.println("Sorry, seat " + seat + " is already booked.");
                }
            } else if (choice == 2) {
                // Cancel an existing booking
                String bookedSeat = theater.getBookedSeat();
                if (bookedSeat != null) {
                    System.out.println("You have a booking for seat " + bookedSeat + ". Would you like to cancel it? (yes/no)");
                    String cancelChoice = scanner.nextLine().trim();
                    if (cancelChoice.equalsIgnoreCase("yes")) {
                        theater.cancelTicket(bookedSeat);
                    }
                } else {
                    System.out.println("No booking found to cancel.");
                }
            }

            // Beverages selection
            Beverages beverages = new Beverages();
            double beveragePrice = 0;
            System.out.println("\nWould you like to add beverages? (yes/no)");
            String beverageChoice = scanner.nextLine().trim();
            if (beverageChoice.equalsIgnoreCase("yes")) {
                beveragePrice = beverages.selectBeverages(scanner);
            }

            // Calculate and display total
            Payment payment = new Payment();
            double totalAmount = payment.calculateTotal(1, ticketPrice, beveragePrice); // Assuming 1 ticket for simplicity

            // Proceed with payment
            payment.proceedPayment(scanner);

            // Show receipt preview
            System.out.println("\nHere is your booking receipt:");
            System.out.println("Movie: " + movie.getMovieName(movieId));
            System.out.println("Theater: " + theater.getTheaterName());
            System.out.println("Seat: " + (theater.getBookedSeat() != null ? theater.getBookedSeat() : "N/A"));
            System.out.println("Total Price: ₹" + totalAmount);

            // Add booking to user profile
            userProfile.addBooking("Movie: " + movie.getMovieName(movieId) + ", Seat: " + (theater.getBookedSeat() != null ? theater.getBookedSeat() : "N/A") + ", Total Price: ₹" + totalAmount);

            // Display booking history
            userProfile.displayBookingHistory();

            // Confirm receipt and send
            System.out.println("\nIs the receipt information correct? (yes/no)");
            String confirmReceipt = scanner.nextLine().trim();
            if (confirmReceipt.equalsIgnoreCase("yes")) {
                System.out.println("\nEnter your phone number to receive the receipt:");
                String phoneNumber = scanner.nextLine().trim();
                payment.sendReceipt(phoneNumber);
            } else {
                System.out.println("Please make the necessary changes and try again.");
            }

            // Thank you message
            System.out.println("\nThank you for using Cinematic App! Enjoy the movie!");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
