package one;
import java.util.*;

public class Booking {
    private String bookingId;
    private String eventId;
    private List<Seat> seats;
    private String buyer;  
    private double amount;
    private String state;   

public Booking(String bookingId, String eventId, List<Seat> seats, String buyer) {
        this.bookingId = bookingId;
        this.eventId = eventId;
        this.seats = seats;
        this.buyer = buyer;
        this.state = "Held";
        this.amount = computeTotal();
    }

private double computeTotal() {
        double sum = 0;
        for (Seat s : seats) {
            sum += s.getPrice(); 
        }
        return sum;
    }

public void printReceipt() 
{
	System.out.println("\n==== Booking Receipt ====");
    System.out.println("Booking ID: " + bookingId);
    System.out.println("Event ID: " + eventId);
    System.out.println("Buyer: " + (buyer == null ? "Not Confirmed" : buyer));
    System.out.println("Seats: " + seats.size());
    for (Seat s : seats) {
        s.display();
        }
    System.out.println("Total Amount: " + amount);
    System.out.println("State: " + state);
    System.out.println("=========================");
    }
public String getBookingId() 
{ return bookingId; }
public String getEventId() 
{ return eventId; }
public List<Seat> getSeats() 
{ return seats; }
public String getBuyer()
{ return buyer; }
public double getAmount() 
{ return amount; }
public String getState() 
{ return state; }

public void setBuyer(String buyer) 
{ this.buyer = buyer; }
public void setState(String state) 
{ this.state = state; }
}
