package one;

public class VIPSeat extends Seat {
    public VIPSeat(String seatNo, String section) {
        super(seatNo, section, "VIP");
        this.price = 1000.0;
    }

    @Override
    public double getPrice() {
        return price * 1.05; 
    }

    @Override
    public void display() {
        System.out.printf("%s %s [%s] ₹%.2f (%s)%n",
                section, seatNo, type, getPrice(), status.toUpperCase());
    }
}
