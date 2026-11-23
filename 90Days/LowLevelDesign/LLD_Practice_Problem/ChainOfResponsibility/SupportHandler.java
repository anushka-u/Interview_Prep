package ChainOfResponsibility;

public interface SupportHandler {
    void setNextHandler(SupportHandler nextHandler);
    void handlerRequest(ComplaintType complaint);
}
