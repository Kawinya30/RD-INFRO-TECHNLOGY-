import java.util.*;

public class BankingSystem {

    // BankAccount Class to represent each customer account
    static class BankAccount {
        private String accountNumber;
        private String accountHolder;
        private double balance;

        public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.balance = initialBalance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getAccountHolder() {
            return accountHolder;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited " + amount + ". New balance: " + balance);
            } else {
                System.out.println("Deposit amount must be positive!");
            }
        }

        public void withdraw(double amount) {
            if (amount <= balance && amount > 0) {
                balance -= amount;
                System.out.println("Withdrew " + amount + ". New balance: " + balance);
            } else {
                System.out.println("Insufficient funds or invalid withdrawal amount!");
            }
        }

        public void transfer(BankAccount toAccount, double amount) {
            if (amount <= balance && amount > 0) {
                withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Transferred " + amount + " to account " + toAccount.getAccountNumber());
            } else {
                System.out.println("Transfer failed! Invalid amount or insufficient balance.");
            }
        }
    }

    // Bank Class to manage all accounts and perform operations
    static class Bank {
        private Map<String, BankAccount> accounts;

        public Bank() {
            accounts = new HashMap<>();
        }

        public void addAccount(BankAccount account) {
            accounts.put(account.getAccountNumber(), account);
            System.out.println("Account created: " + account.getAccountNumber());
        }

        public BankAccount getAccount(String accountNumber) {
            return accounts.get(accountNumber);
        }

        public boolean accountExists(String accountNumber) {
            return accounts.containsKey(accountNumber);
        }

        public void listAccounts() {
            System.out.println("Listing all accounts:");
            for (BankAccount account : accounts.values()) {
                System.out.println("Account Number: " + account.getAccountNumber() + ", Holder: " + account.getAccountHolder() + ", Balance: " + account.getBalance());
            }
        }
    }

    // Main Class to interact with the user via the console
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n--- Bank System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Account Details");
            System.out.println("6. List All Accounts");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner, bank);
                    break;
                case 2:
                    deposit(scanner, bank);
                    break;
                case 3:
                    withdraw(scanner, bank);
                    break;
                case 4:
                    transfer(scanner, bank);
                    break;
                case 5:
                    viewAccountDetails(scanner, bank);
                    break;
                case 6:
                    bank.listAccounts();
                    break;
                case 7:
                    System.out.println("Exiting system...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Create a new bank account
    private static void createAccount(Scanner scanner, Bank bank) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter account holder's name: ");
        String holderName = scanner.next();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        if (!bank.accountExists(accountNumber)) {
            BankAccount account = new BankAccount(accountNumber, holderName, initialBalance);
            bank.addAccount(account);
        } else {
            System.out.println("Account with this number already exists!");
        }
    }

    // Deposit money into an account
    private static void deposit(Scanner scanner, Bank bank) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = bank.getAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Withdraw money from an account
    private static void withdraw(Scanner scanner, Bank bank) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = bank.getAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Transfer money from one account to another
    private static void transfer(Scanner scanner, Bank bank) {
        System.out.print("Enter your account number: ");
        String fromAccountNumber = scanner.next();
        BankAccount fromAccount = bank.getAccount(fromAccountNumber);

        if (fromAccount != null) {
            System.out.print("Enter recipient's account number: ");
            String toAccountNumber = scanner.next();
            BankAccount toAccount = bank.getAccount(toAccountNumber);

            if (toAccount != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                fromAccount.transfer(toAccount, amount);
            } else {
                System.out.println("Recipient account not found!");
            }
        } else {
            System.out.println("Your account not found!");
        }
    }

    // View details of a specific account
    private static void viewAccountDetails(Scanner scanner, Bank bank) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = bank.getAccount(accountNumber);

        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolder());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }
}
