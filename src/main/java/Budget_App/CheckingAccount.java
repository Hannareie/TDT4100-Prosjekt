package Budget_App;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckingAccount extends BankAccount implements PersistenceActor {

    private String cardNumber;
    private String accountName;

    public CheckingAccount(String accountName, String cardNumber) {
        validateCardNumber(cardNumber);
        this.balance = 0;
        this.accountName = accountName;
        this.cardNumber = cardNumber;
    }

    public CheckingAccount(String string, int i, int j) {
    }

    @Override
    public double simulateEndMonth(double expenses) {
        Withdraw(expenses);
        return expenses;
    }

    public void setCardNumber(String cardNumber) {
        validateCardNumber(cardNumber);
        this.cardNumber = cardNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean Withdraw(double amount) {
        if ((balance - amount) < 0) {
            throw new IllegalArgumentException("Cannot withdraw " + amount + ". Credit limit would be exceeded");
        }

        balance -= amount;

        return true;
    }

    public boolean validateCardNumber(String cardNumber) {
        if (cardNumber.length() != 16) {
            throw new IllegalArgumentException("Card number must be 16 numbers long.");
        }
        return true;
    }

    @Override
    public void LoadState() {
        try {
            ArrayList<CheckingAccount> loaded = new ArrayList<CheckingAccount>();
            Scanner in = new Scanner(new FileReader("src/main/resources/Budget_App/CheckingAccount.txt"));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parts = line.split(",");
                CheckingAccount item = new CheckingAccount(parts[0], parts[1]);
                item.balance = Double.valueOf(parts[2]);
                loaded.add(item);
            }
            in.close();

            if (loaded.size() == 0) {
                return;
            }

            this.accountName = loaded.get(0).getAccNum();
            this.balance = loaded.get(0).getBalance();
            this.cardNumber = loaded.get(0).getCardNumber();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void SaveState() {
        // TODO Auto-generated method stub
    }
}