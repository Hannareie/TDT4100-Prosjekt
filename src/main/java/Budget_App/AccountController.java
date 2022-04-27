package Budget_App;

import java.util.ArrayList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Scene;

public class AccountController {

    //Buttons
    @FXML
    private Button savingsAccountButton;
    @FXML
    private Button checkingAccountButton;
    @FXML
    private Button changeDepositButton;
    @FXML
    private Button changeTransferButton;    
    @FXML
    private Button changeWithdrawButton;
    @FXML 
    private Button newExpenseButton;
    @FXML 
    private Button cancelButton; 
    @FXML 
    private Text headerText;
    @FXML
    private VBox root;
  
    //Main objects
    public Budget budget;
    public BankAccount savingsAccount;  // initialized in fxml init function due to required constructor inputs
    public BankAccount checkingAccount; // initialized in fxml init function due to required constructor inputs

    public BankAccount getSavingsAccount() {
        return this.savingsAccount;
    }
    public BankAccount getCheckingAccount() {
        return this.checkingAccount;
    }

    @FXML
    public void initialize() throws IOException {
        budget = new Budget(new ArrayList<Expense>());
        savingsAccount= new SavingAccount("SavingAccount", 0, 1);
        checkingAccount= new CheckingAccount("CheckingAccount", 0, 1);

        checkingAccountView();
    }

    @FXML 
    public void savingsAccountView() {
        root.getChildren().clear();
        budget.LoadState();
        VBox accountDetails = new VBox(5);
        accountDetails.setId("AccountDetails");
        accountDetails.setPadding(new Insets(5, 10, 10, 10));
        Text money = new Text("Available money: " + savingsAccount.getBalance());
        accountDetails.getChildren().add(money);
        root.setPadding(new Insets(10, 20, 20, 20));
        root.getChildren().add(accountDetails);
    }

    @FXML 
    public void checkingAccountView() {
        root.getChildren().clear();
        budget.LoadState();
        VBox accountDetails = new VBox(5);
        accountDetails.setId("AccountDetails");
        accountDetails.setPadding(new Insets(5, 10, 10, 10));
        Text money = new Text("Available money: " + checkingAccount.getBalance());
        accountDetails.getChildren().add(money);
        root.setPadding(new Insets(10, 20, 20, 20));
        root.getChildren().add(accountDetails);

        if (budget.getExpenses() != null) {
            try {
                for (Expense ex : budget.getExpenses()) {
                    VBox textbox = new VBox(5);
                    textbox.setId("textbox");
                    textbox.setPadding(new Insets(5, 10, 10, 10));
                        
                    Text name = new Text("Name: " + ex.getExpenseName());
                    Text desc = new Text("Description: " + ex.getDescription());
                    Text cost = new Text("Cost: " + Double.toString(ex.getCost()));
                    Text category = new Text("Category: " + ex.getSpendingCategory().toString());                        
                    textbox.getChildren().addAll(name, desc, cost, category);
                    root.getChildren().add(textbox);
                }

            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }

    @FXML 
    public void changeNewExpenseView() {
        try {
            switchTo("NewExpense");
          } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML 
    public void changeDepositView() {
        try {
            switchTo("Deposit");
          } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML 
    public void changeTransferView() {
        try {
            switchTo("Transfer");
          } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML 
    public void changeWithdrawView() {
        try {
            switchTo("Withdraw");
          } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchTo(String fxmlName) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName + ".fxml"));
        Parent parents = (Parent) fxmlLoader.load();
        Stage stage = (Stage) savingsAccountButton.getScene().getWindow();
        Scene scene = new Scene(parents);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        var controller = fxmlLoader.getController();
        ((Budget_App.performActionController) controller).setAccountController(this);
        stage.setTitle("New " + fxmlName);
        stage.setScene(scene);
        stage.show();
    }
}

