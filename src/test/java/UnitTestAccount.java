import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UnitTestAccount {
    private Account account;
    @BeforeEach
    public void init(){
        account = new Account("Test");
    }

    @Test
    public void addTransaction_ShouldAddTransaction_WhenReceiveTransaction(){
        account.addTransaction(new Transaction(LocalDate.now(), 200, "deposit"));
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    public void getDeposit_ShouldReturnDeposit_WhenCall(){
        account.addTransaction(new Transaction(LocalDate.now(), 200, "deposit"));
        assertEquals(200, account.getDeposit());
    }

    @Test
    public void getExpense_ShouldReturnExpense_WhenCall(){
        account.addTransaction(new Transaction(LocalDate.now(), 200, "expense"));
        assertEquals(200, account.getExpense());
    }

    @Test
    public void getBalance_ShouldReturnBalance_WhenCall(){
        account.addTransaction(new Transaction(LocalDate.now(), 200, "expense"));
        account.addTransaction(new Transaction(LocalDate.now(), 400, "deposit"));
        assertEquals(200, account.getBalance());
    }

    @Test
    public void getBalance_ShouldReturnNegativeBalance_WhenCall(){
        account.addTransaction(new Transaction(LocalDate.now(), 400, "expense"));
        account.addTransaction(new Transaction(LocalDate.now(), 200, "deposit"));
        assertEquals(-200, account.getBalance());
    }
}