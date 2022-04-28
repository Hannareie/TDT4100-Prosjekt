package Budget_App;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Budget_App.Expense.SpendingCategory;

public class SavingAccount extends BankAccount implements PersistenceActor {

    private String accountName;
    private double interestRate;
    private double withdrawalLimit;

    public SavingAccount() {
    }

    double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public SavingAccount(String accountName, double interestRate, double withdrawalLimit) {
        this.accountName = accountName;
        this.interestRate = interestRate;
        this.withdrawalLimit = withdrawalLimit;
        this.balance = 0;
    }

    public void LoadSavingAccount() {

    }

    public void saveSavingAccount() {

    }

    public String getAccountName() {
        return accountName;
    }

    public double simulateEndMonth(double expenses) {

        double monthlyInterestRate = Math.round(Math.pow(interestRate, 12.0));

        Withdraw(expenses);
        this.balance = balance * (monthlyInterestRate);
        return balance;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean Withdraw(double amount) {

        if ((balance - amount) < 0 || amount > withdrawalLimit) {
            throw new IllegalArgumentException("Cannot withdraw " + amount + ".");
        }

        balance -= amount;
        return true;
    }

    @Override
    public void LoadState() {
        try {
            ArrayList<SavingAccount> loaded = new ArrayList<SavingAccount>();
            Scanner in = new Scanner(new FileReader("src/main/resources/Budget_App/SavingAccount.txt"));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parts = line.split(",");
                SavingAccount item = new SavingAccount(parts[0], Double.valueOf(parts[1]), Double.valueOf(parts[2]));
                item.balance = Double.valueOf(parts[3]);
                loaded.add(item);
            }
            in.close();

            if (loaded.size() == 0) {
                return;
            }

            this.accountName = loaded.get(0).getAccNum();
            this.balance = loaded.get(0).getBalance();
            this.interestRate = loaded.get(0).getInterestRate();
            this.withdrawalLimit = loaded.get(0).getWithdrawalLimit();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SaveState() {

    }
}
