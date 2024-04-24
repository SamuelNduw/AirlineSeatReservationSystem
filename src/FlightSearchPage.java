import Authentication.Auth;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchPage extends JPanel{
    private JTextField departureField;
    private JTextField arrivalField;
    private JTextField departureDateField;
    private JTextField arrivalDateField;
    private JComboBox<String> seatClassComboBox;
    private JButton searchButton;
    private JTable resultsTable;
    private JButton proceedButton;

    Object[][] allFlights;
    static FlightItem2 selectedFlight;

    public FlightSearchPage(Auth auth) {
        setBounds(0, 0, 950, 800);
        this.setSize(950, 800);
        this.setLayout(new BorderLayout());

        JPanel searchCriteriaPanel = new JPanel(new GridBagLayout());
        searchCriteriaPanel.setBorder(BorderFactory.createEmptyBorder(10, 7, 10, 7));
        searchCriteriaPanel.setBackground(Color.LIGHT_GRAY);
//        searchCriteriaPanel.setPreferredSize(new Dimension(950, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel departureLabel = new JLabel("Departure Airport:");
        departureField = new JTextField(10);
        searchCriteriaPanel.add(departureLabel, gbc);
        gbc.gridx++;
        searchCriteriaPanel.add(departureField, gbc);

        JLabel arrivalLabel = new JLabel("Arrival Airport:");
        arrivalField = new JTextField(10);
        gbc.gridx++;
        searchCriteriaPanel.add(arrivalLabel, gbc);
        gbc.gridx++;
        searchCriteriaPanel.add(arrivalField, gbc);

        JLabel departureDateLabel = new JLabel("Departure Date:");
        departureDateField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchCriteriaPanel.add(departureDateLabel, gbc);
        gbc.gridx++;
        searchCriteriaPanel.add(departureDateField, gbc);



        JLabel seatClassLabel = new JLabel("Seat Class:");
        String[] seatClasses = {"Economy", "Business", "First", "All"};
        seatClassComboBox = new JComboBox<>(seatClasses);
        gbc.gridx = 0;
        gbc.gridy++;
        searchCriteriaPanel.add(seatClassLabel, gbc);
        gbc.gridx++;
        searchCriteriaPanel.add(seatClassComboBox, gbc);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFlights();
            }
        });
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchCriteriaPanel.add(searchButton, gbc);

        this.add(searchCriteriaPanel, BorderLayout.NORTH);

        resultsTable = new JTable();
        resultsTable.setBackground(Color.WHITE);
        JScrollPane resultsScrollPane = new JScrollPane(resultsTable);

        // Adjusting preferred size to take up more space
        resultsScrollPane.setPreferredSize(new Dimension(950, 500));
        resultsScrollPane.setBackground(Color.BLACK);
        this.add(resultsScrollPane, BorderLayout.CENTER);


        JPanel proceedPanel = new JPanel();
        proceedPanel.setPreferredSize(new Dimension(950, 100));
        proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(e -> {
            int selectedRowIndex = resultsTable.getSelectedRow();
            if(selectedRowIndex != -1){
                int flightNo = (int) allFlights[selectedRowIndex][0];
                String airline = (String) allFlights[selectedRowIndex][1];
                String deptAirport = (String) allFlights[selectedRowIndex][2];
                String arrAirport = (String) allFlights[selectedRowIndex][3];
                String deptTime = (String) allFlights[selectedRowIndex][4];
                String arrTime = (String) allFlights[selectedRowIndex][5];
                int totSeats = (int) allFlights[selectedRowIndex][6];
                int avalSeats = (int) allFlights[selectedRowIndex][7];
                String seatClass = (String) seatClassComboBox.getSelectedItem();

                selectedFlight = new FlightItem2(flightNo, airline, deptAirport, arrAirport, deptTime, arrTime,totSeats, avalSeats, seatClass);

            }
            this.removeAll();
            this.add(new SeatsDetailsPage(auth, selectedFlight));
            this.revalidate();
            this.repaint();
        });
        proceedButton.setPreferredSize(new Dimension(150, 40));
        proceedPanel.add(proceedButton);
        this.add(proceedPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


    private void searchFlights() {
        String departure = departureField.getText();
        String arrival = arrivalField.getText();
        String departureDate = departureDateField.getText();
        String seatClass = (String) seatClassComboBox.getSelectedItem();

        //
        JOptionPane.showMessageDialog(this, "Searching for flights...");

        // Get flight data based on search criteria
        Object[][] flights = getAllFlights(departure, arrival, departureDate, seatClass);


        displaySearchResults(flights);
    }

    private Object[][] getAllFlights(String departure, String arrival, String departureDate, String seatClass) {

        DBConnection db = new DBConnection();
        ArrayList<FlightItem> retrievedFlights = db.searchFlights(departure, arrival, departureDate);
        allFlights = new Object[retrievedFlights.size()][8];
        for(int i = 0; i< retrievedFlights.size(); i++){
            FlightItem flight = retrievedFlights.get(i);
            allFlights[i][0] = flight.getFlightNo();
            allFlights[i][1] = flight.getAirline();
            allFlights[i][2] = flight.getDepartureAirport();
            allFlights[i][3] = flight.getArrivalAirport();
            allFlights[i][4] = flight.getDepartureTime();
            allFlights[i][5] = flight.getArrivalTime();
            allFlights[i][6] = flight.getTotalSeats();
            allFlights[i][7] = flight.getAvailableSeats();
        }
        for(Object[] row : allFlights){
            for(Object col : row){
                System.out.println(col + " ");
            }
            System.out.println();
        }

        return allFlights;
    }

    private void displaySearchResults(Object[][] searchResults) {
        String[] columnNames = {"Flight No", "Airline", "Departure Airport", "Arrival Airport", "Departure Time", "Arrival Time", "Total Seats", "Available Seats"};

        DefaultTableModel model = new DefaultTableModel(searchResults, columnNames);
        resultsTable.setModel(model);
    }
}