package test.project;
import java.util.List;

public class Approve extends Transaction {
    
    protected Approve(Seating sPlan, List<String> log) {
        super(sPlan, log);
    }

    public String toString() {
        return "APPROVED:\n" + super.getStatus();
    }
}