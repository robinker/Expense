import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;


public class Controller {
    @FXML
    private TableView<Transaction> tableView;
    @FXML
    private TableColumn dateColumn;
    @FXML
    private TableColumn amountColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn descriptionColumn;

    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    @FXML
    private TextField amountTextFiled;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private RadioButton depositRB;
    @FXML
    private RadioButton expenseRB;

    @FXML
    private Label balanceLabel;

    private Account account = new Account("Test");
    private Transaction transaction;

    final ToggleGroup groupType = new ToggleGroup();

    public void displayTable(){
        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("description"));

        tableView.setItems(account.getList());
        tableView.getColumns().setAll(dateColumn, amountColumn, typeColumn, descriptionColumn);
    }

    @FXML
    void initialize(){
        Transaction t = new Transaction(LocalDate.now(), 200, "deposit", "sold food");
        account.addTransaction(t);
        depositRB.setToggleGroup(groupType);
        depositRB.setSelected(true);
        expenseRB.setToggleGroup(groupType);
        displayTable();
    }

    @FXML
    public void addButton(){
        double amount = 0;
        if(!amountTextFiled.getText().isEmpty()) {
            String type = "deposit";
            String description = descriptionTextArea.getText();
            if(expenseRB.isSelected())
                type = "expense";
            amount = Integer.parseInt(amountTextFiled.getText());
            transaction = new Transaction(LocalDate.now(), amount, type, description);
            account.addTransaction(transaction);
            displayTable();
            clearText();
        }

    }

    @FXML
    public void showBalance(){
        balanceLabel.setText("Your balance is " + account.getBalance());
    }

    private void clearText(){
        amountTextFiled.setText("");
        descriptionTextArea.setText("");
    }






}
