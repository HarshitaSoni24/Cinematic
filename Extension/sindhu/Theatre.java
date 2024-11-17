package service;

import java.util.Scanner;

public class Theatre {
    private String city;
    private int totalTickets;
    private int bookedTickets;
    private String bookedSeat;

    public Theatre(String city) {
        this.city = city;
        this.totalTickets = 100;  // Assume 100 total tickets for simplicity
        this.bookedTickets = 0;
        this.bookedSeat = null;  // No seat booked initially
    }

    public void selectTheater(Scanner scanner) {
        System.out.println("Select the theater in " + city + ":");
        System.out.println("1. Cinema A\n2. Cinema B");
        int choice = Integer.parseInt(scanner.nextLine());
        // Additional theater selection logic can go here, but keeping it simple for now
    }

    // Display available seats (total tickets minus booked tickets)
    public void displayAvailableSeats() {
        int availableSeats = totalTickets - bookedTickets;
        System.out.println("Available tickets: " + availableSeats);
    }

    // Method to book a ticket
    public boolean bookSeat(String seat) {
        if (bookedTickets < totalTickets) {
            bookedTickets++;
            bookedSeat = seat;
            System.out.println("Seat " + seat + " booked successfully.");
            return true;
        } else {
            System.out.println("No tickets available.");
            return false;
        }
    }

    // Method to cancel a booked ticket
    public boolean cancelTicket(String seat) {
        if (bookedSeat != null && bookedSeat.equals(seat)) {
            bookedTickets--;
            bookedSeat = null;
            System.out.println("Ticket for seat " + seat + " has been canceled successfully.");
            return true;
        } else {
            System.out.println("No such booking found to cancel.");
            return false;
        }
    }

    public String getTheaterName() {
        return "Cinema A";  // Placeholder, modify as needed
    }

    public int getAvailableTickets() {
        return totalTickets - bookedTickets;
    }

    public String getBookedSeat() {
        return bookedSeat;
    }
}
