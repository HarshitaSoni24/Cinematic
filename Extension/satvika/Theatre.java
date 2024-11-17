package src.pkg;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Theatre {
    private String name;
    private String city;
    private ArrayList<String> showtimes;
    private Map<String, ArrayList<String>> showtimeSeats; // Maps showtime -> available seats

    public Theatre(String name, String city) {
        this.name = name;
        this.city = city;
        this.showtimes = new ArrayList<>();
        this.showtimeSeats = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void addShowTime(String showtime) {
        showtimes.add(showtime);
        showtimeSeats.put(showtime, generateSeats()); // Initialize seats for each showtime
    }

    // Generate a simple seating layout, assuming 10 seats per showtime
    private ArrayList<String> generateSeats() {
        ArrayList<String> seats = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            seats.add("Seat " + i); // Adding seats with labels like "Seat 1", "Seat 2", etc.
        }
        return seats;
    }

    // Added this method to get showtimes
    public ArrayList<String> getShowTimes() {
        return showtimes;
    }

    public void displayShowTimes() {
        System.out.println("\n--- Showtimes ---");
        for (int i = 0; i < showtimes.size(); i++) {
            System.out.println((i + 1) + ". " + showtimes.get(i));
        }
    }

    public void displaySeatLayout(String showtime) {
        System.out.println("\n--- Available Seats for " + showtime + " ---");
        ArrayList<String> seats = showtimeSeats.get(showtime);
        if (seats == null || seats.isEmpty()) {
            System.out.println("No available seats.");
            return;
        }
        for (String seat : seats) {
            System.out.print(seat + " ");
        }
        System.out.println(); // For newline
    }

    public boolean isSeatAvailable(String showtime, String seatNumber) {
        ArrayList<String> seats = showtimeSeats.get(showtime);
        return seats != null && seats.contains(seatNumber);
    }

    public void reserveSeats(String showtime, ArrayList<String> selectedSeats) {
        ArrayList<String> availableSeats = showtimeSeats.get(showtime);
        if (availableSeats != null) {
            availableSeats.removeAll(selectedSeats); // Remove the reserved seats
        }
    }

    // Method to check if all seats are booked for the entire theatre for a specific day
    public boolean isFullyBooked(String date) {
        for (String showtime : showtimes) {
            ArrayList<String> seats = showtimeSeats.get(showtime);
            if (seats != null && !seats.isEmpty()) {
                return false; // If there's at least one seat available, it's not fully booked
            }
        }
        return true; // All showtimes are fully booked
    }
}
