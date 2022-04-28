package Budget_App;

public abstract class BankAccount {

    protected double balance;
    protected String AccNum;

    public abstract boolean Withdraw(double amount);

    public boolean Deposit(double amount) {
        balance += amount;
        return true;
    };

    public String getAccNum() {
        return this.AccNum;
    }

    public double getBalance() {
        return this.balance;
    }

    public abstract double simulateEndMonth(double expenses);

    public boolean Transfer(BankAccount ToReceiveTransfer, double amount) {
        try {
            if (this.Withdraw(amount)) {
                ToReceiveTransfer.Deposit(amount);
            }
            ;
            return true;
        } catch (Exception ex) {
            if (ex instanceof IllegalArgumentException) {
                System.out.println("You are trying to transfer more than you can withdraw.");
                return false;
            }
        }
        return false;
    };
}
