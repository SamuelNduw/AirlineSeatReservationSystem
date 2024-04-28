import Authentication.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPageGUI extends JPanel {
    private JLabel titleLabel, methodLabel, cardLabel, expLabel, cvvLabel, amountLabel;
    private JTextField cardField, expField, cvvField, amountField;
    private JComboBox<String> methodComboBox;
    private JButton payButton;

    public PaymentPageGUI(Auth auth, int bookingID, double cost) {
        this.setBounds(0, 0, 950, 800);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        titleLabel = new JLabel("Enter Payment Details:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        methodLabel = new JLabel("Payment Method:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(methodLabel, gbc);

        String[] methods = {"Credit Card", "PayPal"};
        methodComboBox = new JComboBox<>(methods);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(methodComboBox, gbc);

        cardLabel = new JLabel("Card Number:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(cardLabel, gbc);

        cardField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(cardField, gbc);

        expLabel = new JLabel("Expiration Date (MM/YYYY):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(expLabel, gbc);

        expField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(expField, gbc);

        cvvLabel = new JLabel("CVV:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(cvvLabel, gbc);

        cvvField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cvvField, gbc);

        amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(amountLabel, gbc);

        amountField = new JTextField(15);
        amountField.setText(String.valueOf(cost));
        amountField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(amountField, gbc);

        payButton = new JButton("Pay");
        payButton.setFont(new Font("Dialog", Font.BOLD, 18));
        payButton.addActionListener(e -> {
                String selectedMethod = (String) methodComboBox.getSelectedItem();
                // Process Payment
                // Send the values to the database and make payment
                DBConnection db = new DBConnection();
                db.processPayment(bookingID, cost, selectedMethod);
                // Send user to beginning (Home)
                this.removeAll();
                this.add(new FlightSearchPage(auth));
                this.revalidate();
                this.repaint();
                // Display payment confirmation
                JOptionPane.showMessageDialog(null, "Your payment has been processed Successfully.", "Payment Processed", JOptionPane.INFORMATION_MESSAGE);

        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(payButton, gbc);

        setVisible(true);
    }
}
