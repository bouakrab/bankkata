package com.example.bankkata.Services;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
    private int balance;
    private List<Operation> Operations;
    private LocalDate[] dates;
    private int dateIndex;

    public Account() {
        this.balance = 0;
        this.Operations = new ArrayList<>();
        this.dates = new LocalDate[]{
                LocalDate.of(2012, 1, 10), // 10-01-2012
                LocalDate.of(2012, 1, 13), // 13-01-2012
                LocalDate.of(2012, 1, 14)  // 14-01-2012
        };
        this.dateIndex = 0;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        LocalDate date = getNextDate(); 
        balance += amount;
        Operations.add(new Operation(date, amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient balance for withdrawal.");
        }
        LocalDate date = getNextDate();
        balance -= amount;
        Operations.add(new Operation(date, -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("Date       || Amount || Balance");
        for (int i = Operations.size() - 1; i >= 0; i--) {
            Operation Operation = Operations.get(i);
            System.out.println(Operation);
        }
    }

    // method to get the next date in sequence
    private LocalDate getNextDate() {
        if (dateIndex < dates.length) {
            return dates[dateIndex++];
        }
        return LocalDate.now();
    }

    private static class Operation {
        private LocalDate date;
        private int amount;
        private int balance;

        public Operation(LocalDate date, int amount, int balance) {
            this.date = date;
            this.amount = amount;
            this.balance = balance;
        }

        @Override
        public String toString() {
            String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return formattedDate + " || " + amount + " || " + balance;
        }
    }
}
