package service;

import java.util.Scanner;

public class Beverages {

    // Method to select beverages
    public double selectBeverages(Scanner scanner) {
        double totalPrice = 0;
        System.out.println("Select beverages:");
        System.out.println("1. Coke - ₹50");
        System.out.println("2. Pepsi - ₹45");
        System.out.println("3. Water - ₹20");
        System.out.println("4. None");

        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            switch (choice) {
                case 1:
                    totalPrice += 50;
                    break;
                case 2:
                    totalPrice += 45;
                    break;
                case 3:
                    totalPrice += 20;
                    break;
                case 4:
                    System.out.println("No beverages selected.");
                    return totalPrice;
                default:
                    System.out.println("Invalid choice, please select again.");
                    continue;  // Loop again until valid input
            }

            System.out.println("Would you like to add another beverage? (yes/no)");
            String addMore = scanner.nextLine().trim().toLowerCase();
            if (!addMore.equals("yes")) {
                break;
            }
        }

        return totalPrice;
    }
}
