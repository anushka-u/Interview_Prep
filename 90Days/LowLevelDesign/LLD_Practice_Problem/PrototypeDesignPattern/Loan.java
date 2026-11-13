package PrototypeDesignPattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Loan implements Cloneable{

    private String loanType;
    private  double loanAmount;
    private   double interestRate;
    private int loanTenure;
    private String borrowerName;

    public Loan(String loanType, double loanAmount, double interestRate, int loanTenure, String borrowerName) {
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanTenure = loanTenure;
        this.borrowerName = borrowerName;
    }

    @Override
    public Loan clone() {
        try{
            Loan clonedLoan = (Loan) super.clone();
            return clonedLoan;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }

    public String displayLoanDetails() {
        return "Borrower Name: " + borrowerName + "has taken "+ "Loan Type: " + loanType + "\n" + " of rupees " + loanAmount + " at an interest rate of " + interestRate + "% for a tenure of " + loanTenure + " years.";

    }
}
