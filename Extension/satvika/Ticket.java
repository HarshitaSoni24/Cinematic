package src.pkg;

public class Ticket {
    private Movie movie;
    private int numberOfTickets;
    private double totalPrice;
    private String showTime;

    // Constructor to create a new ticket
    public Ticket(Movie movie, int numberOfTickets, double pricePerTicket, String showTime) {
        this.movie = movie;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = numberOfTickets * pricePerTicket; // Calculate the total price
        this.showTime = showTime;
    }

    // Getter for the movie
    public Movie getMovie() {
        return movie;
    }

    // Getter for the total price of the tickets
    public double getTotalPrice() {
        return totalPrice;
    }

    // Override toString to display ticket information
    @Override
    public String toString() {
        return "Movie: " + movie.getTitle() + ", Tickets: " + numberOfTickets +
               ", Total Price: $" + totalPrice + ", Showtime: " + showTime;
    }
}
