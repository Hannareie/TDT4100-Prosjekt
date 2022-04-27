package Budget_App;

public class Expense {
    
    public enum SpendingCategory {
        Grocery,
        Entertainment,
        RestaurantsAndNightlife,
        Transport,
        Other,
        Saving,
        HomeAndUtilities
    }

    private double cost;
    private SpendingCategory spendingCategory;
    private String description;
    private String connectedAccount;
    private String expenseName;

    public Expense(String expenseName, double cost, SpendingCategory spendingCategory, String description){
        this.cost = cost;
        this.spendingCategory = spendingCategory;
        this.description = description;
        this.expenseName = expenseName;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public SpendingCategory getSpendingCategory() {
        return spendingCategory;
    }

    public void setSpendingCategory(SpendingCategory spendingCategory) {
        this.spendingCategory = spendingCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
