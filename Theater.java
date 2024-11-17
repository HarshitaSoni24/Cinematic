package service;

import java.util.Scanner;

public class Theater {
    private String city;
    private String selectedTheater;
    private String selectedSeats;

    // Constructor takes city name to adjust theater options
    public Theater(String city) {
        this.city = city;
    }

    public void selectTheater() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available Theaters in " + city + ":");
        // Dynamically show theaters based on city
        if (city.equals("Hyderabad")) {
            System.out.println("1. PVR Hyderabad");
            System.out.println("2. INOX Hyderabad");
            System.out.println("3. Cinepolis Hyderabad");
        } else if (city.equals("Mumbai")) {
            System.out.println("1. PVR Mumbai");
            System.out.println("2. INOX Mumbai");
            System.out.println("3. Cinepolis Mumbai");
        } else if (city.equals("Bangalore")) {
            System.out.println("1. PVR Bangalore");
            System.out.println("2. INOX Bangalore");
            System.out.println("3. Cinepolis Bangalore");
        }
        // Add similar logic for other cities

        int theaterChoice = scanner.nextInt();
        switch (theaterChoice) {
            case 1:
                selectedTheater = "PVR " + city;
                break;
            case 2:
                selectedTheater = "INOX " + city;
                break;
            case 3:
                selectedTheater = "Cinepolis " + city;
                break;
            default:
                System.out.println("Invalid choice. Restarting...");
                selectTheater();
                return;
        }
        System.out.println("You selected: " + selectedTheater);
    }

    public void selectSeats() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of seats you want to book:");
        int seats = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        selectedSeats = "";
        for (int i = 1; i <= seats; i++) {
            System.out.println("Enter seat number for booking (e.g., 1A, 2B):");
            String seat = scanner.nextLine();
            selectedSeats += seat + " ";
        }
        System.out.println("Seats booked successfully: " + selectedSeats);
    }

    public String getTheaterName() {
        return selectedTheater;
    }

    public String getSelectedSeats() {
        return selectedSeats;
    }

    public int getSelectedSeatsCount() {
        return selectedSeats.split(" ").length;
    }
}
