package service;

import java.util.HashMap;
import java.util.Scanner;

public class Beverages {
    private HashMap<String, Double> beverageMenu = new HashMap<>();

    public Beverages() {
        beverageMenu.put("Popcorn", 120.0);
        beverageMenu.put("Coke", 80.0);
        beverageMenu.put("Pepsi", 80.0);
        beverageMenu.put("Nachos", 150.0);
        beverageMenu.put("Water Bottle", 30.0);
    }

    public double selectBeverages() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Beverage Menu:");
        beverageMenu.forEach((beverage, price) -> System.out.printf("%s: ₹%.2f%n", beverage, price));

        System.out.println("Enter the number of beverages you want to select:");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        double totalBeveragePrice = 0;

        for (int i = 0; i < count; i++) {
            System.out.println("Enter the beverage name:");
            String beverage = scanner.nextLine();
            if (beverageMenu.containsKey(beverage)) {
                totalBeveragePrice += beverageMenu.get(beverage);
            } else {
                System.out.println("Invalid beverage. Try again.");
                i--; // Retry
            }
        }

        return totalBeveragePrice;
    }
}
