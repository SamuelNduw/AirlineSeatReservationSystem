import Authentication.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {
    JButton navDashboardButton;
    JButton navProfileButton;
    JButton navAllFlightsButton;
    JButton navBookingsButton;
    JButton navIntThrButton;
    JButton bookFlightButton;
    JButton logOutButton;
    JPanel mainPanel;
    MainFrame(Auth auth){
        // Frame configurations
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setLayout(null);
        this.setTitle("Classroom Management System");
        this.setResizable(false);



        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setBounds(0, 0, 250, 800);
        navPanel.setBackground(Color.decode("#0D1826"));
        navPanel.setLayout(null);
        this.add(navPanel);

        // Nav directories Panel
        JPanel navDirPanel = new JPanel();
        navDirPanel.setBounds(0, 100, 250, 350);
        navDirPanel.setBackground(Color.decode("#0D1826"));
        navDirPanel.setLayout(new GridLayout(5, 1, 0, 10));
        navPanel.add(navDirPanel);

        // Nav directories Contents

        // dashboard
        navDashboardButton = new JButton();
        navDashboardButton.addActionListener(this);
        navDashboardButton.setBorderPainted(false);
        navDashboardButton.setLayout(null);
        navDashboardButton.setPreferredSize(new Dimension(250, 60));
        navDashboardButton.setBackground(Color.decode("#0D1826"));
        navDirPanel.add(navDashboardButton);
        JLabel dashboardIcon = new JLabel(); // Icon
        dashboardIcon.setIcon(new ImageIcon(getClass().getResource("Images/dashboardIcon.png")));
        dashboardIcon.setBounds(20, 0, 80, 60);
        navDashboardButton.add(dashboardIcon);
        JLabel dashboardText = new JLabel("DASHBOARD"); // Text
        dashboardText.setBounds(100, 0, 150, 60);
        dashboardText.setForeground(Color.white);
        dashboardText.setFont(new Font("Dialog", Font.BOLD, 18));
        navDashboardButton.add(dashboardText);

        // PROFILE PAGE
        navProfileButton = new JButton();
        navProfileButton.addActionListener(e -> {
            if(e.getSource() == navProfileButton){
                mainPanel.removeAll();
                mainPanel.add(new ProfilePage(auth));
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        navProfileButton.setBorderPainted(false);
        navProfileButton.setLayout(null);
        navProfileButton.setPreferredSize(new Dimension(250, 60));
        navProfileButton.setBackground(Color.decode("#0D1826"));
        navDirPanel.add(navProfileButton);
        JLabel profileIcon = new JLabel(); // Icon
        profileIcon.setIcon(new ImageIcon(getClass().getResource("Images/smallUserIcon.png")));
        profileIcon.setBounds(20, 0, 80, 60);
        navProfileButton.add(profileIcon);
        JLabel profileText = new JLabel("PROFILE PAGE"); // Text
        profileText.setBounds(100, 0, 150, 60);
        profileText.setForeground(Color.white);
        profileText.setFont(new Font("Dialog", Font.BOLD, 18));
        navProfileButton.add(profileText);
        // ALL FLIGHTS
        navAllFlightsButton = new JButton();
        navAllFlightsButton.addActionListener(e -> {
            if(e.getSource() == navAllFlightsButton){
                mainPanel.removeAll();
                mainPanel.add(new FlightDetailsPage(allFlights()));
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        navAllFlightsButton.setBorderPainted(false);
        navAllFlightsButton.setLayout(null);
        navAllFlightsButton.setPreferredSize(new Dimension(250, 60));
        navAllFlightsButton.setBackground(Color.decode("#0D1826"));
        navDirPanel.add(navAllFlightsButton);
        JLabel allFlightsIcon = new JLabel(); // Icon
        allFlightsIcon.setIcon(new ImageIcon(getClass().getResource("Images/airplaneIcon.png")));
        allFlightsIcon.setBounds(20, 0, 80, 60);
        navAllFlightsButton.add(allFlightsIcon);
        JLabel allFlightsText = new JLabel("VIEW ALL"); // Text
        allFlightsText.setBounds(100, 0, 150, 30);
        allFlightsText.setForeground(Color.white);
        allFlightsText.setFont(new Font("Dialog", Font.BOLD, 15));
        navAllFlightsButton.add(allFlightsText);
        JLabel allFlightsText2 = new JLabel("FLIGHTS");
        allFlightsText2. setBounds(100, 30, 150, 30);
        allFlightsText2.setForeground(Color.white);
        allFlightsText2.setFont(new Font("Dialog", Font.BOLD, 15));
        navAllFlightsButton.add(allFlightsText2);

        // BOOKINGS
        navBookingsButton = new JButton();
        navBookingsButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new BookingsPage(auth));
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        navBookingsButton.setBorderPainted(false);
        navBookingsButton.setLayout(null);
        navBookingsButton.setPreferredSize(new Dimension(250, 60));
        navBookingsButton.setBackground(Color.decode("#0D1826"));
        navDirPanel.add(navBookingsButton);
        JLabel bookingsIcon = new JLabel(); // Icon
        bookingsIcon.setIcon(new ImageIcon(getClass().getResource("Images/bookingIcon.png")));
        bookingsIcon.setBounds(20, 0, 80, 60);
        navBookingsButton.add(bookingsIcon);
        JLabel bookingsText = new JLabel("BOOKINGS"); // Text
        bookingsText.setBounds(100, 0, 150, 60);
        bookingsText.setForeground(Color.white);
        bookingsText.setFont(new Font("Dialog", Font.BOLD, 18));
        navBookingsButton.add(bookingsText);

        // intervention, threshold
        navIntThrButton = new JButton();
//        navIntThrButton.addActionListener(e -> {
//            if(e.getSource() == navIntThrButton){
//                mainPanel.removeAll();
//                mainPanel.add(new SetIntThr(auth));
//                mainPanel.revalidate();
//                mainPanel.repaint();
//            }
//        });
        navIntThrButton.setBorderPainted(false);
        navIntThrButton.setLayout(null);
        navIntThrButton.setPreferredSize(new Dimension(250, 60));
        navIntThrButton.setBackground(Color.decode("#0D1826"));
        navDirPanel.add(navIntThrButton);
        JLabel intThrIcon = new JLabel(); // Icon
        //intThrIcon.setIcon(new ImageIcon(getClass().getResource("Images/userIcon.png")));
        intThrIcon.setBounds(20, 0, 80, 60);
        navIntThrButton.add(intThrIcon);
        JLabel intThrText = new JLabel("INTERVENTION"); // Text
        intThrText.setBounds(100, 0, 150, 30);
        intThrText.setForeground(Color.white);
        intThrText.setFont(new Font("Dialog", Font.BOLD, 15));
        navIntThrButton.add(intThrText);
        JLabel intThrText2 = new JLabel("THRESHOLD"); // Text
        intThrText2.setBounds(100, 30, 150, 30);
        intThrText2.setForeground(Color.white);
        intThrText2.setFont(new Font("Dialog", Font.BOLD, 15));
        navIntThrButton.add(intThrText2);
        navIntThrButton.setVisible(false);

        // Nav Bottom Buttons Panel
        JPanel actionsPanel = new JPanel();
        actionsPanel.setBounds(25, 550, 200, 110);
        actionsPanel.setLayout(new GridLayout(2, 1, 0, 10));
        actionsPanel.setBackground(Color.decode("#0D1826"));
        navPanel.add(actionsPanel);

        // Nav Buttom Buttons
        bookFlightButton = new JButton("BOOK FLIGHT");
        bookFlightButton.addActionListener(e -> {
            // BOOK FLIGHT
            if(e.getSource() == bookFlightButton){
                mainPanel.removeAll();
                mainPanel.add(new FlightSearchPage(auth));
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        bookFlightButton.setFont(new Font("Dialog", Font.BOLD, 20));
        bookFlightButton.setForeground(Color.white);
        bookFlightButton.setBackground(Color.decode("#bfa304"));
        bookFlightButton.setBorderPainted(false);
        actionsPanel.add(bookFlightButton);

        logOutButton = new JButton("LOG OUT");
        logOutButton.addActionListener(e -> {
            // Close this window
            Window[] windows = Window.getWindows();
            for(Window window : windows){
                window.dispose();
            }
            // Open signInUpWindow
            new SignInUpFrame();
        });
        logOutButton.setFont(new Font("Dialog", Font.BOLD, 20));
        logOutButton.setForeground(Color.black);
        logOutButton.setBackground(Color.decode("#ffffff"));
        logOutButton.setBorderPainted(false);
        actionsPanel.add(logOutButton);

        // RIGHT PANEL (MAIN CONTENT)
        mainPanel = new JPanel();
        mainPanel.setBounds(250, 0, 950, 800);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);


        // FlightSearchPage

        mainPanel.add(new FlightDetailsPage(allFlights()));
        this.add(mainPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // All flights in 2D Array
    public Object[][] allFlights(){
        // FlightDetailsPage
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
//        for(Object[] row : flights){
//            for(Object col : row){
//                System.out.println(col + " ");
//            }
//            System.out.println();
//        }
        return flights;
    }
    // All seats in 2D array
//    public Object[][] allSeats(){
//
//    }


    @Override
    public void actionPerformed(ActionEvent e) {

            // LOG OUT
            if(e.getSource() == logOutButton){
//                mainPanel.removeAll();
//                mainPanel.add(new ReportPanel());
//                mainPanel.revalidate();
//                mainPanel.repaint();
            }


    }
}
