package src.pkg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private int duration; // Duration in minutes
    private double rating; // Rating out of 10
    private double price; // Price of the ticket
    private List<Theatre> theatres; // List of associated theatres

    // Constructor
    public Movie(String title, String genre, int duration, double rating, double price) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.price = price;
        this.theatres = new ArrayList<>(); // Initialize to prevent null pointer issues
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public List<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<Theatre> theatres) {
        this.theatres = theatres;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ", " + duration + " min) - Rating: " + rating + " - Price: $" + price;
    }
}
