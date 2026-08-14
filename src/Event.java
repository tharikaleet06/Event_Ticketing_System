package one;
import java.util.*;

public class Event {
    private String eventId;
    private String name;
    private String date;
    private String venue;
    private List<Seat> seatMap;

    public Event(String eventId, String name, String date, String venue) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.seatMap = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        seatMap.add(seat);
    }

    public List<Seat> findSeats(String type) {
        List<Seat> result = new ArrayList<>();
        for (Seat s : seatMap) {
            if (s.getType().equalsIgnoreCase(type) && s.getStatus().equals("Available")) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Seat> findSeats(int count) {
        List<Seat> result = new ArrayList<>();
        for (Seat s : seatMap) {
            if (s.getStatus().equals("Available")) {
                result.add(s);
            }
            if (result.size() == count) break;
        }
        return result;
    }

    public void displayEventDetails() {
        System.out.println(eventId + " | " + name + " | " + date + " | " + venue);
    }

    public String getEventId() { return eventId; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getVenue() { return venue; }
    public List<Seat> getSeatMap() { return seatMap; }
}
