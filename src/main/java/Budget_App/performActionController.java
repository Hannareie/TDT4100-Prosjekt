package Budget_App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

import Budget_App.Expense.SpendingCategory;
import javafx.scene.Scene;

public class performActionController {

        public AccountController accountController;

        public void setAccountController(AccountController accountController) {
            this.accountController = accountController;
        }

        //Common
        @FXML
        private Button exitButton;
        @FXML
        private CheckBox savingsAccountBoolean;
        @FXML
        private CheckBox checkingAccountBoolean;
        @FXML
        private CheckBox expSavingsBool;
        @FXML
        private TextField Amount;

        //fields for handelig requests
        @FXML
        private Button handleWithdrawButton;
        @FXML
        private Button handleDepositButton;
        @FXML 
        private Button handleTransferButton;

        //fields for adding an expense
        @FXML
        private Button AddExpenseButton;
        @FXML
        private TextField expenseName;
        @FXML
        private TextField expenseCost;
        @FXML
        private TextArea expenseDescription;
        @FXML
        private ChoiceBox<String> expenseCategory;

    @FXML
    public void handleTransfer() {

    }

    @FXML
    public void handleWithdraw() {
        String withdraw = Amount.getText();
        Boolean is_CheckingAccount = savingsAccountBoolean.selectedProperty().getValue();
        double amount = Double.parseDouble(withdraw);

        if (is_CheckingAccount){
            this.accountController.checkingAccount.Withdraw(amount);
        }
        else {
            this.accountController.savingsAccount.Withdraw(amount);
        }
    }

    @FXML 
    public void handleDeposit() {
        String deposit = Amount.getText();
        Boolean is_CheckingAccount = savingsAccountBoolean.selectedProperty().getValue();
        double amount = Double.parseDouble(deposit);

        if (is_CheckingAccount){
            this.accountController.checkingAccount.Deposit(amount);
        }
        else {
            this.accountController.savingsAccount.Deposit(amount);
        }
    }

    @FXML
    public void handleAddExpense() {
        String name = expenseName.getText() == null ? "Default" : expenseName.getText();
        double cost = Double.parseDouble(expenseCost.getText());
        String desc = expenseDescription.getText() == null ? "Default" : expenseDescription.getText();
        SpendingCategory category = null;//SpendingCategory.valueOf(expenseCategory.getValue());
        Boolean is_CheckingAccount = savingsAccountBoolean.isSelected();

        Expense ex = new Expense(null, cost, category, desc, desc);

        if (is_CheckingAccount) {
            ex = new Expense(accountController.getCheckingAccount(), cost, category, desc, name);
        } else {
            ex = new Expense(accountController.getSavingsAccount(), cost, category, desc, name);
        }
        this.accountController.budget.addExpense(ex);
        this.accountController.budget.SaveState();
    }

    @FXML
    public void handleExit() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BankAccount.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            Stage stage = (Stage) exitButton.getScene().getWindow();
            final Scene scene = new Scene(parent);

            AccountController controller = (Budget_App.AccountController) fxmlLoader.getController();
            controller = this.accountController;
            controller.initialize();
            stage.setTitle("Bankaccount");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
