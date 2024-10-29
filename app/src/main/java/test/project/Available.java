package test.project;

public class Available implements Seat {
    public Available() {};
    public boolean isBooked() {
        return false;
    }
    public String toString() {
        return ".";
    }
}