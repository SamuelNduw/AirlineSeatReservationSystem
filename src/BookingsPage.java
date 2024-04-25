import Authentication.Auth;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookingsPage extends JPanel {
    ArrayList<BHistoryItem> retrievedBookings;
    BookingsPage(Auth auth){
        this.setBounds(0, 0, 950, 800);
        this.setLayout(new FlowLayout());

        // Contents
        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.setPreferredSize(new Dimension(950, 750));

        DBConnection db = new DBConnection();
        retrievedBookings = db.getUserBookingHistory(auth.getUserID());
        for(BHistoryItem booking : retrievedBookings){
            bookingPanel bP = new bookingPanel(booking);
            mainPanel.add(bP);
            mainPanel.revalidate();
            mainPanel.repaint();
            System.out.println(booking);
        }
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        this.add(scrollPane);

        this.setVisible(true);
    }
}
class bookingPanel extends JPanel{
    bookingPanel(BHistoryItem booking){
        this.setPreferredSize(new Dimension(900, 200));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        this.setBackground(Color.decode("#ffffff"));

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(430, 200));
        leftPanel.setBackground(Color.decode("#ffffff"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel deptTime = new JLabel(booking.getDepartureTime());
        deptTime.setFont(new Font("Dialog", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 15);
        leftPanel.add( deptTime, gbc);
        JLabel arrTime = new JLabel(booking.getArrivalTime());
        arrTime.setFont(new Font("Dialog", Font.BOLD, 17));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        leftPanel.add( arrTime, gbc);
        JLabel deptAirport = new JLabel(booking.getDepartureAirport());
        deptAirport.setFont(new Font("Dialog", Font.PLAIN, 17));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 0);
        leftPanel.add( deptAirport, gbc);
        JLabel arrAirport = new JLabel(booking.getArrivalAirport());
        arrAirport.setFont(new Font("Dialog", Font.PLAIN, 17));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 0);
        leftPanel.add( arrAirport, gbc);
        JLabel airline = new JLabel(booking.getAirline());
        airline.setFont(new Font("Dialog", Font.PLAIN, 17));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(airline, gbc);
        JLabel seatNo = new JLabel("Seat: " + booking.getSeatNo());
        seatNo.setFont(new Font("Dialog", Font.BOLD, 19));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(seatNo, gbc);

        this.add(leftPanel);


        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.EAST;
        rightPanel.setPreferredSize(new Dimension(430, 200));
        rightPanel.setBackground(Color.decode("#ffffff"));
        JLabel amount = new JLabel("$" + booking.getAmount());
        amount.setFont(new Font("Dialog", Font.BOLD, 22));
        gbc2.gridy = 0;
        gbc.insets = new Insets(5, 0, 0, 0);
        rightPanel.add(amount, gbc2);
        JLabel paymentDM = new JLabel("Payment Method & Date:");
        paymentDM.setFont(new Font("Dialog", Font.BOLD, 17));
        gbc2.gridy = 1;
        gbc.insets = new Insets(5, 0, 0, 0);
        rightPanel.add(paymentDM, gbc2);
        JLabel paymentMethod = new JLabel(booking.getPaymentMethod());
        paymentMethod.setFont(new Font("Dialog", Font.PLAIN, 17));
        gbc2.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 0);
        rightPanel.add(paymentMethod, gbc2);
        JLabel paymentDate = new JLabel(booking.getPaymentDate());
        paymentDate.setFont(new Font("Dialog", Font.PLAIN, 17));
        gbc2.gridy = 3;
        gbc.insets = new Insets(5, 0, 0, 0);
        rightPanel.add(paymentDate, gbc2);

        this.add(rightPanel);

    }
}
