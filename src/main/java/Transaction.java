import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private double amount;
    private String type;
    private String description;

    public Transaction(LocalDate date, double amount, String type, String description) {
        if(amount > 0) {
            this.date = date;
            this.amount = amount;
            this.type = type;
            this.description = description;
        }
    }

    public Transaction(LocalDate date, double amount, String type){
        this(date, amount, type, " ");
   }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(double amount) {
        if(amount > 0)
            this.type = "deposit";
        else {
            this.type = "expense";
            amount = -amount;
        }
        this.amount = amount;
    }
}
