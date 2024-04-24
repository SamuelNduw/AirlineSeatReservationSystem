public class BHistoryItem {
    private final int flightID;
    private final String airline;
    private final String departureAirport;
    private final String arrivalAirport;
    private final String departureTime;
    private final String arrivalTime;
    private final String seatNo;
    private final double amount;
    private final String paymentMethod;
    private final String paymentDate;

    public BHistoryItem(int flightID, String airline, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime, String seatNo, double amount, String paymentMethod, String paymentDate) {
        this.flightID = flightID;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seatNo = seatNo;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "BHistoryItem{" +
                "flightID=" + flightID +
                ", airline='" + airline + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", seatNo='" + seatNo + '\'' +
                ", amount='" + amount + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }
}
