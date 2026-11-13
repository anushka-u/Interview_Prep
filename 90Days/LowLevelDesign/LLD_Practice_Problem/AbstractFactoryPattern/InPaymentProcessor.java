package AbstractFactoryPattern;

public class InPaymentProcessor implements PaymentProcessor {

    @Override
    public void paymentProcessor() {
        IO.print("India Loan Region Payment Processor");
    }
}
