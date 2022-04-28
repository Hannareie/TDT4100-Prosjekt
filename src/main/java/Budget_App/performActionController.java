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

    // Common
    @FXML
    private Button exitButton;
    @FXML
    private CheckBox savingsAccountBoolean;
    @FXML
    private CheckBox checkingAccountBoolean;
    @FXML
    private CheckBox expSavingsBool;
    @FXML
    private TextField withdrawAmount;

    // fields for handelig requests
    @FXML
    private Button handleWithdrawButton;
    @FXML
    private Button handleDepositButton;
    @FXML
    private Button handleTransferButton;

    // fields for adding an expense
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

    // fields for handlig transfers
    @FXML
    private ChoiceBox<String> accountFrom;
    @FXML
    private ChoiceBox<String> accountTo;

    @FXML
    public void handleTransfer() {
        String from = accountFrom.getValue();
        String to = accountTo.getValue();
        double amount = Double.parseDouble(withdrawAmount.getText());

        if (from.equals("Sparekonto")) {
            if (to.equals("Brukskonto")) {
                this.accountController.getCheckingAccount().Transfer(accountController.getSavingsAccount(), amount);
            } else {
                System.out.println("Invalid input");
            }
        } else if (from.equals("Brukskonto")) {
            if (from.equals("Sparekonto")) {
                this.accountController.getCheckingAccount().Transfer(accountController.getSavingsAccount(), amount);
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Please choose accounts");
        }
    }

    @FXML
    public void handleWithdraw() {
        String withdraw = withdrawAmount.getText() == null ? "0" : withdrawAmount.getText();
        Boolean is_CheckingAccount = checkingAccountBoolean.isSelected();
        Boolean is_SavingAccount = savingsAccountBoolean.isSelected();
        double amount = Double.parseDouble(withdraw);

        if (is_CheckingAccount) {
            try {
                this.accountController.checkingAccount.Withdraw(amount);
                handleExit();

            } catch (Exception e) {
                System.out.println("Not a valid operation");
            }
        } else if (is_SavingAccount) {
            try {
                this.accountController.savingsAccount.Withdraw(amount);
                handleExit();

            } catch (Exception e) {
                System.out.println("Not a valid operation");
            }
        } else {
            System.out.println("Choose an account");
        }
    }

    @FXML
    public void handleDeposit() {
        String deposit = withdrawAmount.getText();
        Boolean is_CheckingAccount = checkingAccountBoolean.isSelected();
        Boolean is_SavingAccount = savingsAccountBoolean.isSelected();
        double amount = Double.parseDouble(deposit);

        if (is_CheckingAccount) {
            try {
                this.accountController.checkingAccount.Deposit(amount);
                this.accountController.budget.SaveState();
                handleExit();

            } catch (Exception e) {
                System.out.println("Not a valid operation");
            }
        } else if (is_SavingAccount) {
            try {
                this.accountController.savingsAccount.Deposit(amount);
                this.accountController.budget.SaveState();
                handleExit();

            } catch (Exception e) {
                System.out.println("Not a valid operation");
            }
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

        if (accountController.checkingAccount.getBalance() < cost) {
            throw new IllegalArgumentException();
        }

        Expense ex = new Expense(name, cost, category, desc);
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
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            AccountController controller = (Budget_App.AccountController) fxmlLoader.getController();
            controller.budget = this.accountController.budget;
            controller.savingsAccount = this.accountController.savingsAccount;
            controller.checkingAccount = this.accountController.checkingAccount;
            controller.checkingAccountView();

            stage.setTitle("Bankaccount");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
