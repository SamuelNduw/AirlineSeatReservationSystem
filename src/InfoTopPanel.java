import javax.swing.*;
import java.awt.*;

public class InfoTopPanel extends JPanel {
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
