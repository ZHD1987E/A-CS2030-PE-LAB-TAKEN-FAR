package test.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RequestTest {
    Request testRequest = new Request(new Seating(5), new Pair<Integer,Integer>(0, 1), 2, x -> true);
    @Test
    void testToString() {
        assertEquals("REQUEST:\nRequesting\n.....",testRequest.toString());
    }
}