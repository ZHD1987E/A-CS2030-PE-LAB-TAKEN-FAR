package test.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InitTest {
    Init test = new Init(new Seating(5).book(new Pair<Integer,Integer>(0, 1)));

    @Test
    void testToString() {
        assertEquals("INIT:\ninitializing\nB....", test.toString());
    }
}