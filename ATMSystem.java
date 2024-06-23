import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ATMSystem {
    private Map<String, User> users;
    private User currentUser;
    private Scanner scanner;

    public ATMSystem() {
        users = new HashMap<>();
        scanner = new Scanner(System.in);
        loadUsers();
    }

    private void loadUsers() {
        // For demonstration, we'll add some users manually
        users.put("user1", new User("Alice", "user1", "1234"));
        users.put("user2", new User("Bob", "user2", "5678"));
    }

    public void start() {
        System.out.println("Welcome to the ATM System!");
        while (true) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            User user = users.get(userId);
            if (user != null && user.checkPin(pin)) {
                currentUser = user;
                System.out.println("Login successful!");
                showMenu();
            } else {
                System.out.println("Invalid User ID or PIN. Please try again.");
            }
        }
    }

    private void showMenu() {
        while (true) {
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdraw();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : currentUser.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private void performWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        currentUser.withdraw(amount);
        System.out.println("Withdrawal successful.");
    }

    private void performDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        currentUser.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private void performTransfer() {
        System.out.print("Enter recipient User ID: ");
        String recipientId = scanner.nextLine();
        User recipient = users.get(recipientId);

        if (recipient != null) {
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            currentUser.transfer(recipient, amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Recipient not found.");
        }
    }
}

