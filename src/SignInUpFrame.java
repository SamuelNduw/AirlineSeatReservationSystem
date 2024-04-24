import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInUpFrame extends JFrame implements ActionListener {
    JPanel signInUpPanel;
    JButton signUp;
    private String placeholderFirstName = "First Name";
    private JButton signIn;

    SignInUpFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800, 600);
        this.setTitle("Classroom Management");
        this.setResizable(false);

        // LEFT PANEL
        JPanel mottoPanel = new JPanel();
        mottoPanel.setBounds(0, 0, 350, 600);
        mottoPanel.setBackground(Color.decode("#0D1826"));
        mottoPanel.setLayout(null);

        JLabel mottoLabel = new JLabel("Fly with Us Today?");
        mottoLabel.setBounds(65, 60, 300, 40);
        mottoLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        mottoLabel.setForeground(Color.WHITE);
        mottoPanel.add(mottoLabel);

        JLabel userIconLabel = new JLabel();
        ImageIcon userIcon = new ImageIcon(getClass().getResource("Images/ourLogo.png"));
        userIconLabel.setIcon(userIcon);
        userIconLabel.setBounds(63, 185, 225, 225);
        mottoPanel.add(userIconLabel);

        // RIGHT PANEL
        signInUpPanel = new JPanel();
        signInUpPanel.setBounds(350, 0, 450, 600);
        signInUpPanel.setBackground(Color.decode("#ffffff"));
        signInUpPanel.setLayout(null);

        JLabel topRightLabel = new JLabel("AIRLINE SEAT RESERVATION");
        topRightLabel.setBounds(40, 70, 400, 30);
        topRightLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        topRightLabel.setForeground(Color.BLACK);
        signInUpPanel.add(topRightLabel);

        JLabel topRightLabel2 = new JLabel("MANAGEMENT");
        topRightLabel2.setBounds(110, 100, 220, 60);
        topRightLabel2.setFont(new Font("Dialog", Font.BOLD, 25));
        topRightLabel2.setForeground(Color.BLACK);
        signInUpPanel.add(topRightLabel2);

        signIn = new JButton("SIGN IN");
        signIn.setBounds(145, 250, 130, 40);
        signIn.setBackground(Color.decode("#bfa304"));
        signIn.setFont(new Font("Dialog", Font.BOLD, 18));
        signIn.setForeground(Color.WHITE);
        signIn.addActionListener(this);
        signInUpPanel.add(signIn);
        signUp = new JButton("SIGN UP");
        signUp.setBounds(145, 310, 130, 40);
        signUp.setBackground(Color.decode("#33B8FF"));
        signUp.setFont(new Font("Dialog", Font.BOLD, 18));
        signUp.setForeground(Color.white);
        signUp.addActionListener(this);
        signInUpPanel.add(signUp);


        this.add(mottoPanel);
        this.add(signInUpPanel);

        this.setVisible(true);

    }

    public static void main(String[] args) {
        SignInUpFrame sn = new SignInUpFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signIn){
            signInUpPanel.removeAll();
            signInUpPanel.add(new SignInPanel());
            signInUpPanel.revalidate();
            signInUpPanel.repaint();
        }
        if(e.getSource() == signUp){
            signInUpPanel.removeAll();
            signInUpPanel.add(new SignUpPanel());
            signInUpPanel.revalidate();
            signInUpPanel.repaint();
        }
    }
}

