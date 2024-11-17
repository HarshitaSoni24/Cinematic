package src.pkg;

import java.io.*;
import java.util.ArrayList;

public class MovieDatabase {
    private ArrayList<Movie> movies;  // List to hold all movies
    private ArrayList<User> users;    // List to hold registered users
    private ArrayList<Booking> bookings;  // List to hold all bookings
    private ArrayList<BeverageSnack> availableSnacks; // List to hold available snacks
    private static final String USERS_FILE = "users.dat"; // File to store user data

    // Constructor
    public MovieDatabase() {
        movies = new ArrayList<>();
        users = new ArrayList<>();
        bookings = new ArrayList<>();
        availableSnacks = new ArrayList<>();
        loadUsers(); // Load users from file on initialization
        loadSnacks(); // Load available snacks
    }

    // Load users from the file
    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous user data found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Save users to the file
    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load snacks from a predefined list or a file
    private void loadSnacks() {
        availableSnacks.add(new BeverageSnack("Popcorn", 5.0));
        availableSnacks.add(new BeverageSnack("Soda", 3.0));
        availableSnacks.add(new BeverageSnack("Candy", 2.5));
        availableSnacks.add(new BeverageSnack("Nachos", 4.0));
    }

    // Method to register a user
    public void registerUser(String username, String password, String name, String phoneNumber) {
        User newUser = new User(username, password, name, phoneNumber);
        users.add(newUser);
        saveUsers();  // Save after registration
    }

    // Method to login a user (returns true if login is successful)
    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method to display a user's bookings
    public void displayUserBookings(String username) {
        boolean foundBookings = false;
        for (Booking booking : bookings) {
            if (booking.getUsername().equals(username)) {
                System.out.println("Booking ID: " + booking.getBookingId() +
                                   ", Movie: " + booking.getMovieName() +
                                   ", Showtime: " + booking.getShowtime() +
                                   ", Date: " + booking.getDate() +
                                   ", Snacks: " + booking.getSelectedSnacks());
                foundBookings = true;
            }
        }
        if (!foundBookings) {
            System.out.println("No bookings found.");
        }
    }

    // Method to cancel a booking
    public boolean cancelBooking(String username, int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getUsername().equals(username) && booking.getBookingId() == bookingId) {
                bookings.remove(booking);  // Remove the booking from the list
                System.out.println("Booking canceled successfully.");
                return true;
            }
        }
        System.out.println("Booking not found for the given username and booking ID.");
        return false;
    }

    // Method to add a booking (with snacks)
    // Modify the addBooking method in MovieDatabase to handle snacks
    public void addBooking(String username, String movieName, String showtime, String date, ArrayList<BeverageSnack> selectedSnacks) {
        Booking newBooking = new Booking(username, movieName, showtime, date, selectedSnacks);
        bookings.add(newBooking);
        System.out.println("Booking added: " + newBooking);
    }


    // Method to display all users (admin-only functionality)
    public void displayUsers() {
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + ", Name: " + user.getName());
        }
    }

    // Method to ban a user (admin-only functionality)
    public void banUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
        System.out.println("User " + username + " has been banned.");
        saveUsers();  // Save after banning a user
    }

    // Method to display all movies
    public void displayMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        }
    }

    // Method to add a movie
    public void addMovie(String title, String genre, int duration, double rating, double price) {
        Movie newMovie = new Movie(title, genre, duration, rating, price);
        movies.add(newMovie);
        System.out.println("Movie added: " + newMovie);
    }

    // Method to delete a movie by title
    public void deleteMovie(String title) {
        Movie movieToDelete = null;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                movieToDelete = movie;
                break;
            }
        }

        if (movieToDelete != null) {
            movies.remove(movieToDelete);
            System.out.println("Movie '" + title + "' has been deleted.");
        } else {
            System.out.println("Movie not found with title: " + title);
        }
    }

    // Method to display available snacks
    public void displayAvailableSnacks() {
        if (availableSnacks.isEmpty()) {
            System.out.println("No snacks available.");
        } else {
            for (BeverageSnack snack : availableSnacks) {
                System.out.println(snack);
            }
        }
    }
}
