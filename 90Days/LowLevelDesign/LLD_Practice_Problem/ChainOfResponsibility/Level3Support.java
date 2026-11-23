package ChainOfResponsibility;

public class Level3Support implements SupportHandler{

    private SupportHandler nextHandler;
    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handlerRequest(ComplaintType complaint) {
        if(complaint ==  ComplaintType.PAYMENT_FAILURE || complaint ==  ComplaintType.CRITICAL_ISSUE){
            IO.println("Level 3 resolved "+ complaint);
        }
        else{
            IO.println("Complaint cannot be resolved");
        }
    }
}
