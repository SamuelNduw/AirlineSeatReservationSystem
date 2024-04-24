import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderTextField extends JTextField implements FocusListener {
    private String placeholder;
    private boolean hasFocus = false;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        addFocusListener(this);
        setText(placeholder);
        setBackground(Color.decode("#8E8E8E"));
        setForeground(Color.decode("#EBEBEB"));
        setFont(new Font("SansSerif", Font.BOLD, 18));
        setBorder(new EmptyBorder(5, 5, 5, 5));

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (!hasFocus && getText().equals(placeholder)) {
            setText("");
            setForeground(Color.WHITE);
        }
        hasFocus = true;
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
            setText(placeholder);
            setForeground(Color.decode("#C4C4C4"));
            hasFocus = false;
        }
    }
}
