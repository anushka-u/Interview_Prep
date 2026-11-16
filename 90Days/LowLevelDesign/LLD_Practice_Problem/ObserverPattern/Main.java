package ObserverPattern;

public class Main {

    public static void main(String[] args) {
        AccountStatus accountStatus1 = new SmsNotification();
        AccountStatus accountStatus2 = new EmailNotification();
        AccountStatus accountStatus3 = new WhatsappNotification();

        BankAccount bankAccount = new BankAccount(0);
        bankAccount.addObserver(accountStatus1);
        bankAccount.addObserver(accountStatus2);
        bankAccount.addObserver(accountStatus3);

        bankAccount.deposit(10000);
    }
}
