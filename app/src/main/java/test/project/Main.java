package test.project;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Transaction r1 = new Request(new Seating(20),new Pair<Integer,Integer>(10, 10), 2030, x -> x % 2 == 0);
        Transaction r2 = new Request(new Seating(20), new Pair<Integer,Integer>(15,2), 2040, x -> false);
        Transaction r3 = new Request(new Seating(20), new Pair<Integer,Integer>(8,2), 1010, x -> false);
        Transaction init = new Init(new Seating(20).book(new Pair<Integer,Integer>(1,5)));
        Transaction result = process(Stream.of(r1,r2,r3), init);
        System.out.println(result);
    }

    public static Transaction process(Stream<Transaction> streamOfTransactions, Transaction begin) {
        return streamOfTransactions.reduce(begin, (a, b) -> b.transact(a));
    }
}