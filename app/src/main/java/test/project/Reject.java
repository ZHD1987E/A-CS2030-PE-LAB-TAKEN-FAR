package test.project;
import java.util.List;

public class Reject extends Transaction {
    
    protected Reject(Seating sPlan, List<String> log) {
        super(sPlan, log);
    }

    public String toString() {
        return "REJECTED:\n" + super.getStatus();
    }
}