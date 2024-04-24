import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FlightDetailsPageTesting extends JFrame {
    private JTable resultsTable;
    private JButton selectFlightButton;
    private JTextArea flightDetailsTextArea;
    private JTextField searchField;
    private JButton searchButton;
    private Object[][] allFlights;

    public FlightDetailsPageTesting(Object[][] flights) {
        this.allFlights = flights;

        setTitle("Flight Details");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFlights(searchField.getText());
            }
        });
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Results table panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Search Results"));
        resultsTable = new JTable();
        resultsTable.setModel(new DefaultTableModel(allFlights, new String[]{"Flight No", "Airline", "Departure Airport", "Arrival Airport", "Departure Time", "Arrival Time", "Total Seats", "Available Seats"}));
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(resultsPanel, BorderLayout.CENTER);

        // Flight details panel
        JPanel flightDetailsPanel = new JPanel(new BorderLayout());
        flightDetailsPanel.setBorder(BorderFactory.createTitledBorder("Selected Flight Details"));
        flightDetailsTextArea = new JTextArea();
        flightDetailsTextArea.setEditable(false);
        flightDetailsTextArea.setFont(new Font("Verdana", Font.BOLD, 17)); // Set font and size
        JScrollPane detailsScrollPane = new JScrollPane(flightDetailsTextArea);
        detailsScrollPane.setPreferredSize(new Dimension(600, 300)); // Set size
        flightDetailsPanel.add(detailsScrollPane, BorderLayout.CENTER);
        mainPanel.add(flightDetailsPanel, BorderLayout.SOUTH);

        // Select Flight button
        selectFlightButton = new JButton("Select Flight");
        selectFlightButton.setEnabled(false);
        selectFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = resultsTable.getSelectedRow();
                if (selectedRowIndex != -1) {
                    displayFlightDetails((Object[]) allFlights[selectedRowIndex]);
                }
            }
        });
        resultsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectFlightButton.setEnabled(resultsTable.getSelectedRow() != -1);
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(selectFlightButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        add(mainPanel);

        setVisible(true);
    }

    private void searchFlights(String keyword) {
        List<Object[]> searchResults = new ArrayList<>();
        for (Object[] flight : allFlights) {
            for (Object detail : flight) {
                if (detail.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(flight);
                    break;
                }
            }
        }

        Object[][] searchResultsArray = searchResults.toArray(new Object[0][]);
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        model.setDataVector(searchResultsArray, new String[]{"Flight No", "Airline", "Departure Airport", "Arrival Airport", "Departure Time", "Arrival Time", "Total Seats", "Available Seats"});
    }

    public void displayFlightDetails(Object[] flightDetails) {
        StringBuilder flightDetailsBuilder = new StringBuilder();

        flightDetailsBuilder.append("Flight Number: ").append(flightDetails[0]).append("\n");
        flightDetailsBuilder.append("Airline: ").append(flightDetails[1]).append("\n");
        flightDetailsBuilder.append("Departure Airport: ").append(flightDetails[2]).append("\n");
        flightDetailsBuilder.append("Arrival Airport: ").append(flightDetails[3]).append("\n");
        flightDetailsBuilder.append("Departure Time: ").append(flightDetails[4]).append("\n");
        flightDetailsBuilder.append("Arrival Time: ").append(flightDetails[5]).append("\n");
        flightDetailsBuilder.append("Total Seats: ").append(flightDetails[6]).append("\n");
        flightDetailsBuilder.append("Available Seats: ").append(flightDetails[7]).append("\n");

        flightDetailsTextArea.setText(flightDetailsBuilder.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            DBConnection db = new DBConnection();
            ArrayList<FlightItem> retrievedFlights = db.retrieveAllFlights();

            Object[][] flights = new Object[retrievedFlights.size()][8];
            for (int i = 0; i < retrievedFlights.size(); i++) {
                FlightItem flight = retrievedFlights.get(i);
                flights[i][0] = flight.getFlightNo();
                flights[i][1] = flight.getAirline();
                flights[i][2] = flight.getDepartureAirport();
                flights[i][3] = flight.getArrivalAirport();
                flights[i][4] = flight.getDepartureTime();
                flights[i][5] = flight.getArrivalTime();
                flights[i][6] = flight.getTotalSeats();
                flights[i][7] = flight.getAvailableSeats();
            }

            // Print the array to verify
            for(Object[] row : flights){
                for(Object col : row){
                    System.out.println(col + " ");
                }
                System.out.println();
            }

            FlightDetailsPageTesting flightDetailsPage = new FlightDetailsPageTesting(flights);
            flightDetailsPage.setVisible(true);
        });
    }
}