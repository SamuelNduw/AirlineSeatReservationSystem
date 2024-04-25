import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class FlightDetailsPage extends JPanel {
    private JTable resultsTable;
    private JButton selectFlightButton;
    private JTextArea flightDetailsTextArea;
    private JTextField searchField;
    private JButton searchButton;
    private Object[][] allFlights;

    public FlightDetailsPage(Object[][] flights) {
        this.allFlights =  flights;

        this.setBounds(0, 0, 950, 800);
        this.setLayout(new BorderLayout());
        //this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.decode("#787878"));
        this.setBounds(0, 0, 950, 800);


        // Results table panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createTitledBorder("All Flights"));
        resultsTable = new JTable();
        resultsTable.setModel(new DefaultTableModel(allFlights, new String[]{"Flight No", "Airline", "Departure Airport", "Arrival Airport", "Departure Time", "Arrival Time", "Total Seats", "Available Seats"}));
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(resultsPanel, BorderLayout.CENTER);

        // Flight details panel
        JPanel flightDetailsPanel = new JPanel(new BorderLayout());
        flightDetailsPanel.setBorder(BorderFactory.createTitledBorder("Flight Details"));
        flightDetailsTextArea = new JTextArea();
        flightDetailsTextArea.setEditable(false);
        flightDetailsTextArea.setFont(new Font("Verdana", Font.BOLD, 17)); // Set font and size
        JScrollPane detailsScrollPane = new JScrollPane(flightDetailsTextArea);
        detailsScrollPane.setPreferredSize(new Dimension(600, 300)); // Set size
        flightDetailsPanel.add(detailsScrollPane, BorderLayout.CENTER);
        this.add(flightDetailsPanel, BorderLayout.SOUTH);

        // Select Flight button
        selectFlightButton = new JButton("Select Flight");
        selectFlightButton.setEnabled(false);
        selectFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = resultsTable.getSelectedRow();
                if (selectedRowIndex != -1) {
                    displayFlightDetails(allFlights[selectedRowIndex]);
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
        this.add(buttonPanel, BorderLayout.NORTH);



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


}