public class FlightSeatItem {
    private final int flightID;
    private final int seatID;
    private final String airline;
    private final String deptAirport;
    private final String arrAirport;
    private final String deptTime;
    private final String seatNo;
    private final String seatClass;
    private String status;

    public FlightSeatItem(int flightID, int seatID, String airline, String deptAirport, String arrAirport, String deptTime, String seatNo, String seatClass, String status) {
        this.flightID = flightID;
        this.seatID = seatID;
        this.airline = airline;
        this.deptAirport = deptAirport;
        this.arrAirport = arrAirport;
        this.deptTime = deptTime;
        this.seatNo = seatNo;
        this.seatClass = seatClass;
        this.status = status;
    }

}
