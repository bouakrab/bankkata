package com.example.bankkata;

import com.example.bankkata.Services.Account;
import com.example.bankkata.Services.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class AccountMockistTest {

    @Test
    public void testAccountBehavior() {
        Account account = new Account();

        // Create a mock object of the AccountService interface
        AccountService accountService = Mockito.mock(AccountService.class);

        // Step 3: Simulate the behavior of deposits, withdrawals, and statement printing
        doAnswer((invocation) -> {
            int amount = invocation.getArgument(0);
            account.deposit(amount); // Call the real deposit method
            return null;
        }).when(accountService).deposit(anyInt());

        doAnswer((invocation) -> {
            int amount = invocation.getArgument(0);
            account.withdraw(amount); // Call the real withdraw method
            return null;
        }).when(accountService).withdraw(anyInt());

        doAnswer((invocation) -> {
            account.printStatement(); // Call the real printStatement method
            return null;
        }).when(accountService).printStatement();

        // Perform deposits and withdrawals
        System.out.println("Simulating deposits and withdrawals...");
        accountService.deposit(1000); // Deposit 1000 on 10-01-2012
        accountService.deposit(2000); // Deposit 2000 on 13-01-2012
        accountService.withdraw(500); // Withdraw 500 on 14-01-2012

        // Print the statement
        System.out.println("Printing statement...");
        accountService.printStatement();

        // Verify the methods were called in the correct order
        System.out.println("Verifying method call order...");
        InOrder inOrder = Mockito.inOrder(accountService);
        inOrder.verify(accountService).deposit(1000);
        inOrder.verify(accountService).deposit(2000);
        inOrder.verify(accountService).withdraw(500);
        inOrder.verify(accountService).printStatement();

        // Verify the number of times each method was called
        System.out.println("Verifying method call counts...");
        verify(accountService, times(1)).deposit(1000);
        verify(accountService, times(1)).deposit(2000);
        verify(accountService, times(1)).withdraw(500);
        verify(accountService, times(1)).printStatement();

        System.out.println("Test completed successfully!");
    }
}
