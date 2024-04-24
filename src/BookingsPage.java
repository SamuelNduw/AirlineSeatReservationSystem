import Authentication.Auth;

import javax.swing.*;
import java.awt.*;

public class BookingsPage extends JPanel {
    BookingsPage(Auth auth){
        this.setBounds(0, 0, 950, 800);
        this.setLayout(new FlowLayout());

        // Title
        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        titlePanel.setPreferredSize(new Dimension(950, 100));
        titlePanel.setBackground(Color.ORANGE);
        JLabel titleLabel = new JLabel("YOUR BOOKINGS");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.ipady = 0;
        titlePanel.add(titleLabel, gbc);
        this.add(titlePanel);

        // Contents
        JPanel mainPanel = new JPanel(new FlowLayout());


        mainPanel.setPreferredSize(new Dimension(950, 750));
        this.add(mainPanel);

        this.setVisible(true);
    }
}
class bookingPanel extends JPanel{
    bookingPanel(BHistoryItem booking){
        this.setPreferredSize(new Dimension(900, 200));
        this.setLayout(new FlowLayout());

        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel deptTime = new JLabel(booking.getDepartureTime());

        leftPanel.setPreferredSize(new Dimension(430, 200));
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(430, 200));

    }
}
