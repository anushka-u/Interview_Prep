package AbstractFactoryPattern;

public class MxPaymentProcessor implements PaymentProcessor {

    @Override
    public void paymentProcessor() {
        IO.print("Mexico Loan Region Payment Processor");
    }
}
