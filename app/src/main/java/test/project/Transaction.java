package test.project;

import java.util.List;

public abstract class Transaction {
    private final Seating seatingPlan;
    private final List<String> log;

    protected Transaction(Seating sPlan, List<String> logMessage) {
        this.seatingPlan = sPlan;
        this.log = logMessage;
    }

    public Seating getSeating() {
        return seatingPlan;
    }

    public List<String> logMessage() {
        return log;
    }

    public abstract Transaction transact(Transaction t);

    public String getStatus() {
        return String.join("\n", log) + "\n" + seatingPlan.toString();
    }
}