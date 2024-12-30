import java.util.Scanner;

/**
 * Main class to run the Personal Finance Tracker application.
 * This program allows users to manage their personal finances by
 * viewing balances, depositing money, recording expenses, transferring funds,
 * and viewing transaction history.
 * 
 * Features:
 * - Checkings and savings account management
 * - Expense tracking with categories
 * - Transaction history logging
 * 
 * @version 1.0
 */
public class Main {
    /**
     * The main method to start the Personal Finance Tracker application.
     * It provides a menu-driven interface for the user to interact with the
     * FinanceTracker class.
     * 
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Initialize the FinanceTracker object
        FinanceTracker tracker = new FinanceTracker();
        // Initialize a Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice; // User's menu choice

        // Prompt the user to enter the current date for transaction logging
        System.out.println("Enter the date (mm/dd/yyyy): ");
        String date = scanner.nextLine();

        // Write the date to the transaction log file
        tracker.writeToFile("");
        tracker.writeToFile(date);
        tracker.writeToFile("");

        // Display menu options until the user chooses to exit
        do {
            // Display the main menu
            System.out.println("\n=== Personal Finance Tracker ===");
            tracker.viewBalances(); // Show current balances
            System.out.println("1. View Balances");
            System.out.println("2. Deposit Money");
            System.out.println("3. Record an Expense");
            System.out.println("4. Transfer Money (Checking <-> Savings)");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            // Get the user's choice
            choice = scanner.nextInt();

            // Execute the selected action
            switch (choice) {
                case 1 -> tracker.viewBalances(); // View account balances
                case 2 -> tracker.depositMoney(scanner); // Deposit money
                case 3 -> tracker.recordExpenses(scanner); // Record an expense
                case 4 -> tracker.transferMoney(scanner); // Transfer money between accounts
                case 5 -> tracker.viewTransactionHistory(); // View transaction history
                case 6 -> System.out.println("Goodbye!"); // Exit the application
                default -> System.out.println("Invalid option. Try again."); // Handle invalid choices
            }
        } while (choice != 6); // Exit when user chooses option 6

        // Close the Scanner to release resources
        scanner.close();
    }
}
