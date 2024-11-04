package test.project;

import java.util.List;
import java.util.stream.Stream;

public class Request extends Transaction {
    private final Bank bankOperator;
    private final Integer billingNo;
    private final Pair<Integer, Integer> seatRange;

    public Request(Seating plan, 
        Pair<Integer,Integer> rowOfSeats, int billing, 
        Bank bank) {
        super(plan, List.<String>of("Requesting"));
        this.bankOperator = bank;
        this.billingNo = billing;
        this.seatRange = rowOfSeats;
    }
    
    @Override
    public Transaction transact(Transaction t) {
        Seating seatPlan = t.getSeating();
        if (!seatPlan.isAvailable(seatRange)) {
            return t;
        } else {
            List<String> logSoFar = t.logMessage();
            if (bankOperator.test(billingNo)) {
                int start = seatRange.getFirst();
                int end = seatRange.getSecond() + start - 1;
                String msg = String
                    .format("billed %s; booked %s--%s",
                billingNo,start,end);
                List<String> added = Stream
                    .concat(logSoFar.stream(), Stream.of(msg))
                    .toList();
                return new Approve(seatPlan.book(seatRange), added);
            } else {
                String msg = String.format("not billed %s", billingNo);
                List<String> added = Stream
                    .concat(logSoFar.stream(), Stream.of(msg))
                    .toList();
                return new Reject(seatPlan, added);
            }
        }
    }

    public String toString() {
        return "REQUEST:\n" + super.getStatus();
    }
}