package test.project;
import java.util.List;
import java.util.stream.Stream;

public class Seating {
    private final List<Seat> seating;
    private final int capacity;

    public Seating(int capacity) {
        this.seating = Stream.<Seat>generate(() -> new Available())
            .limit(capacity)
            .toList();
        this.capacity = capacity;
    }

    public Seating(List<Seat> combined, int capacity2) {
        this.seating = combined;
        this.capacity = capacity2;
    }

    public String toString() {
        return String.join("", seating.stream().map(e -> e.toString()).toList());
    }

    public boolean isAvailable(Pair<Integer, Integer> booking) {
        int start = booking.getFirst();
        int number = booking.getSecond();
        if (start < 0 || start + number - 1 >= capacity || number <= 0) {
            return false;
        } else {
            return seating.subList(start, start + number)
                .stream()
                .allMatch(e -> e.isBooked() == false);
        }
    }

    public Seating book(Pair<Integer, Integer> booking) {
        int start = booking.getFirst();
        int number = booking.getSecond();
        if (isAvailable(booking)) {
            List<Seat> beginSeats = seating.subList(0, start);
            List<Seat> endSeats = seating.subList(start + number, capacity);
            List<Seat> intermediary = Stream.<Seat>generate(() -> new Booked())
                .limit(number).toList();
            List<Seat> combined = Stream
                .of(beginSeats,intermediary,endSeats)
                .flatMap(e -> e.stream())
                .toList();
            return new Seating(combined, capacity);
        } else {
            return this;
        }
    }
}