package ChainOfResponsibility;

import static ChainOfResponsibility.ComplaintType.API_ERROR;
import static ChainOfResponsibility.ComplaintType.TECHNICAL_ISSUE;

public class Level2Support implements SupportHandler {

    private SupportHandler nextHandler;

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handlerRequest(ComplaintType complaint) {
        if(complaint == TECHNICAL_ISSUE || complaint == API_ERROR){
            IO.println("Level 2 resolved "+ complaint);
        }else if(nextHandler != null){
            nextHandler.handlerRequest(complaint);
        }
        else {
            IO.println("Complaint cannot be resolved");
        }
    }
}
