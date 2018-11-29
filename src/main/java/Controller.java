import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;


public class Controller {
    @FXML
    protected TableView<Transaction> tableView;
    @FXML
    protected TableColumn dateColumn;
    @FXML
    protected TableColumn amountColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn descriptionColumn;

    @FXML
    private Button addButton;
    @FXML
    private Button showBalanceButton;
    @FXML
    protected Button doneButton;

    @FXML
    private TextField dateTextFiled;
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
    private boolean isEdit = false;

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
        doneButton.setDisable(true);
    }

    @FXML
    public void addButton(){
        double amount = 0;
        if(!amountTextFiled.getText().isEmpty()) {
            String type = "deposit";
            String description = descriptionTextArea.getText();
            if(expenseRB.isSelected())
                type = "expense";
            amount = Double.parseDouble(amountTextFiled.getText());
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
        dateTextFiled.clear();
        amountTextFiled.clear();
        descriptionTextArea.clear();
    }

    @FXML
    public void editButton(){
        try {
            transaction = tableView.getSelectionModel().getSelectedItem();
            dateTextFiled.setText(""+transaction.getDate());
            amountTextFiled.setText(""+transaction.getAmount());
            if(transaction.getType().equalsIgnoreCase("expense"))
                groupType.selectToggle(expenseRB);
            else if(transaction.getType().equalsIgnoreCase("deposit"))
                groupType.selectToggle(depositRB);
            descriptionTextArea.setText(""+transaction.getDescription());
            doneButton.setDisable(false);
        }catch (Exception ignore){}
    }

    @FXML
    public void doneButton(){
        transaction.setAmount(Double.parseDouble(amountTextFiled.getText()));
        String type = "deposit";
        if (expenseRB.isSelected())
            type = "expense";
        transaction.setType(type);
        transaction.setDescription(descriptionTextArea.getText());
        transaction.setDate(LocalDate.parse(dateTextFiled.getText()));
        clearText();
        displayTable();
        doneButton.setDisable(true);
    }





}
