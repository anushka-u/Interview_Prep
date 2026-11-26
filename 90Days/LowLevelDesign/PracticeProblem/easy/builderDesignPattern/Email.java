package easy.builderDesignPattern;

import java.util.ArrayList;
import java.util.List;

// https://algomaster.io/practice/low-level-design/implement-builder-email

class Email {
    private final String to;
    private final String subject;
    private final List<String> cc;
    private final List<String> bcc;
    private final String body;
    private final String priority;
    private final List<String> attachments;

    Email(EmailBuilder builder) {
        this.to = builder.to;
        this.subject = builder.subject;
        this.cc = new ArrayList<>(builder.cc);
        this.bcc = new ArrayList<>(builder.bcc);
        this.body = builder.body;
        this.priority = builder.priority;
        this.attachments = new ArrayList<>(builder.attachments);
    }

    String render() {
        return "Email{to='" + to
                + "', subject='" + subject
                + "', cc=[" + String.join(", ", cc)
                + "], bcc=[" + String.join(", ", bcc)
                + "], body='" + body
                + "', priority='" + priority
                + "', attachments=[" + String.join(", ", attachments) + "]}";
    }
}

// Implement the complete EmailBuilder class here.
class EmailBuilder{
    public String to;
    public String subject;
    public List<String> cc;
    public List<String> bcc;
    public List<String> attachments;
    public String body;
    public String priority;

    public EmailBuilder(String to, String subject){
        this.to = to;
        this.subject = subject;
        this.priority = "normal";
        this.cc = new ArrayList<>();
        this.bcc = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.body="";
    }

    public EmailBuilder addCc(String address){
        this.cc.add(address);
        return this;
    }

    public EmailBuilder addBcc(String address){
        this.bcc.add(address);
        return this;
    }

    public EmailBuilder addAttachment(String name){
        this.attachments.add(name);
        return this;
    }

    public EmailBuilder setBody(String text){
        this.body = text;
        return this;
    }

    public EmailBuilder setPriority(String level){
        this.priority = level;
        return this;
    }

    public Email build(){
        return new Email(this);
    }
}

class EmailComposer {
    private EmailBuilder draft = null;
    private final List<Email> built = new ArrayList<>();

    public EmailComposer() {
    }

    public boolean start(String to, String subject) {
        if (to.isEmpty() || subject.isEmpty()) {
            return false;
        }
        draft = new EmailBuilder(to, subject);
        return true;
    }

    public boolean cc(String address) {
        if (draft == null) {
            return false;
        }
        draft.addCc(address);
        return true;
    }

    public boolean bcc(String address) {
        if (draft == null) {
            return false;
        }
        draft.addBcc(address);
        return true;
    }

    public boolean body(String text) {
        if (draft == null) {
            return false;
        }
        draft.setBody(text);
        return true;
    }

    public boolean priority(String level) {
        if (draft == null) {
            return false;
        }
        if (!level.equals("low") && !level.equals("normal") && !level.equals("high")) {
            return false;
        }
        draft.setPriority(level);
        return true;
    }

    public boolean attachment(String name) {
        if (draft == null) {
            return false;
        }
        draft.addAttachment(name);
        return true;
    }

    public String build() {
        if (draft == null) {
            return "ERROR: no draft";
        }
        Email email = draft.build();
        built.add(email);
        return email.render();
    }

    public String rendered(int index) {
        if (index < 0 || index >= built.size()) {
            return "ERROR: no such email";
        }
        return built.get(index).render();
    }

    public int buildCount() {
        return built.size();
    }
}