import Authentication.Auth;
import javax.swing.*;
import java.awt.*;
// Parent class for the Booking Confirmation Page
class BookingConfirmationPage extends JPanel {
    // Encapsulation: private fields
    private JPanel mainPanel;
    private JLabel flightLabel;
    private JLabel seatLabel;
    private JLabel costLabel;
    private JLabel departureLabel;
    private JLabel arrivalLabel;
    private JLabel dateLabel;
    private JLabel departureTimeLabel;
    private JLabel arrivalTimeLabel;
    private JLabel seatClassLabel;
    private JLabel airlineLabel;

    // Constructor
    public BookingConfirmationPage(Auth auth, FlightItem2 flightInfo, SeatItem seatInfo) {
        setSize(950, 800);
        setLayout(new BorderLayout());

        // extract date from datetime value
        String date = flightInfo.getDepartureTime();
        date = date.substring(0, 10);
        // extract departure time
        String departureTime = flightInfo.getDepartureTime();
        departureTime = departureTime.substring(11, 16);
        // extract arrival time
        String arrivalTime = flightInfo.getArrivalTime();
        arrivalTime = arrivalTime.substring(11, 16);


        // Logic to check seat class and assign price
        double[] prices = {8029.00, 3207.00, 1657.00};
        String seatClass = seatInfo.getSeatClass();
        double cost = 0.00;
        switch(seatClass){
            case "First":
                cost = prices[0];
            case "Business":
                cost = prices[1];
            case "Economy":
                cost = prices[2];
        }

        // Initialize GUI components
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(950, 600));
        // top contents in boxes
        JPanel topContents = getTopContents(flightInfo, seatInfo, cost);
        mainPanel.add(topContents);
        JPanel middleContents = new JPanel();
        middleContents.setPreferredSize(new Dimension(950, 200));
        middleContents.setBounds(0, 350, 950, 200);
        middleContents.setLayout(new FlowLayout());
        InfoMidPanel deptPanel = new InfoMidPanel(flightInfo.getDepartureAirport(), flightInfo.getDepartureTime());
        middleContents.add(deptPanel);
        JLabel arrowIconLabel = new JLabel();
        arrowIconLabel.setIcon(new ImageIcon(getClass().getResource("Images/arrowRightIcon.png")));
        middleContents.add(arrowIconLabel);
        InfoMidPanel arrPanel = new InfoMidPanel(flightInfo.getArrivalAirport(), flightInfo.getArrivalTime());
        middleContents.add(arrPanel);
        mainPanel.add(middleContents);

        departureLabel = new JLabel("Departure: " + flightInfo.getDepartureAirport());
        arrivalLabel = new JLabel("Arrival: " + flightInfo.getArrivalAirport());
        dateLabel = new JLabel("Date: " + date);
        departureTimeLabel = new JLabel("Departure Time: " + departureTime);
        arrivalTimeLabel = new JLabel("Arrival Time: " + arrivalTime);
        airlineLabel = new JLabel("Airline: " + flightInfo.getAirline());

        // Add components to the frame
        this.add(mainPanel, BorderLayout.CENTER);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.decode("#db0f00"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Dialog", Font.BOLD, 18));
        JButton proceedButton = new JButton("Book & Proceed");
        proceedButton.setBackground(Color.decode("#02cf17"));
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setFont(new Font("Dialog", Font.BOLD, 18));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(950, 200));
        panel.setBounds(0, 700, 950, 100);
        panel.setLayout(new FlowLayout());
        panel.add(cancelButton);
        panel.add(proceedButton);

        this.add(panel, BorderLayout.SOUTH);

        // Cancel button action listener
        cancelButton.addActionListener(e -> {
            this.removeAll();
            this.add(new FlightSearchPage(auth));
            this.revalidate();
            this.repaint();
        });
        // Proceed button action listener
        double finalCost = cost;
        proceedButton.addActionListener(e -> {
            DBConnection db = new DBConnection();
            // Create Booking
            db.createBooking(auth.getUserID(), flightInfo.getFlightNo(), seatInfo.getSeatID());
            // Get booking ID
            int bookingID = db.getBookingID(auth.getUserID(), flightInfo.getFlightNo(), seatInfo.getSeatID());

            // Show next page
            this.removeAll();
            this.add(new PaymentPageGUI(auth, bookingID, finalCost));
            this.revalidate();
            this.repaint();
        });
    }

    private static JPanel getTopContents(FlightItem2 flightInfo, SeatItem seatInfo, double cost) {
        JPanel topContents = new JPanel();
        topContents.setLayout(new FlowLayout());
        topContents.setPreferredSize(new Dimension(950, 200));
        topContents.setBounds(0, 150, 950, 200);
        InfoTopPanel pan1 = new InfoTopPanel("Flight ID", Integer.toString(flightInfo.getFlightNo()));
        topContents.add(pan1);
        InfoTopPanel pan2 = new InfoTopPanel("Seat No", seatInfo.getSeatNo());
        topContents.add(pan2);
        InfoTopPanel pan3 = new InfoTopPanel("Seat Class", seatInfo.getSeatClass());
        topContents.add(pan3);
        InfoTopPanel pan4 = new InfoTopPanel("Price", "$" + cost);
        topContents.add(pan4);
        return topContents;
    }


}
class InfoTopPanel extends JPanel {
    InfoTopPanel(String title, String info){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipady = 10;
        gbc.ipadx = 10;
        this.setPreferredSize(new Dimension(200, 180));
        this.setBackground(Color.decode("#dbd9d9"));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(titleLabel, gbc);
        JLabel infoLabel = new JLabel(info);
        infoLabel.setFont(new Font("Dialog", Font.BOLD, 35));
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(infoLabel, gbc);
    }
}
class InfoMidPanel extends JPanel{
    InfoMidPanel(String airport, String time){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipady = 10;
        gbc.ipadx = 10;
        this.setPreferredSize(new Dimension(200, 150));
        this.setBackground(Color.decode("#dbd9d9"));
        JLabel airportLabel = new JLabel(airport);
        airportLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(airportLabel,gbc);
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(timeLabel, gbc);
    }
}


