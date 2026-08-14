package one;
import java.util.*;

public class TicketAppMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketingService service = new TicketingService();

        Event concert = new Event("E100", "Rock Night", "2025-09-19", "City Arena");
        for (int i = 1; i <= 10; i++) {
            concert.addSeat(new RegularSeat("A-" + i, "REG"));
        }
        for (int i = 11; i <= 15; i++) {
            concert.addSeat(new VIPSeat("A-" + i, "VIP"));
        }
        service.addEvent(concert);

        int choice;
        do {
            System.out.println("\n==== Event Ticketing ====");
            System.out.println("1. List Events");
            System.out.println("2. Search Events");
            System.out.println("3. Show Seats (Available)");
            System.out.println("4. Find & Hold Seats");
            System.out.println("5. Confirm Booking");
            System.out.println("6. Cancel/Refund Booking");
            System.out.println("7. Print Receipt");
            System.out.println("8. Event Summary");
            System.out.println("9. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    service.listEvents();
                    break;

                case 2:
                    System.out.print("Enter Event ID: ");
                    String eid = sc.nextLine();
                    Event e = service.searchEvent(eid);
                    if (e != null) e.displayEventDetails();
                    else System.out.println("Event not found!");
                    break;

                case 3:
                    System.out.print("Enter Event ID: ");
                    eid = sc.nextLine();
                    service.showSeats(eid);
                    break;

                case 4:
                    System.out.print("Enter Event ID: ");
                    eid = sc.nextLine();
                    System.out.print("Count: ");
                    int count = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Filter type Regular/VIP (or blank): ");
                    String type = sc.nextLine();
                    service.holdSeats(eid, count, type);
                    break;

                case 5:
                    System.out.print("Enter held Booking ID: ");
                    String bid = sc.nextLine();
                    System.out.print("Buyer name: ");
                    String buyer = sc.nextLine();
                    System.out.print("Buyer phone: ");
                    String phone = sc.nextLine();
                    service.confirmBooking(bid, buyer, phone);
                    break;

                case 6:
                    System.out.print("Enter Booking ID to cancel: ");
                    bid = sc.nextLine();
                    service.cancelBooking(bid);
                    break;

                case 7:
                    System.out.print("Enter Booking ID: ");
                    bid = sc.nextLine();
                    service.printReceipt(bid);
                    break;

                case 8:
                    System.out.print("Enter Event ID: ");
                    eid = sc.nextLine();
                    service.eventSummary(eid);
                    break;

                case 9:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 9);

        sc.close();
    }
}
