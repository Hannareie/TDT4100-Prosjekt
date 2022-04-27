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
            new Expense("test1", 10, null, null),
            new Expense("test", 10, null, null)
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

        budget.addExpense(new Expense("test1", 10,null,null));
        assertEquals(30, budget.getMonthlyExpenseTotal());

    }
}
