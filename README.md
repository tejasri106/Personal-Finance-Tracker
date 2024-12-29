# Personal-Finance-Tracker
Personal Finance Tracker is a simple Java application designed to help users manage their finances. It allows users to track their checking and savings account balances, record expenses, and view transaction history. The program stores data in external files for persistence and provides an intuitive interface for various financial operations.

Features:

View Balances
- Displays the current balances of checking and savings accounts in a clear, formatted view.

Deposit Money
- Deposit funds into either the checking or savings account.
- Updates balances and logs transactions in a file.

Record Expenses
- Categorize and log expenses under predefined categories or custom categories.
- Automatically deducts the expense from the checking account if sufficient funds are available.

Transfer Money
- Transfer funds between checking and savings accounts.
- Ensures sufficient balance in the source account before transferring.
  
View Transaction History
- Displays all logged transactions from a file for easy review.

Data Persistence
- Balances and transaction history are saved to external files (balances.txt and transactions.txt), ensuring data is retained across sessions.

File Structure: 

balances.txt
Stores the current balances for checking and savings accounts in a simple text format.

transactions.txt
Logs all transactions (deposits, expenses, transfers) with details and timestamps.

Requirements:

Java Development Kit (JDK) 8 or higher.
Basic knowledge of Java and file handling.

How to Use:

Clone the repository and compile the program: javac FinanceTracker.java
Run the program: java FinanceTracker
Follow the prompts to perform operations like depositing funds, recording expenses, and viewing transaction history.

Future Enhancements
Add support for multiple users or accounts.
Implement graphical user interface (GUI) for improved user experience.
Introduce analytics features like expense summaries or spending trends.
