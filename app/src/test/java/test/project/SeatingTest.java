package test.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SeatingTest {
    Seating seatingTest = new Seating(5);
    @Test
    void testBook() {
        Seating seatingTest1 = seatingTest.book(new Pair<Integer,Integer>(0, 1));
        Seating seatingTest2 = seatingTest.book(new Pair<Integer,Integer>(4, 1));
        Seating seatingTest2A = seatingTest1.book(new Pair<Integer,Integer>(2, 2));
        Seating seatingTest2B = seatingTest1.book(new Pair<Integer, Integer>(0,5));
        Seating seatingTest3 = seatingTest.book(new Pair<Integer,Integer>(2, 2));
        Seating seatingTest4 = seatingTest.book(new Pair<Integer,Integer>(0, 5));
        Seating seatingTest5 = seatingTest.book(new Pair<Integer,Integer>(-1, 1));
        Seating seatingTest6 = seatingTest.book(new Pair<Integer,Integer>(0, 1000));
        Seating seatingTest7 = seatingTest.book(new Pair<Integer,Integer>(3, 2));
        Seating seatingTest7A = seatingTest7.book(new Pair<Integer,Integer>(0, 5));
        assertEquals("B....",seatingTest1.toString());
        assertEquals("....B",seatingTest2.toString());
        assertEquals("..BB.", seatingTest3.toString());
        assertEquals("BBBBB", seatingTest4.toString());
        assertEquals(".....", seatingTest5.toString());
        assertEquals(".....", seatingTest6.toString());
        assertEquals("B.BB.",seatingTest2A.toString());
        assertEquals("B....",seatingTest2B.toString());
        assertEquals("...BB",seatingTest7.toString());
        assertEquals("...BB",seatingTest7A.toString());
    }

    @Test
    void testIsAvailable() {
        assertEquals(true, seatingTest.isAvailable(new Pair<Integer,Integer>(0, 1)));
        assertEquals(true, seatingTest.isAvailable(new Pair<Integer,Integer>(0, 5)));
        assertEquals(true, seatingTest.isAvailable(new Pair<Integer,Integer>(2, 2)));
        assertEquals(true, seatingTest.isAvailable(new Pair<Integer,Integer>(4, 1)));
        assertEquals(false, seatingTest.isAvailable(new Pair<Integer,Integer>(-1, 1)));
        assertEquals(false, seatingTest.isAvailable(new Pair<Integer,Integer>(0, 0)));
        assertEquals(false, seatingTest.isAvailable(new Pair<Integer,Integer>(0, 6)));
    }

    @Test
    void testToString() {
        assertEquals(".....",seatingTest.toString());
    }
}
