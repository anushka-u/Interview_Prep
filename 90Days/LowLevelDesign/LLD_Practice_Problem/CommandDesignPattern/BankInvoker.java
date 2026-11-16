package CommandDesignPattern;

import ObserverPattern.BankAccount;

public class BankInvoker {

    private BankCommand bankCommand;

    public void executeCommand(BankCommand bankCommand){
       bankCommand.execute();
    }
}
