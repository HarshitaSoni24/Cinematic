package service;

import java.util.Scanner;

public class Location {
    private String city;

    // Method to choose location, which accepts a Scanner argument
    public void chooseLocation(Scanner scanner) {
        System.out.println("Select your location (City):");
        System.out.println("1. Hyderabad");
        System.out.println("2. Mumbai");
        System.out.println("3. Bengaluru");

        // Loop until a valid choice is made
        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    this.city = "Hyderabad";
                    break;
                case 2:
                    this.city = "Mumbai";
                    break;
                case 3:
                    this.city = "Bengaluru";
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    continue; // Repeat the loop until a valid choice is made
            }
            break; // Exit the loop if a valid choice is made
        }

        System.out.println("You selected: " + city);
    }

    public String getCity() {
        return this.city;
    }
}
