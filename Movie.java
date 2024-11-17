package service;

import java.util.Scanner;

public class Movie {
    private String[] movies = {
        "RRR", "Pushpa", "KGF 2", "Baahubali", 
        "Dangal", "3 Idiots", "PK", "Chhichhore", 
        "Kantara", "Gully Boy"
    };

    private double[] ratings = {8.5, 7.9, 8.4, 8.1, 8.4, 8.4, 8.1, 8.3, 8.3, 7.9};
    private double[] prices = {150, 180, 200, 250, 220, 180, 200, 170, 200, 190};

    public void displayMovies() {
        System.out.println("Available Movies:");
        for (int i = 0; i < movies.length; i++) {
            System.out.printf("%d. %s (Rating: %.1f, Price: ₹%.2f per ticket)%n", 
                              (i + 1), movies[i], ratings[i], prices[i]);
        }
    }

    public int selectMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the movie number you want to watch:");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > movies.length) {
            System.out.println("Invalid choice, try again.");
            return selectMovie();
        }
        System.out.println("You selected: " + movies[choice - 1]);
        return choice;
    }

    public double getMoviePrice(int movieId) {
        return prices[movieId - 1];
    }
}
