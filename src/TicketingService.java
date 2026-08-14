package one;
import java.util.*;

public class TicketingService {
    private List<Event> events;
    private Map<String, Booking> bookings;

    public TicketingService() {
        events = new ArrayList<>();
        bookings = new HashMap<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void listEvents() {
        System.out.println("\n==== Events ====");
        for (Event e : events) {
            e.displayEventDetails();
        }
    }

    public Event searchEvent(String eventId) {
        for (Event e : events) {
            if (e.getEventId().equalsIgnoreCase(eventId)) {
                return e;
            }
        }
        return null;
    }

    public void showSeats(String eventId) {
        Event event = searchEvent(eventId);
        if (event == null) {
            System.out.println("Event not found!");
            return;
        }
        int available = 0;
        for (Seat s : event.getSeatMap()) {
            if (s.getStatus().equals("Available")) {
                s.display();
                available++;
            }
        }
        System.out.println("Total available: " + available);
    }

    public void holdSeats(String eventId, int count, String type) {
        Event event = searchEvent(eventId);
        if (event == null) {
            System.out.println("Event not found!");
            return;
        }

        List<Seat> seats;
        if (type == null || type.isEmpty()) {
            seats = event.findSeats(count);
        } else {
            seats = event.findSeats(type);
            if (seats.size() > count) {
                seats = seats.subList(0, count);
            }
        }

        if (seats.isEmpty()) {
            System.out.println("No seats available for your request.");
            return;
        }

        for (Seat s : seats) {
            s.setStatus("Held");
        }

        String bookingId = "B" + (bookings.size() + 1);
        Booking b = new Booking(bookingId, eventId, seats, null);
        b.setState("Held");
        bookings.put(bookingId, b);

        System.out.println("Holding seats:");
        for (Seat s : seats) {
            s.display();
        }
        System.out.println("Held booking ID: " + bookingId + " | Amount " + b.getAmount());
    }

    public void confirmBooking(String bookingId, String buyer, String phone) {
        Booking b = bookings.get(bookingId);
        if (b == null) {
            System.out.println("Booking not found!");
            return;
        }
        if (!b.getState().equals("Held")) {
            System.out.println("Booking is not in Held state.");
            return;
        }
        b.setBuyer(buyer + " | " + phone);
        b.setState("Confirmed");

        for (Seat s : b.getSeats()) {
            s.setStatus("Booked");
        }
        System.out.println("Booking confirmed for " + buyer + " | Amount: " + b.getAmount());
    }

    public void cancelBooking(String bookingId) {
        Booking b = bookings.get(bookingId);
        if (b == null) {
            System.out.println("Booking not found!");
            return;
        }
        if (b.getState().equals("Cancelled")) {
            System.out.println("Booking already cancelled.");
            return;
        }
        for (Seat s : b.getSeats()) {
            s.setStatus("Available");
        }
        b.setState("Cancelled");
        System.out.println("Booking " + bookingId + " cancelled. Refund: " + b.getAmount());
    }

    public void printReceipt(String bookingId) {
        Booking b = bookings.get(bookingId);
        if (b == null) {
            System.out.println("Booking not found!");
            return;
        }
        b.printReceipt();
    }

    public void eventSummary(String eventId) {
        Event e = searchEvent(eventId);
        if (e == null) {
            System.out.println("Event not found!");
            return;
        }
        int total = e.getSeatMap().size();
        int available = 0, held = 0, booked = 0;
        double revenue = 0;
        for (Seat s : e.getSeatMap()) {
            switch (s.getStatus()) {
                case "Available": available++; break;
                case "Held": held++; break;
                case "Booked": booked++; revenue += s.getPrice(); break;
            }
        }
        double occupancy = (booked * 100.0) / total;

        System.out.println("\n==== Event Summary ====");
        System.out.println(e.getEventId() + " | " + e.getName() + " | " + e.getDate() + " | " + e.getVenue());
        System.out.println("Seats: total=" + total + ", available=" + available + ", held=" + held + ", booked=" + booked);
        System.out.printf("Occupancy: %.2f%%\n", occupancy);
        System.out.println("Revenue : " + revenue);
        System.out.println("========================");
    }
}
