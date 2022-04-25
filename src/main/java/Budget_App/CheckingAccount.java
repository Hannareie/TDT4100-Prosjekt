package Budget_App;

public class CheckingAccount extends BankAccount implements PersistenceActor {

    private String cardNumber;
    private String accountName;

    public CheckingAccount(String accountName, String cardNumber){
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

    public void setCardNumber(String cardNumber){
        validateCardNumber(cardNumber);
        this.cardNumber = cardNumber;
    }

    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public double getBalance(){
        return balance;
    }

    @Override
    public boolean Withdraw(double amount) {        
        if((balance - amount) < 0){
            throw new IllegalArgumentException("Cannot withdraw " + amount + ". Credit limit would be exceeded");
        }

        balance -= amount;

        return true;
    }
    
    public boolean validateCardNumber(String cardNumber){
        if(cardNumber.length() != 16){
            throw new IllegalArgumentException("Card number must be 16 numbers long.");
        }
        return true;
    }

    @Override
    public void LoadState() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void SaveState() {
        // TODO Auto-generated method stub
    }
}