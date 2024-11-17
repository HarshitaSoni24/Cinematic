package src.pkg;

public class Payment {

    // Enum for different payment methods
    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, PAYPAL, CASH
    }

    // Method to process the payment
    public static boolean processPayment(User user, double amount, PaymentMethod method) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive amount greater than zero.");
            return false; // Return false if the amount is invalid
        }
        
        switch (method) {
            case CREDIT_CARD:
                return processCreditCardPayment(user, amount);
            case DEBIT_CARD:
                return processDebitCardPayment(user, amount);
            case PAYPAL:
                return processPaypalPayment(user, amount);
            case CASH:
                return processCashPayment(user, amount);
            default:
                System.out.println("Invalid payment method.");
                return false;
        }
    }

    // Simulate processing payment via Credit Card
    private static boolean processCreditCardPayment(User user, double amount) {
        System.out.println("Processing credit card payment for " + user.getName());
        System.out.println("Payment successful. Amount: $" + amount);
        return true;
    }

    // Simulate processing payment via Debit Card
    private static boolean processDebitCardPayment(User user, double amount) {
        System.out.println("Processing debit card payment for " + user.getName());
        System.out.println("Payment successful. Amount: $" + amount);
        return true;
    }

    // Simulate processing payment via PayPal
    private static boolean processPaypalPayment(User user, double amount) {
        System.out.println("Processing PayPal payment for " + user.getName());
        System.out.println("Payment successful. Amount: $" + amount);
        return true;
    }

    // Simulate processing payment via Cash
    private static boolean processCashPayment(User user, double amount) {
        System.out.println("Processing cash payment for " + user.getName());
        System.out.println("Payment successful. Amount: $" + amount);
        return true;
    }

    // Optional: Display available payment methods
    public static void displayAvailablePaymentMethods() {
        System.out.println("Available payment methods:");
        for (PaymentMethod method : PaymentMethod.values()) {
            System.out.println("- " + method);
        }
    }
}
