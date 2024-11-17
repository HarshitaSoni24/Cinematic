package src.pkg;

import java.io.Serializable;
import java.util.ArrayList;

public class Booking implements Serializable {
    private static int idCounter = 1;  // Static counter to generate unique booking IDs
    private int bookingId;
    private String username;
    private String movieName;
    private String showtime;
    private String date;
    private ArrayList<BeverageSnack> selectedSnacks;  // List to store selected snacks

    // Constructor to create a new booking with snacks
    public Booking(String username, String movieName, String showtime, String date, ArrayList<BeverageSnack> selectedSnacks) {
        this.bookingId = idCounter++;
        this.username = username;  // Store the username of the person booking
        this.movieName = movieName;
        this.showtime = showtime;
        this.date = date;
        this.selectedSnacks = selectedSnacks != null ? selectedSnacks : new ArrayList<>(); // Initialize if null
    }

    // Getters
    public int getBookingId() { return bookingId; }
    public String getUsername() { return username; }
    public String getMovieName() { return movieName; }
    public String getShowtime() { return showtime; }
    public String getDate() { return date; }
    public ArrayList<BeverageSnack> getSelectedSnacks() { return selectedSnacks; } // Getter for snacks

    // Override toString to display the booking details, including snacks
    @Override
    public String toString() {
        StringBuilder snackList = new StringBuilder();
        for (BeverageSnack snack : selectedSnacks) {
            snackList.append(snack.getName()).append(" - $").append(snack.getPrice()).append(", ");
        }
        return "Booking ID: " + bookingId + ", Movie: " + movieName +
                ", Showtime: " + showtime + ", Date: " + date +
                ", Snacks: " + (snackList.length() > 0 ? snackList.toString() : "None");
    }
}
