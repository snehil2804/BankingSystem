package com.bankingsystem;

public class Account {
    private int accountNumber;
    private int customerId;
    private double balance;
    private String type;

    // Constructor
    public Account(int accountNumber, int customerId, double balance, String type) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
        this.type = type;
    }

    public Account(int customerId, double balance, String type) {
        this.customerId = customerId;
        this.balance = balance;
        this.type = type;
    }

    // Getters and Setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerId=" + customerId +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                '}';
    }
}