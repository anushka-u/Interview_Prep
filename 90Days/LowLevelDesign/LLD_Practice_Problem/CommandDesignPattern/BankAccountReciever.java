package CommandDesignPattern;

import ObserverPattern.BankAccount;

public class BankAccountReciever {

    private double balance;

    public BankAccountReciever(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance+=amount;
        IO.print("Deposited " + amount + " to the account. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if(balance>=amount) {
            balance-=amount;
            IO.print("Withdrew " + amount + " from the account. New balance: " + balance);
        } else {
            IO.print("Insufficient funds. Current balance: " + balance);
        }
    }

    public void checkBalance(){
        IO.print("Current balance: " + balance);
    }

}
