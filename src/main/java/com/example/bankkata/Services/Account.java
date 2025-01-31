package com.example.bankkata.Services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
    private int balance;
    private List<Transaction> transactions;

    public Account() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(int amount) {
        balance += amount;
        transactions.add(new Transaction(LocalDate.now(), amount, balance));
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
    }

    @Override
    public void withdraw(int amount) {
        balance -= amount;
        transactions.add(new Transaction(LocalDate.now(), -amount, balance));
        System.out.println("Withdrawn: " + amount + ", New Balance: " + balance);
    }

    @Override
    public void printStatement() {
        System.out.println("Date           || Amount || Balance");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            System.out.println(transaction);
        }
    }

    private static class Transaction {
        private LocalDate date;
        private int amount;
        private int balance;

        public Transaction(LocalDate date, int amount, int balance) {
            this.date = date;
            this.amount = amount;
            this.balance = balance;
        }

        @Override
        public String toString() {
            // Format the date as dd-MM-yyyy
            String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return formattedDate + " || " + amount + " || " + balance;
        }
    }
}
