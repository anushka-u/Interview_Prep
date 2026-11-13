package AbstractFactoryPattern;

public class InLoanValidatorRegionFactory implements LoanFactory {

    @Override
    public LoanValidator loanValidator() {
        return new InLoanValidatorRegion();
    }

    @Override
    public PaymentProcessor paymentProcessor() {
        return new InPaymentProcessor();
    }
}
