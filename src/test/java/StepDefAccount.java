import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefAccount {
    private Account account;
    @Before
    public void init(){
        account = new Account("Test");
    }

    @Given("^a user with balance (\\d+)$")
    public void add_deposit(double amount){
        account.addTransaction(new Transaction(LocalDate.now(),amount,"deposit"));
    }

    @When("^I deposit (\\d++)$")
    public void deposit(double amount){
        account.addTransaction(new Transaction(LocalDate.now(),amount,"deposit"));

    }

    @When("^I expense (\\d++)$")
    public void expense(double amount){
        account.addTransaction(new Transaction(LocalDate.now(), amount,"expense"));

    }

    @Then("^my account have balance (\\d+)$")
    public void balancePositive(double amount){
        assertEquals(amount, account.getBalance());
    }

    @Then("^my account is in debt for (\\d+)$")
    public void balanceDebt(double amount){
        assertEquals(amount, - account.getBalance());
    }

}
