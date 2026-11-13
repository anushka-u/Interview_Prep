package PrototypeDesignPattern;

public class MainClass {
    public static void main(String[] args){

        Loan loan = new Loan("Home Loan", 500000, 7.2, 20, "John Doe");
        IO.print(loan.displayLoanDetails());
        Loan clonedLoan = loan.clone();
        clonedLoan.setBorrowerName("Jane Smith");
        IO.println(clonedLoan.displayLoanDetails());
    }
}
