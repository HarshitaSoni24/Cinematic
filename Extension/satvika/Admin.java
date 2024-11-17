package src.pkg;

import java.util.Scanner;

public class Admin {
    private MovieDatabase movieDatabase;

    public Admin(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Display Movies");
            System.out.println("2. Add Movie");
            System.out.println("3. Edit Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. View Users and Ban User");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    movieDatabase.displayMovies();
                    break;

                case 2:
                    // Add new movie
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter movie duration (in minutes): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter movie price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter movie genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter movie rating (out of 10): ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    // Create new movie and add to the database
                    movieDatabase.addMovie(title, genre, duration, rating, price);
                    System.out.println("Movie added successfully!");
                    break;

                case 3:
                    movieDatabase.displayMovies();
                    System.out.print("Enter movie number to edit: ");
                    int movieIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (movieIndex < 1 || movieIndex > movieDatabase.getMovies().size()) {
                        System.out.println("Invalid movie number. Try again.");
                        break;
                    }

                    // Collect new movie details
                    System.out.print("Enter new movie title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new movie duration (in minutes): ");
                    int newDuration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new movie price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new movie genre: ");
                    String newGenre = scanner.nextLine();
                    System.out.print("Enter new movie rating (out of 10): ");
                    double newRating = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    // Edit the movie details
                    movieDatabase.editMovie(movieIndex - 1, newTitle, newGenre, newDuration, newRating, newPrice);
                    System.out.println("Movie updated successfully!");
                    break;

                case 4:
                    movieDatabase.displayMovies();
                    System.out.print("Enter movie number to delete: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (deleteIndex < 1 || deleteIndex > movieDatabase.getMovies().size()) {
                        System.out.println("Invalid movie number. Try again.");
                        break;
                    }
                    movieDatabase.deleteMovie(deleteIndex - 1);
                    System.out.println("Movie deleted successfully!");
                    break;

                case 5:
                    movieDatabase.displayUsers();
                    System.out.print("Enter username to ban: ");
                    String usernameToBan = scanner.nextLine();
                    if (movieDatabase.banUser(usernameToBan)) {
                        System.out.println("User " + usernameToBan + " has been banned.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 6:
                    exit = true;
                    System.out.println("Exiting admin menu.");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
