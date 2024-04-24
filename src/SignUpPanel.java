import Authentication.Auth;
import emailValidator.EmailValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPanel extends JPanel implements ActionListener {
    PlaceholderTextField dfdffd, name, email, contactNumber, newPassword, confirmPassword;
    JButton join;
    SignUpPanel(){
        this.setBounds(0, 0, 450, 600);
        this.setBackground(Color.decode("#ffffff"));
        this.setLayout(null);

        // sign in or sign up Label
        JLabel sign = new JLabel("SIGN UP");
        sign.setBounds(75, 60, 300, 50);
        sign.setFont(new Font("Dialog", Font.BOLD, 30));
        sign.setForeground(Color.BLACK);
        this.add(sign);

        // Form input fields
        name = new PlaceholderTextField("NAME");
        name.setBounds(65, 200, 310, 30);
        email = new PlaceholderTextField("EMAIL");
        email.setBounds(65, 250, 310, 30);
        contactNumber = new PlaceholderTextField("MOBILE NUMBER");
        contactNumber.setBounds(65, 300, 310, 30);
        newPassword = new PlaceholderTextField("NEW PASSWORD");
        newPassword.setBounds(65, 360, 310, 30);
        confirmPassword = new PlaceholderTextField("CONFIRM PASSWORD");
        confirmPassword.setBounds(65, 410, 310, 30);
        this.add(name);
        this.add(email);
        this.add(contactNumber);
        this.add(newPassword);
        this.add(confirmPassword);

        // Form Join button
        join = new JButton("JOIN");
        join.setBounds(125, 460, 200, 35);
        join.setFont(new Font("SansSerif", Font.BOLD, 18));
        join.setBackground(Color.decode("#33B8FF"));
        join.setForeground(Color.WHITE);
        join.addActionListener(this);
        this.add(join);

        // button to take initial focus, so that textfield placeholders can show
        JButton focusInitialButton = new JButton();
        focusInitialButton.setBounds(450, 0, 0, 0);
        focusInitialButton.requestFocusInWindow();
        this.add(focusInitialButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // JOIN BUTTON IS PRESSED
        if(e.getSource() == join){
            String fName = name.getText();
            String emailVal = email.getText();
            String contactVal = contactNumber.getText();
            String nPassword = newPassword.getText(),
                    cPassword = confirmPassword.getText();

            // Check if fields are empty
            if(fName.trim().isEmpty() || emailVal.trim().isEmpty() || contactVal.trim().isEmpty() || nPassword.trim().isEmpty() || cPassword.trim().isEmpty() || fName.equals("FIRST NAME") || emailVal.equals("EMAIL") || contactVal.equals("SUBJECT") || nPassword.equals("NEW PASSWORD") || cPassword.equals("CONFIRM PASSWORD")){
                JOptionPane.showMessageDialog(null, "You left a text field empty!", "Submission Error", JOptionPane.ERROR_MESSAGE);
            }else if(!nPassword.equals(cPassword)){
                JOptionPane.showMessageDialog(null, "The new password and confirm password are not the same, please insert the same values in both text fields!", "SUBMISSION FAILED", JOptionPane.ERROR_MESSAGE);
            }else{
                // Check if email is valid
                if(EmailValidator.isValidEmail(emailVal)){
                    DBConnection db = new DBConnection();
                    db.registerUser(fName, emailVal, nPassword, contactVal);
                    Auth auth = db.loginUser(emailVal, nPassword);
                    // Success Message
                    JOptionPane.showMessageDialog(null, "You have successfully signed up.", null, JOptionPane.INFORMATION_MESSAGE);
                    name.setText("NAME");
                    email.setText("EMAIL");
                    contactNumber.setText("MOBILE NUMBER");
                    newPassword.setText("NEW PASSWORD");
                    confirmPassword.setText("CONFIRM PASSWORD");
                    // Dispose Window
                    Window[] windows = Window.getWindows();
                    for(Window window : windows){
                        window.dispose();
                    }
                    // Open Main Window
                    new MainFrame(auth);
                }else{
                    // Error Message (invalid email)
                    JOptionPane.showMessageDialog(null, "Email is not valid!", "Submission Error", JOptionPane.ERROR_MESSAGE);
                }
            };
        }


    }
}
