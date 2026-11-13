package AbstractFactoryPattern;

public class MxLoanValidatorRegion implements LoanValidator {
    @Override
    public void loanValidator() {
        IO.print("Mexico Loan Region Validator");
    }
}
