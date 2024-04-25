public class RecentBookingsItem {
    private final int flightID;
    private final String username;

    public RecentBookingsItem(int flightID, String username) {
        this.flightID = flightID;
        this.username = username;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "RecentBookingsItem{" +
                "flightID=" + flightID +
                ", username='" + username + '\'' +
                '}';
    }
}
