package service;
import java.util.HashSet;
import java.util.Set;

public class SeatManager {
    private Set<String> bookedSeats;

    public SeatManager() {
        bookedSeats = new HashSet<>();
    }

    // Book a seat
    public boolean bookSeat(String seat) {
        if (bookedSeats.contains(seat)) {
            return false; // Seat already booked
        }
        bookedSeats.add(seat);
        return true;
    }

    // Display available seats
    public void displayAvailableSeats() {
        System.out.println("Available seats:");
        for (char row = 'A'; row <= 'C'; row++) { // Example rows A, B, C
            for (int col = 1; col <= 5; col++) { // Example 5 seats per row
                String seat = row + String.valueOf(col);
                if (bookedSeats.contains(seat)) {
                    System.out.print("[X] "); // [X] means booked
                } else {
                    System.out.print("[" + seat + "] "); // [A1] means available
                }
            }
            System.out.println();
        }
    }
}
