package AbstractFactoryPattern;

public class Main {

    private final LoanValidator loanValidator;
    private final PaymentProcessor paymentProcessor;

    public Main(LoanFactory loanFactory) {

        this.loanValidator = loanFactory.loanValidator();
        this.paymentProcessor = loanFactory.paymentProcessor();
    }

    public void execute() {

        loanValidator.loanValidator();
        paymentProcessor.paymentProcessor();
    }

    public static void main(String[] args) {

        LoanFactory indiaFactory =
                new InLoanValidatorRegionFactory();

        Main indiaApp = new Main(indiaFactory);
        indiaApp.execute();


        LoanFactory mexicoFactory =
                new MxLoanAndPaymentFactory();

        Main mexicoApp = new Main(mexicoFactory);
        mexicoApp.execute();
    }
}