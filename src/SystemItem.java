public class SystemItem {
    private final int totalFlights;
    private final int totalUsers;
    private final int bookingsToday;

    public SystemItem(int totalFlights, int totalUsers, int bookingsToday) {
        this.totalFlights = totalFlights;
        this.totalUsers = totalUsers;
        this.bookingsToday = bookingsToday;
    }

    public int getTotalFlights() {
        return totalFlights;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public int getBookingsToday() {
        return bookingsToday;
    }

    @Override
    public String toString() {
        return "SystemItem{" +
                "totalFlights=" + totalFlights +
                ", totalUsers=" + totalUsers +
                ", bookingsToday=" + bookingsToday +
                '}';
    }
}
