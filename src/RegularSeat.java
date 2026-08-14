package one;

public class RegularSeat extends Seat {
    public RegularSeat(String seatNo, String section) {
        super(seatNo, section, "Regular");
        this.price = 500.0;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void display() {
        System.out.printf("%s %s [%s] ₹%.2f (%s)%n",
                section, seatNo, type, price, status.toUpperCase());
    }
}
