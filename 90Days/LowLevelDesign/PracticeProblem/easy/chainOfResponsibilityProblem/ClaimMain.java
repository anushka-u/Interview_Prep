package easy.chainOfResponsibilityProblem;

interface ClaimValidator {
    void setNext(ClaimValidator next);
    ClaimValidator getNext();
    String validate(ExpenseClaim claim);
}

abstract class BaseClaimValidator implements ClaimValidator {
    // TODO: Store next validator
    private  ClaimValidator next;

    @Override
    public void setNext(ClaimValidator next) {
        this.next = next;
    }
    // TODO: Implement setNext() and getNext()
    @Override
    public ClaimValidator getNext(){
        return  next;
    }
    // TODO: Implement shared forwarding helper
    protected String forward(ExpenseClaim claim){
        if(next != null){
            return  next.validate(claim);
        }
        return "APPROVED";
    }

}

class AmountValidator extends BaseClaimValidator {
    // TODO
    @Override
    public String validate(ExpenseClaim claim) {
        if(claim.getAmount()>0)
        {
            return forward(claim);
        }
        else{
            return "REJECTED";
        }
    }
}

class ReceiptValidator extends BaseClaimValidator {
    // TODO
    @Override
    public String validate(ExpenseClaim claim) {
        if(claim.getIsReceiptAttached()){
            return  forward(claim);
        }else{
            return  "REJECTED";
        }
    }
}

class ManagerValidator extends BaseClaimValidator {
    // TODO
    @Override
    public String validate(ExpenseClaim claim) {
        if(claim.getIsManagerApproved()){
            return   forward(claim);
        }
        else {
            return  "REJECTED";
        }
    }
}

class ExpenseClaim {
    int amount;
    boolean receiptAttached;
    boolean managerApproved;

    public ExpenseClaim(int amount, boolean receiptAttached, boolean managerApproved) {
        this.amount = amount;
        this.receiptAttached = receiptAttached;
        this.managerApproved = managerApproved;
    }

    public  int getAmount() {
        return amount;
    }
    public boolean getIsReceiptAttached() {
        return  receiptAttached;
    }

    public  boolean getIsManagerApproved() {
        return   managerApproved;
    }
}

class ClaimProcessor {
    // TODO: Configure chain
    // TODO: Process expense claim

    private  ClaimValidator next;
   public ClaimProcessor(){
       ClaimValidator amount = new AmountValidator();
       ClaimValidator receipt = new ReceiptValidator();
       ClaimValidator manager = new ManagerValidator();

       amount.setNext(receipt);
       receipt.setNext(manager);

       next = amount;

   }

   public String processClaim(ExpenseClaim claim){
       return next.validate(claim);
   }
}
public class ClaimMain {
    public static void main(String[] args) {

        ClaimProcessor processor = new ClaimProcessor();

        ExpenseClaim claim = new ExpenseClaim(
                15000,
                true,
                true
        );

        String result = processor.processClaim(claim);

        System.out.println(result);
    }
}