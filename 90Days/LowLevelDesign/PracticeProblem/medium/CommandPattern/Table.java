package medium.CommandPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Implement interface TableCommand with execute() and undo()
interface TableCommand{
    void execute();
    void undo();
}
// Provided receiver. Do not modify.
class Table {
    private final Map<String, String> rows = new HashMap<>();

    public boolean has(String key) {
        return rows.containsKey(key);
    }

    public String get(String key) {
        return rows.getOrDefault(key, "");
    }

    public void put(String key, String value) {
        rows.put(key, value);
    }

    public void remove(String key) {
        rows.remove(key);
    }

    public int size() {
        return rows.size();
    }
}

// Implement class InsertCommand(table, key, value): execute puts the row, undo removes it
class InsertCommand implements TableCommand{
    private Table table;
    private String key;
    private String value;

    public InsertCommand(Table table, String key, String value){
        this.table = table;
        this.key = key;
        this.value = value;
    }
    @Override
    public void execute(){
        table.put(key,value);
    }

    @Override
    public void undo(){
        table.remove(key);
    }
}
// Implement class UpdateCommand(table, key, value): execute remembers the current value before
// overwriting it, undo puts the remembered value back

class UpdateCommand implements TableCommand{
    private Table table;
    private String key;
    private String value;

    private String currentValue;

    public UpdateCommand(Table table, String key, String value){
        this.table = table;
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute(){
        currentValue = table.get(key);
        table.put(key, value);
    }

    @Override
    public void undo(){
        table.put(key, currentValue);
    }
}

// Implement class DeleteCommand(table, key): execute remembers the current value before removing
// the row, undo puts the row back with that value
class DeleteCommand implements TableCommand{
    private Table table;
    private String key;

    public DeleteCommand(Table table, String key){
        this.table = table;
        this.key =  key;
    }

    private String currentValue;

    @Override
    public void execute(){
        currentValue = table.get(key);
        table.remove(key);
    }

    @Override
    public void undo(){
        table.put(key,currentValue);
    }
}

// Provided invoker. Do not modify.
class TransactionManager {
    private final Table table = new Table();
    private final List<TableCommand> pending = new ArrayList<>();
    private boolean active = false;

    public TransactionManager() {
    }

    private boolean apply(TableCommand command) {
        command.execute();
        pending.add(command);
        return true;
    }

    public boolean begin() {
        if (active) {
            return false;
        }
        active = true;
        return true;
    }

    public boolean insertRow(String key, String value) {
        if (!active || key.isEmpty() || table.has(key)) {
            return false;
        }
        return apply(new InsertCommand(table, key, value));
    }

    public boolean updateRow(String key, String value) {
        if (!active || !table.has(key)) {
            return false;
        }
        return apply(new UpdateCommand(table, key, value));
    }

    public boolean deleteRow(String key) {
        if (!active || !table.has(key)) {
            return false;
        }
        return apply(new DeleteCommand(table, key));
    }

    public int commit() {
        if (!active) {
            return -1;
        }
        int count = pending.size();
        pending.clear();
        active = false;
        return count;
    }

    public int rollback() {
        if (!active) {
            return -1;
        }
        int count = pending.size();
        for (int i = pending.size() - 1; i >= 0; i--) {
            pending.get(i).undo();
        }
        pending.clear();
        active = false;
        return count;
    }

    public String read(String key) {
        return table.has(key) ? table.get(key) : "MISSING";
    }

    public int rowCount() {
        return table.size();
    }

    public int pendingCount() {
        return pending.size();
    }
}

