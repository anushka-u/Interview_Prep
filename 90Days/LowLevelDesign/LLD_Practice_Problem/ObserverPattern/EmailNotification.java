package ObserverPattern;

public class EmailNotification implements AccountStatus {
    @Override
    public void updateStatus(double balance) {
        IO.println("Email Notification: Your account balance is " + balance);
    }
}
