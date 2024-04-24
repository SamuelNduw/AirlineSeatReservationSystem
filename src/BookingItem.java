public class BookingItem {
    private final int bookingID;
    private final int userID;
    private final int flightID;
    private final int seatID;
    private final String bookingDate;
    private String paymentStatus;

    public BookingItem(int bookingID, int userID, int flightID, int seatID, String bookingDate, String paymentStatus) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.flightID = flightID;
        this.seatID = seatID;
        this.bookingDate = bookingDate;
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "BookingItem{" +
                "bookingID=" + bookingID +
                ", userID=" + userID +
                ", flightID=" + flightID +
                ", seatID=" + seatID +
                ", bookingDate='" + bookingDate + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public int getFlightID() {
        return flightID;
    }

    public int getSeatID() {
        return seatID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
