package one;

public abstract class Seat {
    protected String seatNo;
    protected String section;
    protected String type;
    protected double price;
    protected String status; 

    public Seat(String seatNo, String section, String type) {
        this.seatNo = seatNo;
        this.section = section;
        this.type = type;
        this.status = "Available";
    }

    public abstract double getPrice(); 
    public abstract void display();    
  
    public String getSeatNo() { return seatNo; }
    public String getSection() { return section; }
    public String getType() { return type; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
