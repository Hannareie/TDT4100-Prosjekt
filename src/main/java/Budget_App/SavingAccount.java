package Budget_App;

public class SavingAccount extends BankAccount implements PersistenceActor {

    private String accountName;
    private double interestRate;
    private double withdrawalLimit;
    private double balance;

    public SavingAccount() {
    }

    public SavingAccount(String accountName, double interestRate, double withdrawalLimit){
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

    public double getBalance(){
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
        
        if((balance - amount) < 0 || amount > withdrawalLimit){
            throw new IllegalArgumentException("Cannot withdraw " + amount+".");
        }
        
        balance -= amount;
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
