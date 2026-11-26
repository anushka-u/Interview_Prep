package medium.StrategyDesignPattern;

// Implement the DiscountStrategy interface here.
interface DiscountStrategy{
    String name();
    double applyDiscount(double price);
}
// Implement the NoDiscount class here.
class NoDiscount implements DiscountStrategy{

    public NoDiscount(){
    }

    @Override
    public String name(){
        return "none";
    }

    @Override
    public double applyDiscount(double price){
        return price;
    }
}
// Implement the PercentageDiscount class here.
class PercentageDiscount implements DiscountStrategy{

    private double percent;

    public PercentageDiscount(double percent){
        this.percent = percent;
    }

    @Override
    public String name(){
        return "percentage";
    }

    @Override
    public double applyDiscount(double price){
        double discount = (price * percent) / 100;
        return price - discount;
    }
}
// Implement the FlatDiscount class here.
class FlatDiscount implements DiscountStrategy{

    private double amount;

    public FlatDiscount(double amount){
        this.amount = amount;
    }

    @Override
    public String name(){
        return "flat";
    }

    @Override
    public double applyDiscount(double price){
        return Math.max(0, price - amount);
    }
}
// ShoppingCart is pre-implemented. Do not change it.
class ShoppingCart {
    private DiscountStrategy strategy = new NoDiscount();
    private int checkouts = 0;

    public ShoppingCart() {
    }

    public boolean setNoDiscount() {
        strategy = new NoDiscount();
        return true;
    }

    public boolean setPercentageDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            return false;
        }
        strategy = new PercentageDiscount(percent);
        return true;
    }

    public boolean setFlatDiscount(double amount) {
        if (amount < 0) {
            return false;
        }
        strategy = new FlatDiscount(amount);
        return true;
    }

    public String currentStrategy() {
        return strategy.name();
    }

    public double checkout(double price) {
        if (price < 0) {
            return -1.0;
        }
        checkouts++;
        return strategy.applyDiscount(price);
    }

    public String receipt(double price) {
        if (price < 0) {
            return "INVALID";
        }
        return String.format(java.util.Locale.US, "Original: $%.2f | Final: $%.2f",
                price, checkout(price));
    }

    public int checkoutCount() {
        return checkouts;
    }
}
