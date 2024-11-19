package src.pkg;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    // Method to get the list of movies
    public List<Movie> getMovies() {
        return movies;  // Return the list of movies
    }

    // Method to edit a movie
    public void editMovie(int index, String newTitle, String newGenre, int newDuration, double newRating, double newPrice) {
        if (index >= 0 && index < movies.size()) {
            Movie movie = movies.get(index);
            movie.setTitle(newTitle);
            movie.setGenre(newGenre);
            movie.setDuration(newDuration);
            movie.setRating(newRating);
            movie.setPrice(newPrice);
        } else {
            System.out.println("Invalid movie index.");
        }
    }

    // Method to delete a movie
    public void deleteMovie(int index) {
        if (index >= 0 && index < movies.size()) {
            movies.remove(index);
            System.out.println("Movie deleted successfully!");
        } else {
            System.out.println("Invalid movie index.");
        }
    }

    // Method to display all users
    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users) {
                System.out.println(user);  // Assuming the User class has a meaningful toString() method
            }
        }
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

    public void displayUserBookings(String username) {
        boolean foundBookings = false;
        for (Booking booking : bookings) {
            if (booking.getUsername().equals(username)) {
                System.out.println(booking);  // Assuming Booking class has a meaningful toString() method
                foundBookings = true;
            }
        }

        if (!foundBookings) {
            System.out.println("No bookings found for user: " + username);
        }
    }
    
    // Method to handle movie ticket bookings
    public void bookTickets(String currentUser, String movieTitle, String theatreName, String showtime, int numberOfTickets, String date, ArrayList<BeverageSnack> selectedSnacks) {
        // Locate the movie by title
        Movie movie = movies.stream()
                            .filter(m -> m.getTitle().equalsIgnoreCase(movieTitle))
                            .findFirst()
                            .orElse(null);

        if (movie == null) {
            System.out.println("Movie not found.");
            return;
        }

        // Locate or validate the theatre
        Theatre theatre = movie.getTheatres().stream()
                               .filter(t -> t.getName().equalsIgnoreCase(theatreName))
                               .findFirst()
                               .orElse(null);

        if (theatre == null) {
            System.out.println("Theatre not found for this movie.");
            return;
        }

        // Verify tickets availability
        if (theatre.getRemainingSeats(showtime) < numberOfTickets) {
            System.out.println("Not enough available seats.");
            return;
        }

        // Proceed with booking
        Booking newBooking = new Booking(currentUser, movieTitle, showtime, date, selectedSnacks);
        bookings.add(newBooking);
        theatre.reserveSeats(showtime, generateSeatList(numberOfTickets));  // Reserve the seats

        System.out.println("Booking successful!");
    }

    private ArrayList<String> generateSeatList(int numberOfSeats) {
        ArrayList<String> selectedSeats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            selectedSeats.add("Seat " + i);
        }
        return selectedSeats;
    }

    // Method to ban a user based on their username
    public boolean banUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setBanned(true);  // Set the user as banned
                return true;  // Successfully banned the user
            }
        }
        return false;  // User not found
    }
    

    // Method to view all available snacks
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
