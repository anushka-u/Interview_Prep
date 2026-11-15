package StratergyDesignPattern;

public class UpiPayment implements PaymentStratergy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI");
    }
}
