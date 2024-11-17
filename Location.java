package service;

import java.util.Scanner;

public class Location {
    private String city;

    public void chooseLocation() {
        Scanner scanner = new Scanner(System.in);

        // States and Cities in India
        System.out.println("Choose your state:");
        System.out.println("1. Telangana");
        System.out.println("2. Maharashtra");
        System.out.println("3. Karnataka");
        int stateChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Set cities based on state
        switch (stateChoice) {
            case 1:
                System.out.println("Choose your city:");
                System.out.println("1. Hyderabad");
                System.out.println("2. Warangal");
                System.out.println("3. Nizamabad");
                break;
            case 2:
                System.out.println("Choose your city:");
                System.out.println("1. Mumbai");
                System.out.println("2. Pune");
                System.out.println("3. Nagpur");
                break;
            case 3:
                System.out.println("Choose your city:");
                System.out.println("1. Bangalore");
                System.out.println("2. Mysore");
                System.out.println("3. Hubli");
                break;
            default:
                System.out.println("Invalid state choice. Restarting...");
                chooseLocation();
                return;
        }

        int cityChoice = scanner.nextInt();
        switch (cityChoice) {
            case 1:
                city = stateChoice == 1 ? "Hyderabad" : (stateChoice == 2 ? "Mumbai" : "Bangalore");
                break;
            case 2:
                city = stateChoice == 1 ? "Warangal" : (stateChoice == 2 ? "Pune" : "Mysore");
                break;
            case 3:
                city = stateChoice == 1 ? "Nizamabad" : (stateChoice == 2 ? "Nagpur" : "Hubli");
                break;
            default:
                System.out.println("Invalid city choice. Restarting...");
                chooseLocation();
                return;
        }
        System.out.println("You selected: " + city);
    }

    public String getCity() {
        return city;
    }
}
