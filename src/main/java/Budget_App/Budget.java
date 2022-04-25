package Budget_App;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Budget implements PersistenceActor {

    private double monthlyExpenseTotal;
    private ArrayList<Expense> expenses;
    
    public Budget(){
        this.expenses = new ArrayList<Expense>();
        this.monthlyExpenseTotal = 0;
    }

    public Budget(ArrayList<Expense> expenses) { 
        this.expenses = expenses;
        this.monthlyExpenseTotal = 0;
        updateTotalMonthly();
    }

    public String endMonth(BankAccount account) {
        double balance = account.getBalance() - monthlyExpenseTotal;

        var expenseCalculationResult = "Your expenses this month left you with: " + balance + " after this months expenses. ";
        var trueBalanceAfterMonth = account.simulateEndMonth(monthlyExpenseTotal);
        var interestCalculationResult = "Your account balance after this month was, in total after interest and expenses: " + trueBalanceAfterMonth;
        
        return expenseCalculationResult + "\n" + interestCalculationResult;
    }

    public ArrayList<Expense> getExpenses(){
        return expenses;
    }

    public void addExpense(Expense expense){
        expenses.add(expense);
        updateTotalMonthly();
    }

    public void updateTotalMonthly(){
        double newTotalExpenses = 0;
        for(var e : expenses){
            newTotalExpenses += e.getCost();
        }
        monthlyExpenseTotal = newTotalExpenses;
    }

    public void setMonthlyTotalExpenses(double monthlyExpenseTotal) {
        this.monthlyExpenseTotal = monthlyExpenseTotal;
    }

    public double getMonthlyExpenseTotal(){
        return monthlyExpenseTotal;
    }

    @Override
    public void SaveState() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("src/main/resources/Budget_App/Budget.txt", "UTF-8");
			
			for(var item : expenses){
                writer.println(item.getExpenseName() + "," + String.valueOf(item.getCost()) + "," + item.getDescription() + "," + item.getConnectedAccount().getAccNum() + "," + item.getSpendingCategory());
            }
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
    @Override
	public void LoadState() {
		try {
            ArrayList<Expense> loaded = new ArrayList<Expense>();
			Scanner in = new Scanner(new FileReader("src/main/resources/Budget_App/Budget.txt"));
			while (in.hasNext()) {

				String line = in.next();
				String[] parts = line.split(",");
                //var spendingCatString = parts[4];
				Expense item = new Expense(null, Double.valueOf(parts[1]), null, parts[2], parts[0]);
				loaded.add(item);
			}
			in.close();
            this.expenses = loaded;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}