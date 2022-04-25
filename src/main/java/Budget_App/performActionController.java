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
        Boolean is_CheckingAccount = checkingAccountBoolean.isSelected();
        Boolean is_SavingAccount = savingsAccountBoolean.isSelected();
        double amount = Double.parseDouble(withdraw);

        if (is_CheckingAccount){
            this.accountController.checkingAccount.Withdraw(amount);
            handleExit();
        }
        else if (is_SavingAccount) {
            this.accountController.savingsAccount.Withdraw(amount);
            handleExit();
        } else {
            System.out.println("Choose an account");
        }
    }

    @FXML 
    public void handleDeposit() {
        String deposit = Amount.getText();
        Boolean is_CheckingAccount = checkingAccountBoolean.isSelected();
        Boolean is_SavingAccount = savingsAccountBoolean.isSelected();
        double amount = Double.parseDouble(deposit);

        if (is_CheckingAccount){
            this.accountController.checkingAccount.Deposit(amount);
            this.accountController.budget.SaveState();
            handleExit();
        }
        else if (is_SavingAccount) {
            this.accountController.savingsAccount.Deposit(amount);
            this.accountController.budget.SaveState();
            handleExit();
        } else {
            System.out.println("Choose an account");
        }
    }

    @FXML
    public void handleAddExpense() {

        String name = expenseName.getText() == null ? "Default" : expenseName.getText();
        double cost = Double.parseDouble(expenseCost.getText());
        String desc = expenseDescription.getText() == null ? "Default" : expenseDescription.getText();
        SpendingCategory category = SpendingCategory.valueOf(expenseCategory.getValue());
        Boolean is_CheckingAccount = checkingAccountBoolean.isSelected();
        Boolean is_SavingAccount = savingsAccountBoolean.isSelected();
        
        if (is_CheckingAccount) {
            Expense ex = new Expense("CheckingAccount", cost, category, desc, name);
            this.accountController.budget.addExpense(ex);
            this.accountController.budget.SaveState();
            handleExit();
        } else if (is_SavingAccount) {
            Expense ex = new Expense("SavingAccount", cost, category, desc, name);
            this.accountController.budget.addExpense(ex);
            this.accountController.budget.SaveState();
            handleExit();
        } else {
            System.out.println("Choose an account");
        }
        this.accountController.budget.SaveState();
        handleExit();
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
