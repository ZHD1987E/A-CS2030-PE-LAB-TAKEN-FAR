package test.project;
import java.util.List;

public class Init extends Transaction {
    
    public Init(Seating sPlan) {
        super(sPlan, List.<String>of("initializing"));
    }

    public Transaction transact(Transaction t) {
        return this;
    }
    public String toString() {
        return "INIT:\n" + super.getStatus();
    }
}