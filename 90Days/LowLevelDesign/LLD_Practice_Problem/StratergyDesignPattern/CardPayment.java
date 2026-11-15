package StratergyDesignPattern;

public class CardPayment implements PaymentStratergy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Card");
    }
}
