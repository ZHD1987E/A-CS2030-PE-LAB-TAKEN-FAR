package test.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SeatTest {
    Available seatA = new Available();
    Booked seatB = new Booked();
    @Test
    void testAIsBooked() {
        assertEquals(false, seatA.isBooked());
    }

    @Test
    void testAToString() {
        assertEquals(".", seatA.toString());
    }

    @Test
    void testBIsBooked() {
        assertEquals(true, seatB.isBooked());
    }

    @Test
    void testBToString() {
        assertEquals("B", seatB.toString());
    }

    @Test
    void testSubClassProperty() {
        assertInstanceOf(Seat.class, seatA);
        assertInstanceOf(Seat.class, seatB);
    }
}