package easy.chainOfResponsibilityProblem;

import java.util.ArrayList;
import java.util.List;

public interface SupportHandler {
    void setNext(SupportHandler next);
    SupportHandler getNext();
    void handle(ISSUETYPE issue, List<String> result);
}

enum ISSUETYPE{
    PASSWORD,
    LOGIN,
    BUG,
    APP_CRASH,
    PAYMENT_FAILURE,
    SECURITY_BREACH
}

abstract class BaseSupportHandler implements SupportHandler {
    private SupportHandler nextHandler;
   // List<String> result = new ArrayList<>();

    @Override
    public void setNext(SupportHandler next) {
        this.nextHandler = next;
    }

    @Override
    public SupportHandler getNext() {
        return nextHandler;
    }

    protected void forward(ISSUETYPE issue, List<String> result) {
        if(nextHandler != null) {
            nextHandler.handle(issue, result);
        }
    }
}

class BaseSupport extends  BaseSupportHandler {

    @Override
    public void handle(ISSUETYPE issue, List<String> result) {
        if(issue.equals(ISSUETYPE.PASSWORD) || issue.equals(ISSUETYPE.LOGIN)) {
            result.add("BASIC SUPPORT resolved " + issue);
        }
        forward(issue, result);
    }
}

class TechnicalSupport extends  BaseSupportHandler {
    @Override
    public void handle(ISSUETYPE issue, List<String> result) {
        if(issue.equals(ISSUETYPE.BUG) || issue.equals(ISSUETYPE.APP_CRASH)) {
            result.add("Technical Support resolved " + issue);
        }
        forward(issue, result);
    }
}
class SeniorSupport extends  BaseSupportHandler {

    @Override
    public void handle(ISSUETYPE issue, List<String> result) {
        if(issue.equals(ISSUETYPE.PAYMENT_FAILURE) || issue.equals(ISSUETYPE.SECURITY_BREACH)) {
            result.add("Senior Support resolved " + issue);
        }
        forward(issue, result);
    }
}

class SupportRouter{
    private  SupportHandler supportHandler;
    public SupportRouter(){
        SupportHandler baseSupport = new BaseSupport();
        SupportHandler technicalSupport = new TechnicalSupport();
        SupportHandler seniorSupport = new SeniorSupport();
        baseSupport.setNext(technicalSupport);
        technicalSupport.setNext(seniorSupport);

        supportHandler = baseSupport;
    }
    public List<String> submitIssue(ISSUETYPE issue) {
        List<String> result = new ArrayList<>();
        supportHandler.handle(issue, result);
        return result;
    }
}

