package test.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

public class MainTest {
    Main test = new Main();

    @Test
    public void checkIfStreamFunctionWorks() {
        Transaction r1 = new Request(new Seating(20), new Pair<Integer,Integer>(10, 10), 2030, x -> x % 2 == 0);
        Transaction r2 = new Request(new Seating(20), new Pair<Integer,Integer>(15, 2), 2040, x -> x % 2 == 0);
        Transaction r3 = new Request(new Seating(20), new Pair<Integer,Integer>(8, 2), 1010, x -> false);
        Init init = new Init(new Seating(20).book(new Pair<Integer,Integer>(1,5))); 
        Stream<Transaction> testStream = Stream.<Transaction>of(r1, r2, r3);
        Transaction result = test.process(testStream, init);
        assertEquals("REJECTED:\ninitializing\nbilled 2030; booked 10--19\nnot billed 1010\n.BBBBB....BBBBBBBBBB", result.toString());

    }
}
