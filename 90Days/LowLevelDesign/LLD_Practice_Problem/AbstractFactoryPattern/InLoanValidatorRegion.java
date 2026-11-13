package AbstractFactoryPattern;

public class InLoanValidatorRegion implements LoanValidator {
    @Override
    public void loanValidator() {
        IO.print("India Loan Region Validator");
    }
}

