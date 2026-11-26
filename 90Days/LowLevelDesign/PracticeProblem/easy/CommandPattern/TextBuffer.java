package easy.CommandPattern;

// Implement interface EditCommand with execute() and undo()

import java.util.ArrayList;
import java.util.List;

// Provided receiver. Do not modify.
class TextBuffer {
    private String text = "";

    public String getText() {
        return text;
    }

    public void append(String piece) {
        text += piece;
    }

    public String truncate(int count) {
        int n = Math.min(count, text.length());
        String removed = text.substring(text.length() - n);
        text = text.substring(0, text.length() - n);
        return removed;
    }

    public void setText(String value) {
        text = value;
    }
}
interface EditCommand{
    void execute();
    void undo();

}
// Implement class InsertCommand(buffer, text): execute appends the text,
// undo truncates exactly that many characters
class InsertCommand implements EditCommand{
    private TextBuffer textBuffer;
    private String text;

    public InsertCommand(TextBuffer textBuffer, String text){
        this.textBuffer = textBuffer;
        this.text = text;
    }

    @Override
    public void execute(){
        textBuffer.append(text);
    }

    @Override
    public void undo(){
        textBuffer.truncate(text.length());
    }
}


// Implement class DeleteLastCommand(buffer, count): execute truncates up to count characters
// and remembers what came off, undo appends that same text again
class DeleteLastCommand implements EditCommand{
    private TextBuffer textBuffer;
    private int count;

    private String text;

    public DeleteLastCommand(TextBuffer textBuffer, int count){
        this.textBuffer = textBuffer;
        this.count = count;
    }

    @Override
    public void execute(){
        text = textBuffer.truncate(count);
    }

    @Override
    public void undo(){
        textBuffer.append(text);
    }


}

// Implement class ClearCommand(buffer): execute remembers the whole text and empties the buffer,
// undo puts the remembered text back
class ClearCommand implements EditCommand{

    private TextBuffer textBuffer;
    private String text;

    public ClearCommand(TextBuffer textBuffer){
        this.textBuffer = textBuffer;
    }

    @Override
    public void execute(){
        text = textBuffer.getText();
        textBuffer.setText("");
    }

    @Override
    public void undo(){
        textBuffer.setText(text);
    }
}
// Provided invoker. Do not modify.
class EditorSession {
    private final TextBuffer buffer = new TextBuffer();
    private final List<EditCommand> history = new ArrayList<>();
    private final List<EditCommand> redoStack = new ArrayList<>();

    public EditorSession() {
    }

    private boolean apply(EditCommand command) {
        command.execute();
        history.add(command);
        redoStack.clear();
        return true;
    }

    public boolean insert(String text) {
        if (text.isEmpty()) {
            return false;
        }
        return apply(new InsertCommand(buffer, text));
    }

    public boolean deleteLast(int count) {
        if (count <= 0 || buffer.getText().isEmpty()) {
            return false;
        }
        return apply(new DeleteLastCommand(buffer, count));
    }

    public boolean clear() {
        if (buffer.getText().isEmpty()) {
            return false;
        }
        return apply(new ClearCommand(buffer));
    }

    public boolean undo() {
        if (history.isEmpty()) {
            return false;
        }
        EditCommand command = history.remove(history.size() - 1);
        command.undo();
        redoStack.add(command);
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) {
            return false;
        }
        EditCommand command = redoStack.remove(redoStack.size() - 1);
        command.execute();
        history.add(command);
        return true;
    }

    public String text() {
        return buffer.getText();
    }

    public int length() {
        return buffer.getText().length();
    }

    public int historySize() {
        return history.size();
    }
}

/**
 * Your EditorSession object will be instantiated and called as such:
 * EditorSession obj = new EditorSession();
 * boolean param_1 = obj.insert(text);
 * boolean param_2 = obj.deleteLast(count);
 * boolean param_3 = obj.clear();
 * boolean param_4 = obj.undo();
 * boolean param_5 = obj.redo();
 * String param_6 = obj.text();
 * int param_7 = obj.length();
 * int param_8 = obj.historySize();
 */