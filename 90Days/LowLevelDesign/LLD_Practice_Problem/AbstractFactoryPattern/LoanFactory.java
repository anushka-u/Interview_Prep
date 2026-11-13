package AbstractFactoryPattern;

public interface LoanFactory {

    LoanValidator loanValidator();
    PaymentProcessor paymentProcessor();
}
