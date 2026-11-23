package ChainOfResponsibility;

public class Main {

    static void main() {
        Level1Support level1 = new Level1Support();
        Level2Support level2 = new Level2Support();
        Level3Support level3 = new Level3Support();

// Configure chain
        level1.setNextHandler(level2);
        level2.setNextHandler(level3);

// Submit request through first handler
        level1.handlerRequest(ComplaintType.API_ERROR);
        level1.handlerRequest(ComplaintType.ACCOUNT_ISSUE);
        level1.handlerRequest(ComplaintType.CRITICAL_ISSUE);
        level1.handlerRequest(ComplaintType.PAYMENT_FAILURE);
    }
}
