package Budget_App;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BudgetTest {
    

    public Budget budget;
    public ArrayList<Expense> testExpenses;

    @BeforeEach
    public void setUpBudget(){
        testExpenses = new ArrayList<Expense>(Arrays.asList(
            new Expense(new CheckingAccount("0000111122223333","null"), 10, null, null, "test1"),
            new Expense(new CheckingAccount("1111222233334444", "null"), 10, null, null, "test2")
        ));
        budget = new Budget(testExpenses);
    }

    @Test
    public void testConstructor() {
        
        assertEquals(testExpenses.get(0).getCost(), budget.getExpenses().get(0).getCost());
        assertEquals(testExpenses.get(1), budget.getExpenses().get(1));
    }

    @Test
    void testBudgetTotalExpenseMetric() {
        
        assertEquals(20, budget.getMonthlyExpenseTotal());

    }

    @Test
    void testBudgetExpenseAdd() {

        budget.addExpense(new Expense(new CheckingAccount("1234123412341234","null"),10,null,null,"test3"));
        assertEquals(30, budget.getMonthlyExpenseTotal());

    }
}
