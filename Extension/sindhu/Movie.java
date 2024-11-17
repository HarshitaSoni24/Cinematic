package service;

import java.util.Scanner;

public class Movie {
    private String[] movies = {"Movie A", "Movie B", "Movie C"};
    private double[] prices = {150.0, 200.0, 250.0};

    // Method to display movies and select a movie, which accepts a Scanner argument
    public void displayMovies() {
        System.out.println("Available movies:");
        for (int i = 0; i < movies.length; i++) {
            System.out.println((i + 1) + ". " + movies[i]);
        }
    }

    public int selectMovie(Scanner scanner) {
        int choice = -1;

        // Loop until a valid movie is selected
        while (choice < 1 || choice > movies.length) {
            System.out.println("Select a movie by number:");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice < 1 || choice > movies.length) {
                System.out.println("Invalid choice, please try again.");
            }
        }
        return choice - 1; // Return movie index (0-based)
    }

    public double getMoviePrice(int movieIndex) {
        return prices[movieIndex];
    }

    public String getMovieName(int movieIndex) {
        return movies[movieIndex];
    }
}
