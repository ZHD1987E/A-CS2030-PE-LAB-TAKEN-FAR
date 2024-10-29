package test.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;

public class TransactionTest {

    @Test void BigBang() {
        Transaction r1 = new Request(new Seating(20),new Pair<Integer,Integer>(10, 10), 2030, x -> x % 2 == 0);
        Transaction r2 = new Request(new Seating(20), new Pair<Integer,Integer>(15,2), 2040, x -> false);
        Transaction r3 = new Request(new Seating(20), new Pair<Integer,Integer>(8,2), 1010, x -> false);
        Transaction r4 = new Request(new Seating(20), new Pair<Integer, Integer>(19,1), 3230, x -> x % 2 == 0);
        Transaction init = new Init(new Seating(20).book(new Pair<Integer,Integer>(1,5)));
        Transaction afterR1 = r1.transact(init);
        Transaction afterR2 = r2.transact(afterR1);
        Transaction afterR3 = r3.transact(afterR2);
        Transaction afterR4 = r4.transact(afterR3);
        assertEquals("REJECTED:\ninitializing\nbilled 2030; booked 10--19\nnot billed 1010\n.BBBBB....BBBBBBBBBB",afterR4.toString());
    }

    /*
     * A personal verification to make sure it works, with overlapping bookings.
     */
    @Test void BigBangToo() {
        Transaction init = new Init(new Seating(5));
        Transaction r1 = new Request(new Seating(5), new Pair<Integer, Integer>(0,1),1,x -> true);
        Transaction r2 = new Request(new Seating(5), new Pair<Integer, Integer>(1,1),2,x -> false);
        Transaction r3 = new Request(new Seating(5), new Pair<Integer, Integer>(2,1),3,x -> true);
        Transaction r4 = new Request(new Seating(5), new Pair<Integer, Integer>(0,5),4,x -> x == 4);
        Transaction r5 = new Request(new Seating(5), new Pair<Integer,Integer>(4, 1),5,x -> true);
        Transaction afterR1 = r1.transact(init);
        Transaction afterR2 = r2.transact(afterR1);
        Transaction afterR3 = r3.transact(afterR2);
        Transaction afterR4 = r4.transact(afterR3);
        Transaction afterR5 = r5.transact(afterR4);
        assertEquals("APPROVED:\ninitializing\nbilled 1; booked 0--0\nnot billed 2\nbilled 3; booked 2--2\nbilled 5; booked 4--4\nB.B.B",afterR5.toString());
    }

    @Test void WhyRejectOrder() {
        Transaction init = new Init(new Seating(5));
        Transaction r1 = new Request(new Seating(5), new Pair<Integer, Integer>(0,6),1,x -> false);
        Transaction r2 = new Request(new Seating(5), new Pair<Integer, Integer>(0,3),12,x -> false);
        Transaction r3 = new Request(new Seating(5), new Pair<Integer, Integer>(0,1000),12,x -> true);
        Transaction afterR1 = r1.transact(init);
        Transaction afterR2 = r2.transact(afterR1);
        Transaction afterR3 = r3.transact(afterR2);
        assertEquals("REJECTED:\ninitializing\nnot billed 12\n.....",afterR3.toString());
    }

    /*
     * This assumes that you cannot take in an Approve, Reject or even an Init transaction type as a request.
     */
    @Test void invalidTransactionTest() {
        Approve testApprove = new Approve(new Seating(5), List.<String>of("This is a test approval"));
        Reject testReject = new Reject(new Seating(5), List.<String>of("This is a test Rejection"));
        Init testInit = new Init(new Seating(5));
        assertEquals("INIT:\ninitializing\n.....",testInit.transact(testApprove).toString());
        assertEquals("INIT:\ninitializing\n.....",testInit.transact(testReject).toString());
        assertEquals("APPROVED:\nThis is a test approval\n.....",testApprove.transact(testReject).toString());
        assertEquals("APPROVED:\nThis is a test approval\n.....",testApprove.transact(testInit).toString());
        assertEquals("REJECTED:\nThis is a test Rejection\n.....",testReject.transact(testInit).toString());
        assertEquals("REJECTED:\nThis is a test Rejection\n.....",testReject.transact(testApprove).toString());
    }

    @Test void isInstanceOfTransaction() {
        Approve testApprove = new Approve(new Seating(5), List.<String>of("This is a test approval"));
        Reject testReject = new Reject(new Seating(5), List.<String>of("This is a test Rejection"));
        Init testInit = new Init(new Seating(5));
        Request testRequest = new Request(new Seating(5), new Pair<Integer,Integer>(0, 1), 1, e -> true);
        assertInstanceOf(Transaction.class, testRequest);
        assertInstanceOf(Transaction.class, testInit);
        assertInstanceOf(Transaction.class, testApprove);
        assertInstanceOf(Transaction.class, testReject);
    }

    @Test void noBookingMustHappen() {
        Request r1 = new Request(new Seating(5), new Pair<Integer,Integer>(0, 0), 1, e -> true);
        Request r2 = new Request(new Seating(5), new Pair<Integer,Integer>(0, 100000), 1, e -> true);
        Request r3 = new Request(new Seating(5), new Pair<Integer,Integer>(4, 0), 1, e -> true);
        Request r4 = new Request(new Seating(5), new Pair<Integer,Integer>(4, 100000), 1, e -> true);
        Init testInit = new Init(new Seating(5));
        Transaction afterR1 = r1.transact(testInit);
        Transaction afterR2 = r2.transact(afterR1);
        Transaction afterR3 = r3.transact(afterR2);
        Transaction afterR4 = r4.transact(afterR3);
        assertEquals("INIT:\ninitializing\n.....",afterR4.toString());
    }
}