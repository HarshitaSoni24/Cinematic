package service;

import java.util.Scanner;

public class CinematicApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Location selection
        Location location = new Location();
        location.chooseLocation();

        // Movie selection
        Movie movie = new Movie();
        movie.displayMovies();
        int movieId = movie.selectMovie();
        double ticketPrice = movie.getMoviePrice(movieId);

        // User login or registration
        User user = new User();
        user.loginOrRegister();

        // Theater selection based on city
        String city = location.getCity();
        Theater theater = new Theater(city);
        theater.selectTheater();

        // Seat selection
        theater.selectSeats();
        int seatCount = theater.getSelectedSeatsCount();

        // Beverages selection
        Beverages beverages = new Beverages();
        System.out.println("Would you like to add beverages? (yes/no)");
        String beverageChoice = scanner.nextLine();
        double beveragePrice = 0;

        if (beverageChoice.equalsIgnoreCase("yes")) {
            beveragePrice = beverages.selectBeverages();
        }

        // Calculate and display total
        Payment payment = new Payment();
        payment.calculateTotal(seatCount, ticketPrice, beveragePrice);

        // Proceed with payment
        payment.proceedPayment();

        // Confirmation for payment after QR generation
        System.out.println("Would you like to confirm the payment after scanning the QR code? (yes/no)");
        String confirmPayment = scanner.nextLine();
        if (confirmPayment.equalsIgnoreCase("yes")) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment cancelled. Please try again.");
            return; // Exit if user cancels the payment process
        }

        // Show receipt preview
        System.out.println("Here is your booking receipt:");
        System.out.println("Movie: " + movie.getMovieName(movieId));
        System.out.println("Theater: " + theater.getTheaterName());
        System.out.println("Seats: " + theater.getSelectedSeats());
        System.out.println("Total Price: ₹" + payment.getTotalAmount());

        // Confirm receipt
        System.out.println("Is the receipt information correct? (yes/no)");
        String confirmReceipt = scanner.nextLine();
        if (confirmReceipt.equalsIgnoreCase("yes")) {
            // Proceed with receipt printing
            System.out.println("Enter your phone number to receive the receipt:");
            String phoneNumber = scanner.nextLine();
            payment.sendReceipt(phoneNumber);
        } else {
            System.out.println("Please make the necessary changes and try again.");
        }

        // Thank you message
        System.out.println("Thank you for using Cinematic!");
    }
}
