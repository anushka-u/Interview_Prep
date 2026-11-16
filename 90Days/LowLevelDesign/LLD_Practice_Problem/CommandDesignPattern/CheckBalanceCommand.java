package CommandDesignPattern;

import ObserverPattern.BankAccount;

public class CheckBalanceCommand implements BankCommand{

    private BankAccountReciever bankAccountReciever;
    private double amount;

    public CheckBalanceCommand(BankAccountReciever bankAccountReciever){
        this.bankAccountReciever = bankAccountReciever;
    }

    @Override
    public void execute(){
        bankAccountReciever.checkBalance();
    }
}
