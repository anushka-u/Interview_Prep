package StratergyDesignPattern;

public class NetBanking implements PaymentStratergy{

    @Override
    public void pay(double amount) {
        IO.print("Paid " + amount + " via NetBanking");
    }
}
