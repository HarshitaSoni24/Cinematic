package src.cineflix;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import src.pkg.*;

public class Main {

    private static Theatre theatre1;
    private static Theatre theatre2;
    private static Theatre imaxTheatre;
    private static ArrayList<BeverageSnack> snacks;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieDatabase movieDatabase = new MovieDatabase();

        // Initialize theatres
        theatre1 = new Theatre("Cineflix Theatre A", "Hyderabad");
        theatre2 = new Theatre("Cineflix Theatre B", "Hyderabad");
        imaxTheatre = new Theatre("IMAX Theatre", "Hyderabad");
        theatre1.addShowTime("10:00 AM");
        theatre1.addShowTime("01:00 PM");
        theatre2.addShowTime("04:00 PM");
        imaxTheatre.addShowTime("06:00 PM");

        // Initialize snacks
        snacks = new ArrayList<>();
        snacks.add(new BeverageSnack("Popcorn", 5.0));
        snacks.add(new BeverageSnack("Soda", 3.0));
        snacks.add(new BeverageSnack("Candy", 2.5));
        snacks.add(new BeverageSnack("Nachos", 4.0));

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Welcome to Cineflix Theatre ---");
            System.out.println("1. Admin Login");
            System.out.println("2. User Registration / Login");
            System.out.println("3. Guest Access");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    adminLogin(scanner, movieDatabase);
                    break;
                case 2:
                    userLoginOrRegister(scanner, movieDatabase);
                    break;
                case 3:
                    guestAccess(scanner);
                    break;
                case 4:
                    System.out.println("\nThank you for visiting Cineflix Theatre!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void adminLogin(Scanner scanner, MovieDatabase movieDatabase) {
        System.out.println("\n--- Admin Login ---");
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if ("admin".equals(username) && "admin123".equals(password)) {
            System.out.println("Admin login successful!");
            adminMenu(scanner, movieDatabase);
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void adminMenu(Scanner scanner, MovieDatabase movieDatabase) {
        boolean adminExit = false;
        while (!adminExit) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Display Users");
            System.out.println("2. Ban User");
            System.out.println("3. Return to Main Menu");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    movieDatabase.displayUsers();
                    break;
                case 2:
                    banUser(scanner, movieDatabase);
                    break;
                case 3:
                    adminExit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void banUser(Scanner scanner, MovieDatabase movieDatabase) {
        System.out.print("\nEnter the username of the user to ban: ");
        String username = scanner.nextLine();
        movieDatabase.banUser(username);
    }

    private static void userLoginOrRegister(Scanner scanner, MovieDatabase movieDatabase) {
        boolean returnToMain = false;
        while (!returnToMain) {
            System.out.println("\n--- User Login / Register ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Return to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    loginUser(scanner, movieDatabase);
                    returnToMain = true;
                    break;
                case 2:
                    registerUser(scanner, movieDatabase);
                    returnToMain = true;
                    break;
                case 3:
                    returnToMain = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void loginUser(Scanner scanner, MovieDatabase movieDatabase) {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (movieDatabase.loginUser(username, password)) {
            System.out.println("Login successful!");
            userMenu(scanner, movieDatabase, username);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void registerUser(Scanner scanner, MovieDatabase movieDatabase) {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Add phone number validation here
        String phoneNumber = getPhoneNumber();

        movieDatabase.registerUser(username, password, name, phoneNumber);
        System.out.println("Registration successful! You can now login.");
    }

    private static void userMenu(Scanner scanner, MovieDatabase movieDatabase, String username) {
        boolean returnToMain = false;
        while (!returnToMain) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Theatres and Shows");
            System.out.println("2. View My Bookings");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. Return to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    displayTheatresAndShows(scanner, movieDatabase, username);
                    break;
                case 2:
                    displayUserBookings(username, movieDatabase);
                    break;
                case 3:
                    cancelBooking(scanner, movieDatabase, username);
                    break;
                case 4:
                    returnToMain = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void displayUserBookings(String username, MovieDatabase movieDatabase) {
        System.out.println("\n--- My Bookings ---");
        movieDatabase.displayUserBookings(username);
    }

    private static void cancelBooking(Scanner scanner, MovieDatabase movieDatabase, String username) {
        System.out.println("\n--- Cancel a Booking ---");
        movieDatabase.displayUserBookings(username);
        System.out.print("Enter the booking number to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = movieDatabase.cancelBooking(username, bookingId);
        if (success) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Invalid booking number.");
        }
    }

    private static void guestAccess(Scanner scanner) {
        boolean returnToMain = false;
        while (!returnToMain) {
            System.out.println("\n--- Guest Access ---");
            System.out.println("1. View Theatres and Shows");
            System.out.println("2. Return to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    displayTheatresAndShows(scanner, null, null);
                    break;
                case 2:
                    returnToMain = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void displayTheatresAndShows(Scanner scanner, MovieDatabase movieDatabase, String username) {
        System.out.println("\n--- Select a Theatre ---");
        System.out.println("1. Cineflix Theatre A (Hyderabad)");
        System.out.println("2. Cineflix Theatre B (Hyderabad)");
        System.out.println("3. IMAX Theatre (Hyderabad)");
        System.out.println("4. Return");
        System.out.print("Choose an option: ");
        int theatreChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Theatre selectedTheatre = null;
        switch (theatreChoice) {
            case 1:
                selectedTheatre = theatre1;
                break;
            case 2:
                selectedTheatre = theatre2;
                break;
            case 3:
                selectedTheatre = imaxTheatre;
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                return;
        }

        if (selectedTheatre != null) {
            displayAvailableDates(selectedTheatre, movieDatabase, username);
        }
    }

    private static void displayAvailableDates(Theatre theatre, MovieDatabase movieDatabase, String username) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Select Date ---");
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (EEEE)");
    
        // Display the dates with the day names and availability
        for (int i = 0; i < 14; i++) {
            LocalDate date = today.plusDays(i);
            String formattedDate = date.format(dateFormatter);
            if (theatre.isFullyBooked(formattedDate)) {
                System.out.println((i + 1) + ". " + formattedDate + " **FULL**");
            } else {
                System.out.println((i + 1) + ". " + formattedDate);
            }
        }
        System.out.println("15. Return");
    
        // Read the user input for date selection
        System.out.print("Select a date or choose 15 to return: ");
        int dateOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        if (dateOption == 15) {
            return;
        } else if (dateOption > 0 && dateOption <= 14) {
            LocalDate selectedDate = today.plusDays(dateOption - 1);
            String formattedDate = selectedDate.format(dateFormatter);
    
            System.out.println("\nSelected date: " + formattedDate);
            if (theatre.isFullyBooked(formattedDate)) {
                System.out.println("Sorry, the selected date is fully booked.");
            } else {
                // Display showtimes for the selected date
                theatre.displayShowTimes();
                System.out.print("Select a showtime number: ");
                int showtimeOption = scanner.nextInt();
                scanner.nextLine(); // Consume newline
    
                if (showtimeOption > 0 && showtimeOption <= theatre.getShowTimes().size()) {
                    String selectedShowtime = theatre.getShowTimes().get(showtimeOption - 1);
                    theatre.displaySeatLayout(selectedShowtime);
    
                    // Proceed with booking and snack selection
                    bookTickets(scanner, theatre, selectedShowtime, movieDatabase, username);
                } else {
                    System.out.println("Invalid showtime option.");
                }
            }
        } else {
            System.out.println("Invalid option. Returning to the main menu.");
        }
    }

    private static void bookTickets(Scanner scanner, Theatre theatre, String selectedShowtime, MovieDatabase movieDatabase, String username) {
        System.out.println("\n--- Book Tickets ---");
        // Select the number of tickets and proceed with snack selection
        System.out.print("Enter the number of tickets: ");
        int numTickets = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Select snacks
        System.out.println("\n--- Select Snacks ---");
        for (int i = 0; i < snacks.size(); i++) {
            System.out.println((i + 1) + ". " + snacks.get(i).getName() + " - Rs " + snacks.get(i).getPrice());
        }
        System.out.println("Enter snack numbers separated by commas (e.g., 1,2,3): ");
        String snackSelection = scanner.nextLine();

        // Confirm the booking
        boolean bookingSuccess = movieDatabase.bookTickets(username, theatre.getName(), selectedShowtime, numTickets, snackSelection);
        if (bookingSuccess) {
            System.out.println("Booking confirmed! Enjoy your movie.");
        } else {
            System.out.println("Booking failed.");
        }
    }

    private static String getPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        String phoneNumber;

        // Loop until a valid 10-digit phone number is entered
        while (true) {
            System.out.print("Enter your 10-digit phone number: ");
            phoneNumber = scanner.nextLine();

            // Check if the phone number is exactly 10 digits
            if (phoneNumber.length() == 10 && phoneNumber.matches("\\d{10}")) {
                System.out.println("Phone number is valid.");
                break;  // Exit the loop if phone number is valid
            } else {
                System.out.println("Invalid phone number! Please enter exactly 10 digits.");
            }
        }
        return phoneNumber;
    }
}
