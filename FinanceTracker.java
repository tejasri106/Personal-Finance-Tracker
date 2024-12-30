import java.util.*;
import java.io.*;

/**
 * The FinanceTracker class is a personal finance management system that allows users
 * to manage their checking and savings accounts, record expenses, transfer money
 * between accounts, and view transaction history. 
 * Balances are saved and loaded from files to maintain state between sessions.
 */
public class FinanceTracker {
    private double checkingsBalance; // Balance of the checking account
    private double savingsBalance; // Balance of the savings account
    private String[] expenseCategories = {"Groceries", "Rent", "Clothes", "Entertainment", "Food", "Home", "Other"};

    /**
     * Constructs a FinanceTracker object with default balances of 0.0 and loads
     * balances from the file if available.
     */
    public FinanceTracker() {
        this.checkingsBalance = 0.0;
        this.savingsBalance = 0.0;
        loadBalances();
    }

    /**
     * Gets the checking account balance.
     * @return the balance of the checking account.
     */
    public double getCheckingsBalance() {
        return checkingsBalance;
    }

    /**
     * Sets the checking account balance.
     * @param balance the new balance for the checking account.
     */
    public void setCheckingsBalance(double balance) {
        this.checkingsBalance = balance;
    }

    /**
     * Sets the savings account balance.
     * @param balance the new balance for the savings account.
     */
    public void setSavingsBalance(double balance) {
        this.savingsBalance = balance;
    }

    /**
     * Displays the balances of the checking and savings accounts.
     */
    public void viewBalances() {
        System.out.printf("Checking Account: $%.2f %n", checkingsBalance);
        System.out.printf("Savings Account: $%.2f %n", savingsBalance);
    }

    /**
     * Allows the user to deposit money into either the checking or savings account.
     * @param scanner a Scanner object for user input.
     */
    public void depositMoney(Scanner scanner) {
        while (true) {
            System.out.println("Which account do you want to deposit to? (1. Checking, 2. Savings)");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("Enter the amount you want to deposit: ");
                double amount = scanner.nextDouble();
                checkingsBalance += amount;
                saveBalances();
                writeToFile(String.format("$%.2f deposited to checking account.", amount));
                System.out.printf("$%.2f deposited to your checking account.%n", amount);
                System.out.println();
                break;
            } else if (choice == 2) {
                System.out.println("Enter the amount you want to deposit: ");
                double amount = scanner.nextDouble();
                savingsBalance += amount;
                saveBalances();
                writeToFile(String.format("$%.2f deposited to savings account.", amount));
                System.out.printf("$%.2f deposited to your savings account.%n", amount);
                System.out.println();
                break;
            } else {
                System.out.println("Invalid choice. Enter Again");
            }
        }
    }

    /**
     * Records an expense in a specific category and deducts it from the checking account balance.
     * @param scanner a Scanner object for user input.
     */
    public void recordExpenses(Scanner scanner) {
        System.out.println("Select the expense you want to record: ");
        for (int i = 0; i < expenseCategories.length; i++) {
            System.out.println("[" + (i + 1) + "] " + expenseCategories[i]);
        }
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                System.out.println("Enter the amount of money spent on this expense: ");
                double amount = scanner.nextDouble();
                if (checkingsBalance >= amount) {
                    checkingsBalance -= amount;
                    saveBalances();
                    writeToFile(String.format("%s: $%.2f spent", expenseCategories[choice - 1], amount));
                    System.out.printf("Remaining balance in checking: $%.2f%n", checkingsBalance);
                    writeToFile(String.format("Remaining balance in checking: $%.2f%n", checkingsBalance));
                } else {
                    System.out.println("Not enough balance in checkings account.");
                }
                break;

            case 7:
                System.out.println("Specify the category");
                String otherCategory = scanner.next();
                System.out.println("Enter the amount of money spent on this expense: ");
                double otherAmount = scanner.nextDouble();
                if (checkingsBalance >= otherAmount) {
                    checkingsBalance -= otherAmount;
                    saveBalances();
                    writeToFile(String.format("%s: $%.2f spent", otherCategory, otherAmount));
                    System.out.printf("Remaining balance in checking: $%.2f%n", checkingsBalance);
                    writeToFile(String.format("Remaining balance in checking: $%.2f%n", checkingsBalance));
                } else {
                    System.out.println("Not enough balance in checkings account.");
                }
                break;
            default:
                System.out.println("That is not a choice");
        }
    }

    /**
     * Transfers money between the checking and savings accounts.
     * @param scanner a Scanner object for user input.
     */
    public void transferMoney(Scanner scanner) {
        System.out.println("Select: [1] Savings to Checkings [2] Checkings to Savings");
        int choice = scanner.nextInt();
        System.out.println("How much money do you want to transfer?");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be positive!");
        }

        if (choice == 1 && savingsBalance >= amount) {
            savingsBalance -= amount;
            checkingsBalance += amount;
            saveBalances();
            writeToFile(String.format("$%.2f transferred from savings to checking account.", amount));
            writeToFile(String.format("Balance in checking: $%.2f", checkingsBalance));
            writeToFile(String.format("Balance in savings: $%.2f", savingsBalance));
            System.out.printf("$%.2f transferred from savings to checking account successfully.%n", amount);
            viewBalances();
        } else if (choice == 2 && checkingsBalance >= amount) {
            savingsBalance += amount;
            checkingsBalance -= amount;
            saveBalances();
            writeToFile(String.format("$%.2f transferred from checking to savings account.", amount));
            writeToFile(String.format("Balance in checking: $%.2f", checkingsBalance));
            writeToFile(String.format("Balance in savings: $%.2f", savingsBalance));
            System.out.printf("$%.2f transferred from checking to savings account successfully.%n", amount);
            viewBalances();
        } else if (choice != 1 && choice != 2) {
            System.out.println("Invalid choice!");
        } else {
            System.out.println("Not enough balance in the chosen account to transfer!");
            viewBalances();
        }
    }

    /**
     * Displays the transaction history by reading from the transactions.txt file.
     */
    public void viewTransactionHistory() {
        System.out.println();
        System.out.println("=== Transaction History ===");
        Scanner fileScanner = null;
        String line;
        try {
            fileScanner = new Scanner(new FileInputStream("transactions.txt"));
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }

    /**
     * Writes a log entry to the transactions.txt file.
     * @param log the log message to write.
     */
    public void writeToFile(String log) {
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter(new FileOutputStream("transactions.txt", true));
            fileWriter.println(log);
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    /**
     * Loads the checking and savings account balances from the balances.txt file.
     */
    public void loadBalances() {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new FileInputStream("balances.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");
                if (parts[0].equalsIgnoreCase("checking")) {
                    this.checkingsBalance = Double.parseDouble(parts[1]);
                } else if (parts[0].equalsIgnoreCase("savings")) {
                    this.savingsBalance = Double.parseDouble(parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            this.checkingsBalance = 0.00;
            this.savingsBalance = 0.00;
        } catch (NumberFormatException e) {
            System.out.println("Invalid data in balances file. Starting with default balances.");
            this.checkingsBalance = 0.00;
            this.savingsBalance = 0.00;
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }

    /**
     * Saves the checking and savings account balances to the balances.txt file.
     */
    public void saveBalances() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream("balances.txt"));
            writer.printf("checking:%.2f%n", this.checkingsBalance);
            writer.printf("savings:%.2f%n", this.savingsBalance);
        } catch (IOException e) {
            System.out.println("Error saving balances: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
