package StratergyDesignPattern;

public class Main {

    public static void main(String[] args) {
        PaymentStratergy paymentStratergy =  new UpiPayment();
        PaymentService paymentService = new PaymentService(paymentStratergy);
        paymentService.makePayment(1000.0);

        paymentService = new PaymentService(new CardPayment());
        paymentService.makePayment(2100.0);
    }
}
