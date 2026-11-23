package ChainOfResponsibility;

import static ChainOfResponsibility.ComplaintType.ACCOUNT_ISSUE;
import static ChainOfResponsibility.ComplaintType.PASSWORD_RESET;

public class Level1Support implements SupportHandler {

    private SupportHandler nextHandler;

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handlerRequest(ComplaintType complaint) {
        if(complaint == PASSWORD_RESET || complaint == ACCOUNT_ISSUE){
            IO.println("Level 1 resolved "+ complaint);
        }
        else if(nextHandler != null){
            nextHandler.handlerRequest(complaint);
        }else {
            IO.println("Complaint cannot be resolved");
        }
    }
}
