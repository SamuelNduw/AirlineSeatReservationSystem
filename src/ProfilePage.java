import Authentication.Auth;

import javax.swing.*;
import java.awt.*;


public class ProfilePage extends JPanel {
    JPanel leftPanel;
    JPanel rightPanel;
    ProfilePage(Auth auth){
        this.setBounds(0, 0, 950, 800);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(400, 800));
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBackground(Color.decode("#ffffff"));
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel userIconLabel = new JLabel();
        userIconLabel.setIcon(new ImageIcon(getClass().getResource("Images/userIcon.png")));
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(userIconLabel, gbc);
        JLabel userName = new JLabel(auth.getUsername());
        userName.setFont(new Font("Dialog", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(100, 0, 20, 0);
        leftPanel.add(userName, gbc);
        JLabel userEmail = new JLabel(auth.getEmail());
        userEmail.setForeground(Color.DARK_GRAY);
        userEmail.setFont(new Font("Dialog", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        leftPanel.add(userEmail, gbc);
        JButton updateDetailsButton = new JButton("UPDATE DETAILS");
        updateDetailsButton.addActionListener(e ->{
            rightPanel.removeAll();
            rightPanel.add(new editDetailsPanel(auth));
            rightPanel.revalidate();
            rightPanel.repaint();
        });
        updateDetailsButton.setFont(new Font("Dialog", Font.BOLD, 18));
        updateDetailsButton.setBackground(Color.decode("#33b8ff"));
        updateDetailsButton.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(80, 0, 20, 0);
        leftPanel.add(updateDetailsButton, gbc);
        this.add(leftPanel);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        flowPanel.setPreferredSize(new Dimension(500, 800));
        userDetailsContent userNamePan = new userDetailsContent("Username", auth.getUsername(), "#ffffff");
        flowPanel.add(userNamePan);
        userDetailsContent userEmailPan = new userDetailsContent("Email", auth.getEmail(), "#ffffff");
        flowPanel.add(userEmailPan);
        userDetailsContent userNumberPan = new userDetailsContent("Contact Number", auth.getContactNumber(), "#ffffff");
        flowPanel.add(userNumberPan);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(flowPanel);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(Box.createHorizontalGlue());
        this.add(rightPanel);

        this.setVisible(true);
    }


}

class userDetailsContent extends JPanel{
    userDetailsContent(String title, String value, String color){
        this.setPreferredSize(new Dimension(500, 100));
        this.setBackground(Color.decode(color));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 5, 0, 100);
        this.add(titleLabel, gbc);
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 0, 5);
        this.add(valueLabel, gbc);

        this.setVisible(true);
    }
}

class editDetailsPanel extends JPanel{
    editDetailsPanel(Auth auth){
        this.setBounds(0, 0, 500, 800);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;


        JLabel newDetailsLabel = new JLabel("Update Your Details");
        newDetailsLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 80, 5);
        this.add(newDetailsLabel, gbc);
        JLabel usernameLabel = new JLabel("Username");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(usernameLabel, gbc);
        JTextField usernameTextField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(usernameTextField, gbc);
        JLabel emailLabel = new JLabel("Email");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(emailLabel, gbc);
        JTextField emailTextField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(emailTextField, gbc);
        JLabel contactLabel = new JLabel("Phone Number");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(contactLabel, gbc);
        JTextField contactTextField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(contactTextField, gbc);
        JButton updateButton = new JButton("UPDATE");
        updateButton.addActionListener(e -> {
            // Update details
            DBConnection db = new DBConnection();
            db.updateUserDetails(auth.getUserID(), usernameTextField.getText(), emailTextField.getText(), contactTextField.getText());
            auth.setUsername(usernameTextField.getText());
            auth.setEmail(emailTextField.getText());
            auth.setContactNumber(contactTextField.getText());

            // Close this window
            Window[] windows = Window.getWindows();
            for(Window window : windows){
                window.dispose();
            }
            // Open new Main Window
            new MainFrame(auth);
        });
        updateButton.setFont(new Font("Dialog", Font.BOLD, 18));
        updateButton.setForeground(Color.white);
        updateButton.setBackground(Color.decode("#bfa304"));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(updateButton, gbc);

        this.setVisible(true);
    }
}
