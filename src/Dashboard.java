import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import Authentication.Auth;

public class Dashboard extends JPanel {
    Dashboard(){
        this.setBounds(0, 0, 950, 800);
        this.setLayout(null);
        this.setBackground(Color.red);

        topInfo topPanel = new topInfo();
        topPanel.setBounds(0, 0, 633, 200);
        this.add(topPanel);

        midInfo midPanel = new midInfo();
        midPanel.setBounds(0, 200, 633, 600);
        this.add(midPanel);

        rightInfo rightPanel = new rightInfo();
        rightPanel.setBounds(633, 0, 318, 800);
        this.add(rightPanel);

        this.setVisible(true);
    }
}
class topInfo extends JPanel{
    topInfo(){
        this.setPreferredSize(new Dimension(633, 200));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));

        // Getting the statistics from the db
        DBConnection db = new DBConnection();
        SystemItem stats = db.getSystemStatistics();

        topInfoItem noFlightsPanel = new topInfoItem("Total Flights", String.valueOf(stats.getTotalFlights()));
        this.add(noFlightsPanel);
        topInfoItem noUsersPanel = new topInfoItem("Total Users", String.valueOf(stats.getTotalUsers()));
        this.add(noUsersPanel);
        topInfoItem noBookingsPanel = new topInfoItem("Bookings Today", String.valueOf(stats.getBookingsToday()));
        this.add(noBookingsPanel);

        this.setVisible(true);
    }
}
class topInfoItem extends JPanel{
    topInfoItem(String title, String info){
        this.setPreferredSize(new Dimension(210, 190));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#ffffff"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(titleLabel, gbc);
        JLabel infoLabel = new JLabel(info);
        infoLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(infoLabel, gbc);

    }
}
class midInfo extends JPanel{
    private JTable resultsTable;
    private Object[][] allFlights;
    midInfo(){
        this.setPreferredSize(new Dimension(633, 600));

        // Getting all flights from db and placing it into 2D array
        DBConnection db = new DBConnection();
        ArrayList<FlightItem> retrievedFlights = db.retrieveAllFlights();

        allFlights = new Object[retrievedFlights.size()][6];
        for(int i = 0; i < retrievedFlights.size(); i++){
            FlightItem flight = retrievedFlights.get(i);
            allFlights[i][0] = flight.getFlightNo();
            allFlights[i][1] = flight.getAirline();
            allFlights[i][2] = flight.getDepartureAirport();
            allFlights[i][3] = flight.getArrivalAirport();
            allFlights[i][4] = flight.getDepartureTime();
            allFlights[i][5] = flight.getArrivalTime();
        }

        // All flights Panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setPreferredSize(new Dimension(633, 450));
        resultsPanel.setBorder(BorderFactory.createTitledBorder("All Flights"));
        resultsTable = new JTable();
        resultsTable.setModel(new DefaultTableModel(allFlights, new String[]{"Flight No", "Airline", "Departure Airport", "Arrival Airport", "Departure Time", "Arrival Time"}));
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(resultsPanel);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(633, 150));
        buttonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        JButton editButton = new JButton("EDIT");
        editButton.setBackground(Color.decode("#bfa304"));
        editButton.setFont(new Font("Dialog", Font.BOLD, 18));
        editButton.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 15, 0, 0);
        buttonsPanel.add(editButton, gbc);
        JButton addButton = new JButton("ADD FLIGHT");
        addButton.addActionListener(e ->{
            this.removeAll();
            this.add(new AddFlightPage());
            this.revalidate();
            this.repaint();
        });
        addButton.setBackground(Color.decode("#33b8ff"));
        addButton.setFont(new Font("Dialog", Font.BOLD, 18));
        addButton.setForeground(Color.white);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 15, 0, 0);
        buttonsPanel.add(addButton, gbc);

        this.add(buttonsPanel);

        this.setVisible(true);
    }
}
class AddFlightPage extends JPanel{
    AddFlightPage(){
        this.setBounds(0, 0, 633, 600);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Enter Flight Details:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        JLabel airlineLabel = new JLabel("Airline");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(airlineLabel, gbc);

        JTextField airlineField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(airlineField, gbc);

        JLabel deptAirport = new JLabel("Departure Airport");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(deptAirport, gbc);

        JTextField deptAirportField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(deptAirportField, gbc);

        JLabel arrivalAirport = new JLabel("Arrival Airport");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(arrivalAirport, gbc);

        JTextField arrivalAirportField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(arrivalAirportField, gbc);

        JLabel deptTime = new JLabel("Departure Time");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(deptTime, gbc);

        JTextField deptTimeField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(deptTimeField, gbc);

        JLabel arrTime = new JLabel("Arrival Time");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(arrTime, gbc);

        JTextField arrTimeField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(arrTimeField, gbc);

        JLabel totSeats = new JLabel("Total Seats");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(totSeats, gbc);

        JTextField totSeatsField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(totSeatsField, gbc);

        JButton addButton = new JButton("ADD");
        addButton.setFont(new Font("Dialog", Font.BOLD, 18));
        addButton.addActionListener(e -> {
            // Add Flight to DB
//            DBConnection db = new DBConnection();
//            db.addFlight(airlineField.getText(), deptAirportField.getText(), arrivalAirportField.getText(), deptTimeField.getText(), arrTimeField.getText(), Integer.valueOf(totSeatsField.getText()));
//            // Send user to dashboard
            this.removeAll();
            this.add(new midInfo());
            this.revalidate();
            this.repaint();
            // Display process success
            JOptionPane.showMessageDialog(null, "Flight was successfully added.", "Flight Successfully added", JOptionPane.INFORMATION_MESSAGE);

        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addButton, gbc);
    }
}
class rightInfo extends JPanel{
    rightInfo(){
        this.setPreferredSize(new Dimension(316, 800));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        this.setBackground(Color.decode("#ffffff"));


        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBackground(Color.decode("#ffffff"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel titleLabel = new JLabel("RECENT BOOKINGS");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 0;
        titlePanel.add(titleLabel, gbc);
        this.add(titlePanel);

        JPanel columnPanel = new JPanel();
        columnPanel.setBackground(Color.decode("#ffffff"));
        columnPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        JLabel flightID = new JLabel("Flight No");
        flightID.setFont(new Font("Dialog", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 0, 0);
        columnPanel.add(flightID, gbc);
        JLabel username = new JLabel("Passenger Name");
        username.setFont(new Font("Dialog", Font.BOLD, 17));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 25, 0, 0);
        columnPanel.add(username, gbc);
        this.add(columnPanel);

        JPanel rightPanelContents = new JPanel();
        DBConnection db = new DBConnection();
        ArrayList<RecentBookingsItem> recentBookings = db.getUserRecentBookings();
        for(RecentBookingsItem booking : recentBookings){
            rightInfoItem contentPanel = new rightInfoItem(booking.getFlightID(), booking.getUsername());
            rightPanelContents.add(contentPanel);
        }
        JScrollPane scrollPane = new JScrollPane(rightPanelContents);
        this.add(scrollPane);

        this.setVisible(true);
    }
}
class rightInfoItem extends JPanel{
    rightInfoItem(int flightID, String username){
        this.setPreferredSize(new Dimension(316, 60));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#ffffff"));
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel flightIDPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flightIDPanel.setPreferredSize(new Dimension(157, 60));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        JLabel flightIDLabel = new JLabel(String.valueOf(flightID));
        flightIDLabel.setFont(new Font("Dialog", Font.PLAIN, 17));
        flightIDPanel.add(flightIDLabel);
        this.add(flightIDPanel, gbc);
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.setPreferredSize(new Dimension(157, 60));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        JLabel usernameLabel = new JLabel(username);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 17));
        usernamePanel.add(usernameLabel);
        this.add(usernamePanel, gbc);

        this.setVisible(true);

    }
}