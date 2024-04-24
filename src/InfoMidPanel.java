import javax.swing.*;
import java.awt.*;

public class InfoMidPanel extends JPanel{
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
