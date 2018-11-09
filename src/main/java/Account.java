import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Account {
    private String name;
    private ArrayList<Transaction> transactions;

    public Account(String name){
        this.name = name;
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t){
        transactions.add(t);
    }

    public double getBalance(){
        return getDeposit() - getExpense();
    }

    public double getDeposit(){
        double deposit = 0;
        for(Transaction t: transactions){
            if(t.getType().equals("deposit"))
                deposit += t.getAmount();
        }
        return deposit;
    }

    public double getExpense(){
        double expense = 0;
        for(Transaction t: transactions){
            if(t.getType().equals("expense"))
                expense += t.getAmount();
        }
        return expense;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getName() {
        return name;
    }

    public ObservableList<Transaction> getList(){
        return FXCollections.observableArrayList(transactions);
    }
}
