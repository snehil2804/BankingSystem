package com.bankingsystem;

import java.util.Scanner;
import java.util.List;

public class BankingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static CustomerDAO customerDAO = new CustomerDAO();
    private static AccountDAO accountDAO = new AccountDAO();
    private static TransactionDAO transactionDAO = new TransactionDAO();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    createNewCustomer();
                    break;
                case 2:
                    viewCustomerDetails();
                    break;
                case 3:
                    updateCustomerInformation();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    createNewAccount();
                    break;
                case 6:
                    viewAccountDetails();
                    break;
                case 7:
                    updateAccountInformation();
                    break;
                case 8:
                    closeAccount();
                    break;
                case 9:
                    depositFunds();
                    break;
                case 10:
                    withdrawFunds();
                    break;
                case 11:
                    transferFunds();
                    break;
                case 12:
                    viewTransactionHistory();
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Register a new customer");
        System.out.println("2. View customer details");
        System.out.println("3. Update customer information");
        System.out.println("4. Delete a customer");
        System.out.println("5. Create a new account");
        System.out.println("6. View account details");
        System.out.println("7. Update account information");
        System.out.println("8. Close an account");
        System.out.println("9. Deposit funds");
        System.out.println("10. Withdraw funds");
        System.out.println("11. Transfer funds");
        System.out.println("12. View transaction history");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createNewCustomer() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(name, email, phoneNumber, address);
        customerDAO.addCustomer(customer);
        System.out.println("Customer added successfully. ID: " + customer.getCustomerId());
    }

    private static void viewCustomerDetails() {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        Customer customer = customerDAO.getCustomer(customerId);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void updateCustomerInformation() {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        Customer customer = customerDAO.getCustomer(customerId);
        if (customer != null) {
            System.out.print("Enter new name (leave blank to keep unchanged): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                customer.setName(name);
            }
            System.out.print("Enter new email (leave blank to keep unchanged): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                customer.setEmail(email);
            }
            System.out.print("Enter new phone number (leave blank to keep unchanged): ");
            String phoneNumber = scanner.nextLine();
            if (!phoneNumber.isEmpty()) {
                customer.setPhoneNumber(phoneNumber);
            }
            System.out.print("Enter new address (leave blank to keep unchanged): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                customer.setAddress(address);
            }
            customerDAO.updateCustomer(customer);
            System.out.println("Customer information updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void deleteCustomer() {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        customerDAO.deleteCustomer(customerId);
        System.out.println("Customer deleted successfully.");
    }

    private static void createNewAccount() {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter initial balance: ");
        double balance = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter account type (savings/checking): ");
        String type = scanner.nextLine();

        Account account = new Account(customerId, balance, type);
        accountDAO.addAccount(account);
        System.out.println("Account created successfully. Account Number: " + account.getAccountNumber());
    }

    private static void viewAccountDetails() {
        System.out.print("Enter account number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        Account account = accountDAO.getAccount(accountNumber);
        if (account != null) {
            System.out.println(account);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void updateAccountInformation() {
        System.out.print("Enter account number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        Account account = accountDAO.getAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter new balance (leave blank to keep unchanged): ");
            String balanceInput = scanner.nextLine();
            if (!balanceInput.isEmpty()) {
                account.setBalance(Double.parseDouble(balanceInput));
            }
            System.out.print("Enter new account type (leave blank to keep unchanged): ");
            String type = scanner.nextLine();
            if (!type.isEmpty()) {
                account.setType(type);
            }
            accountDAO.updateAccount(account);
            System.out.println("Account information updated successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void closeAccount() {
        System.out.print("Enter account number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        accountDAO.deleteAccount(accountNumber);
        System.out.println("Account closed successfully.");
    }

    private static void depositFunds() {
        System.out.print("Enter account number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Account account = accountDAO.getAccount(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            accountDAO.updateAccount(account);
            Transaction transaction = new Transaction(accountNumber, "deposit", amount);
            transactionDAO.addTransaction(transaction);
            System.out.println("Funds deposited successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawFunds() {
        System.out.print("Enter account number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Account account = accountDAO.getAccount(accountNumber);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountDAO.updateAccount(account);
                Transaction transaction = new Transaction(accountNumber, "withdrawal", amount);
                transactionDAO.addTransaction(transaction);
                System.out.println("Funds withdrawn successfully.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transferFunds() {
        System.out.print("Enter source account number: ");
        int sourceAccountNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter destination account number: ");
        int destinationAccountNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter amount to transfer: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Account sourceAccount = accountDAO.getAccount(sourceAccountNumber);
        Account destinationAccount = accountDAO.getAccount(destinationAccountNumber);
        if (sourceAccount != null && destinationAccount != null) {
            if (sourceAccount.getBalance() >= amount) {
                sourceAccount.setBalance(sourceAccount.getBalance() - amount);
                destinationAccount.setBalance(destinationAccount.getBalance() + amount);
                accountDAO.updateAccount(sourceAccount);
                accountDAO.updateAccount(destinationAccount);

                Transaction sourceTransaction = new Transaction(sourceAccountNumber, "transfer out", amount);
                Transaction destinationTransaction = new Transaction(destinationAccountNumber, "transfer in", amount);
                transactionDAO.addTransaction(sourceTransaction);
                transactionDAO.addTransaction(destinationTransaction);

                System.out.println("Funds transferred successfully.");
            } else {
                System.out.println("Insufficient balance in source account.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private static void viewTransactionHistory() {
        System.out.print("Enter account number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        List<Transaction> transactions = transactionDAO.getTransactions(accountNumber);

        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this account.");
        } else {
            transactions.forEach(System.out::println);
        }
    }
}