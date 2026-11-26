package easy.CommandPracticeTwo;

import java.util.ArrayList;
import java.util.List;

class Document{
    private List<String> document  = new ArrayList<>();

    public void createText(String input){
        document.add(input);
    }

    public String deleteText(String input){
        if(document.contains(input)){
            document.remove(input);
            return  input;
        }
        return null;
    }

    public void printDocuments() {
        System.out.println(document);
    }
}

interface DocumentCommand{
    void execute();
}

class CreateEdit implements DocumentCommand{

    private String text;
    private  Document document;
    public CreateEdit(Document document, String text){
        this.document = document;
        this.text =  text;
    }

    @Override
    public void execute() {
        document.createText(text);
    }
}

class DeleteEdit implements DocumentCommand{
    private String text;
    private   Document document;
    private String deletedText;

    public DeleteEdit(Document document, String text){
        this.document = document;
        this.text =  text;
    }

    @Override
    public void execute() {
        deletedText = document.deleteText(text);
    }
}

class DocumentInvoker{
    private DocumentCommand document;

    public  DocumentInvoker(DocumentCommand document){
        this.document = document;
    }

    public void executeCommand() {
        document.execute();
    }
}
public class DocumentMain {

    static void main() {

        Document document = new Document();

        // Create operation
        DocumentCommand createCommand =
                new CreateEdit(document, "Hello World");

        DocumentInvoker invoker1 =
                new DocumentInvoker(createCommand);

        invoker1.executeCommand();
        document.printDocuments();

        // Delete operation
        DocumentCommand deleteCommand =
                new DeleteEdit(document, "Hello World");

        DocumentInvoker invoker2 =
                new DocumentInvoker(deleteCommand);

        invoker2.executeCommand();
        document.printDocuments();
    }
}
