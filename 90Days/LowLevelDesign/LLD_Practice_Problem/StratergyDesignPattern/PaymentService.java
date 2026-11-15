package StratergyDesignPattern;

public class PaymentService {

    private final PaymentStratergy paymentStratergy;

    public PaymentService(PaymentStratergy paymentStratergy) {
        this.paymentStratergy = paymentStratergy;
    }

    public void makePayment(double amount) {
        paymentStratergy.pay(amount);
    }
}
