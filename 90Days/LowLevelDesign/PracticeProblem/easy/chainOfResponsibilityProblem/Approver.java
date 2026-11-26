package easy.chainOfResponsibilityProblem;

interface Approver {
    void setNext(Approver next);
    Approver getNext();
    String approve(int amount);
}

abstract class BaseApprover implements Approver {
    // TODO: Store next approver
    private Approver nextApprover;
    // TODO: Implement setNext() and getNext()
    @Override
    public void setNext(Approver next){
        this.nextApprover = next;
    }

    @Override
    public Approver getNext(){
        return nextApprover;
    }
    // TODO: Implement shared forwarding helper

    protected String forward(int amount){
        if(nextApprover != null){
            return nextApprover.approve(amount);
        }
        return "Payment Rejected";
    }
}

class JuniorApprover extends BaseApprover {
    // TODO
    @Override
    public String approve(int amount) {
        if(amount<=5000){
            return  "Junior Approver approved: "+ amount;
        }

        return forward(amount);
    }
}

class ManagerApprover extends BaseApprover {
    // TODO
    @Override
    public String approve(int amount) {
        if(amount>5000 && amount<=25000){
            return "Manager approved amount "+ amount;
        }
        return forward(amount);
    }
}

class DirectorApprover extends BaseApprover {
    // TODO

    @Override
    public String approve(int amount) {
        if(amount > 25000 && amount<=75000){
            return "Director approved " + amount;
        }
        return "Payment rejected";
    }
}

class PaymentApproval {

    private Approver head;

    public PaymentApproval() {
        Approver junior = new JuniorApprover();
        Approver manager = new ManagerApprover();
        Approver director = new DirectorApprover();

        junior.setNext(manager);
        manager.setNext(director);

        head = junior;
    }

    public String submitPayment(int amount) {
        return head.approve(amount);
    }
}

class Main {
    public static void main(String[] args) {

        PaymentApproval paymentApproval = new PaymentApproval();

        System.out.println(paymentApproval.submitPayment(3000));
        System.out.println(paymentApproval.submitPayment(15000));
        System.out.println(paymentApproval.submitPayment(50000));
        System.out.println(paymentApproval.submitPayment(90000));
    }
}
