package PracticeProblem.medium.ChainOfResponsibility;


import java.util.List;

interface Logger {
    String label();
    void setNext(Logger logger);
    Logger getNext();
    void log(int level, String message, List<String> lines);
}

abstract class BaseLogger implements Logger {

    private Logger nextLogger;

    @Override
    public void setNext(Logger logger) {
        this.nextLogger = logger;
    }

    @Override
    public Logger getNext() {
        return nextLogger;
    }

    protected abstract int threshold();

    @Override
    public void log(int level, String message, List<String> lines) {
        if (level >= threshold()) {
            lines.add("[" + label() + "] " + message);
        }

        forward(level, message, lines);
    }

    protected void forward(int level, String message, List<String> lines) {
        if (nextLogger != null) {
            nextLogger.log(level, message, lines);
        }
    }
}

class DebugLogger extends BaseLogger {
    @Override
    public String label() {
        return "DEBUG";
    }

    @Override
    protected int threshold() {
        return 1;
    }
}

class InfoLogger extends BaseLogger {
    @Override
    public String label() {
        return "INFO";
    }

    @Override
    protected int threshold() {
        return 2;
    }
}

class WarnLogger extends BaseLogger {
    @Override
    public String label() {
        return "WARN";
    }

    @Override
    protected int threshold() {
        return 3;
    }
}

class ErrorLogger extends BaseLogger {
    @Override
    public String label() {
        return "ERROR";
    }

    @Override
    protected int threshold() {
        return 4;
    }
}
