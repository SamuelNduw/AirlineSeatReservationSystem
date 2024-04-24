public class SeatItem {
    private int seatID;
    private int flightID;
    private String seatNo;
    private String seatClass;
    private String status;

    public SeatItem(int seatID, int flightID, String seatNo, String seatClass, String status) {
        this.seatID = seatID;
        this.flightID = flightID;
        this.seatNo = seatNo;
        this.seatClass = seatClass;
        this.status = status;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SeatItem{" +
                "seatID=" + seatID +
                ", flightID=" + flightID +
                ", seatNo='" + seatNo + '\'' +
                ", seatClass='" + seatClass + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
