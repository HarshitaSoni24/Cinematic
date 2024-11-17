package service;
import java.util.HashMap;

public class TicketDatabase {
    private HashMap<String, Integer> tickets;

    public TicketDatabase() {
        tickets = new HashMap<>();
    }

    // Initialize ticket count for a movie
    public void addMovie(String movieName, int ticketsAvailable) {
        tickets.put(movieName, ticketsAvailable);
    }

    // Book tickets
    public boolean bookTickets(String movieName, int ticketsToBook) {
        if (tickets.containsKey(movieName) && tickets.get(movieName) >= ticketsToBook) {
            tickets.put(movieName, tickets.get(movieName) - ticketsToBook);
            return true;
        }
        return false; // Not enough tickets
    }

    // Check available tickets
    public int getAvailableTickets(String movieName) {
        return tickets.getOrDefault(movieName, 0);
    }
}
