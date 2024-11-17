package service;

import java.util.Scanner;

public class Movie {
    private String[] movieNames = {
        "RRR", "Pushpa", "Kantara", "Bahubali: The Conclusion", "The Kashmir Files", 
        "KGF: Chapter 2", "RRR", "Shershaah", "Gully Boy", "Dangal"
    };
    
    private double[] moviePrices = {150, 180, 200, 250, 300, 350, 250, 220, 210, 240}; // Prices per ticket in INR
    private double[] movieRatings = {8.5, 7.9, 8.3, 9.0, 8.2, 8.4, 8.5, 8.7, 8.3, 7.8}; // Ratings out of 10

    public void displayMovies() {
        System.out.println("Available Movies:");
        for (int i = 0; i < movieNames.length; i++) {
            System.out.println((i + 1) + ". " + movieNames[i] + " (Rating: " + movieRatings[i] + ", Price: ₹" + moviePrices[i] + " per ticket)");
        }
    }

    public int selectMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the movie number you want to watch:");
        int movieChoice = scanner.nextInt();
        if (movieChoice < 1 || movieChoice > movieNames.length) {
            System.out.println("Invalid choice. Please try again.");
            return selectMovie();
        }
        System.out.println("You selected: " + movieNames[movieChoice - 1]);
        return movieChoice - 1; // Returning the index of the movie in the list
    }

    public double getMoviePrice(int movieId) {
        return moviePrices[movieId];
    }

    public String getMovieName(int movieId) {
        return movieNames[movieId];
    }

    public double getMovieRating(int movieId) {
        return movieRatings[movieId];
    }
}
