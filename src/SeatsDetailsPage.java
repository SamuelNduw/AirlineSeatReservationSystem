import Authentication.Auth;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SeatsDetailsPage extends JPanel {
    private JTable resultsTable;
    private JButton selectSeatButton;
    private JTextArea seatDetailsTextArea;
    private JTextField searchField;
    private JButton searchButton;
    private Object[][] allSeats;
    private int selectedFlightID;
    private String selectedSeatClass;
    private SeatItem selectedSeat;
    JButton proceedButton;

    public SeatsDetailsPage(Auth auth, FlightItem2 selectedFlight) {

//        this.allSeats =  seats;

        this.setBounds(0, 0, 950, 800);
        this.setLayout(new BorderLayout());
        //this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.decode("#787878"));
        this.setBounds(0, 0, 950, 800);

        // get information from previous page;
        selectedFlightID = selectedFlight.getFlightNo();
        selectedSeatClass = selectedFlight.getSeatClass();

        // create 2D array using seats retrieved
        DBConnection db = new DBConnection();
        ArrayList<SeatItem> retrievedSeats = db.getSeatsByClass(selectedFlightID, selectedSeatClass);

        Object[][] seats = new Object[retrievedSeats.size()][5];
        for(int i = 0; i < retrievedSeats.size(); i++){
            SeatItem seat = retrievedSeats.get(i);
            seats[i][0] = seat.getSeatID();
            seats[i][1] = seat.getFlightID();
            seats[i][2] = seat.getSeatNo();
            seats[i][3] = seat.getSeatClass();
            seats[i][4] = seat.getStatus();
        }

        this.allSeats = seats;


        // Main central panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        // Results table panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Available Seats"));
        resultsTable = new JTable();
        resultsTable.setModel(new DefaultTableModel(allSeats, new String[]{"Seat ID", "Flight ID", "Seat Number", "Class", "Status"}));
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setPreferredSize(new Dimension(950, 500));
        resultsPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(resultsPanel);

        // Flight details panel
        JPanel seatDetailsPanel = new JPanel(new BorderLayout());
        seatDetailsPanel.setBorder(BorderFactory.createTitledBorder("Selected Seat Details"));
        seatDetailsTextArea = new JTextArea();
        seatDetailsTextArea.setEditable(false);
        seatDetailsTextArea.setFont(new Font("Verdana", Font.BOLD, 17)); // Set font and size
        JScrollPane detailsScrollPane = new JScrollPane(seatDetailsTextArea);
        detailsScrollPane.setPreferredSize(new Dimension(600, 200)); // Set size
        seatDetailsPanel.add(detailsScrollPane, BorderLayout.CENTER);
        mainPanel.add(seatDetailsPanel);
        this.add(mainPanel, BorderLayout.CENTER);

        // Select Flight button
        selectSeatButton = new JButton("Select Seat");
        selectSeatButton.setEnabled(false);
        selectSeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = resultsTable.getSelectedRow();
                if (selectedRowIndex != -1) {
                    displaySeatDetails(allSeats[selectedRowIndex]);
                }
            }
        });
        resultsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectSeatButton.setEnabled(resultsTable.getSelectedRow() != -1);
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(selectSeatButton);
        this.add(buttonPanel, BorderLayout.NORTH);

        JPanel proceedPanel = new JPanel();
        proceedPanel.setPreferredSize(new Dimension(950, 100));
        proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(e -> {
            int selectedRowIndex = resultsTable.getSelectedRow();
            if(selectedRowIndex != -1){
                int seatID = (int) allSeats[selectedRowIndex][0];
                int flightID = (int) allSeats[selectedRowIndex][1];
                String seatNo = (String) allSeats[selectedRowIndex][2];
                String seatClass = (String) allSeats[selectedRowIndex][3];
                String status = (String) allSeats[selectedRowIndex][4];

                selectedSeat = new SeatItem(seatID, flightID, seatNo, seatClass, status);
            }
            this.removeAll();
            this.add(new BookingConfirmationPage(auth, selectedFlight, selectedSeat));
            this.revalidate();
            this.repaint();
        });
        proceedButton.setPreferredSize(new Dimension(150, 40));
        proceedPanel.add(proceedButton);
        this.add(proceedPanel, BorderLayout.SOUTH);



        setVisible(true);
    }

    private void searchFlights(String keyword) {
        List<Object[]> searchResults = new ArrayList<>();
        for (Object[] flight : allSeats) {
            for (Object detail : flight) {
                if (detail.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(flight);
                    break;
                }
            }
        }

        Object[][] searchResultsArray = searchResults.toArray(new Object[0][]);
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        model.setDataVector(searchResultsArray, new String[]{"Seat ID", "Flight ID", "Seat Number", "Class", "Status"});
    }

    public void displaySeatDetails(Object[] seatDetails) {
        StringBuilder seatDetailsBuilder = new StringBuilder();

        seatDetailsBuilder.append("Seat ID: ").append(seatDetails[0]).append("\n");
        seatDetailsBuilder.append("Flight ID: ").append(seatDetails[1]).append("\n");
        seatDetailsBuilder.append("Seat Number: ").append(seatDetails[2]).append("\n");
        seatDetailsBuilder.append("Class: ").append(seatDetails[3]).append("\n");
        seatDetailsBuilder.append("Status: ").append(seatDetails[4]).append("\n");


        seatDetailsTextArea.setText(seatDetailsBuilder.toString());
    }


}