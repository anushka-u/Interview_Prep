package AbstractFactoryPattern;

import PrototypeDesignPattern.Loan;

public class MxLoanAndPaymentFactory implements LoanFactory {

    @Override
    public LoanValidator loanValidator() {
        return new MxLoanValidatorRegion();
    }

    @Override
    public PaymentProcessor paymentProcessor() {
        return new MxPaymentProcessor();
    }
}
