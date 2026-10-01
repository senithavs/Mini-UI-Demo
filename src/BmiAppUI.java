import javax.swing.*;
import java.awt.*;

public class BmiAppUI {


    private JComboBox<String> unitBox;
    private JLabel weightLabel = new JLabel("Weight (kg):");
    private JLabel heightLabel = new JLabel("Height (m):");
    private JTextField weightField = new JTextField();
    private JTextField heightField = new JTextField();
    private JLabel bmiLabel = new JLabel("-");
    private JLabel categoryLabel = new JLabel("-");

    public void start() {
        JFrame frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 470);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setLayout(new BorderLayout(10, 10));
        ((JPanel) frame.getContentPane())
                .setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.setVisible(true);
    }

    public void addText(String text) {
        weightField.setText(text);
    }
}