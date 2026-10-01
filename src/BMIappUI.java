import javax.swing.*;
import java.awt.*;

public class BMIappUI {

    private final BMICalculator calculator = new BMICalculator();

    private final JTextField ageField    = new JTextField();
    private final JTextField weightField = new JTextField();
    private final JTextField heightField = new JTextField();

    private final JLabel bmiValueLabel = new JLabel("-");
    private final JLabel categoryLabel = new JLabel("-");
    private final JLabel bmiRangeLabel = new JLabel("-");
    private final JLabel statusLabel   = new JLabel("-");

    public void start() {
        JFrame frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(430, 460);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        ((JPanel) frame.getContentPane())
                .setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        frame.add(createTitleLabel(), BorderLayout.NORTH);
        frame.add(createMainPanel(),  BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JLabel createTitleLabel() {
        JLabel title = new JLabel("BMI Calculator", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        return title;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 0, 15));
        panel.add(createInputPanel());
        panel.add(createButtonPanel());
        panel.add(createResultPanel());
        return panel;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Age (year) :"));
        panel.add(ageField);
        panel.add(new JLabel("Weight :"));
        panel.add(weightField);
        panel.add(new JLabel("Height :"));
        panel.add(heightField);
        return panel;
    }

    private JPanel createButtonPanel() {
        JButton calcButton  = new JButton("Calculate BMI");
        JButton resetButton = new JButton("Reset");

        calcButton.addActionListener(e -> calculateBMI());
        resetButton.addActionListener(e -> resetForm());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panel.add(calcButton);
        panel.add(resetButton);
        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 8));

        panel.add(new JLabel("Your BMI Value :"));
        panel.add(bmiValueLabel);
        panel.add(new JLabel("Category :"));
        panel.add(categoryLabel);
        panel.add(new JLabel("BMI Range :"));
        panel.add(bmiRangeLabel);
        panel.add(new JLabel("Weight Status :"));
        panel.add(statusLabel);
        return panel;
    }

    private void calculateBMI() {
        try {
            double age    = Double.parseDouble(ageField.getText().trim());
            double weight = Double.parseDouble(weightField.getText().trim());
            double height = Double.parseDouble(heightField.getText().trim());

            if (age <= 0 || weight <= 0 || height <= 0) {
                showError("Age, weight and height must be greater than 0!");
                return;
            }

            double bmi = calculator.calculateMetric(weight, height);
            displayResult(bmi);

        } catch (NumberFormatException ex) {
            showError("Please enter valid numbers in all fields!");
        }
    }

    private void displayResult(double bmi) {
        String category = BMICategory.getCategory(bmi);

        bmiValueLabel.setText(String.format("%.2f", bmi));
        categoryLabel.setText(category);
        bmiRangeLabel.setText(getRange(category));
        statusLabel.setText(getStatus(category));

        Color color = getCategoryColor(category);
        categoryLabel.setForeground(color);
        statusLabel.setForeground(color);
    }

    private void resetForm() {
        ageField.setText("");
        weightField.setText("");
        heightField.setText("");

        bmiValueLabel.setText("-");
        categoryLabel.setText("-");
        bmiRangeLabel.setText("-");
        statusLabel.setText("-");

        categoryLabel.setForeground(Color.BLACK);
        statusLabel.setForeground(Color.BLACK);
    }

    private String getRange(String category) {
        return switch (category) {
            case "Underweight" -> "less than 18.5";
            case "Normal" -> "between 18.5 and 24.9";
            case "Overweight" -> "between 25 and 29.9";
            default -> "30 or greater";
        };
    }

    private String getStatus(String category) {
        return switch (category) {
            case "Underweight" -> "You are below the healthy range";
            case "Normal" -> "You are at a healthy weight";
            case "Overweight" -> "You are slightly above the healthy range";
            default -> "You are well above the healthy range";
        };
    }

    private Color getCategoryColor(String category) {
        return switch (category) {
            case "Normal" -> new Color(0, 153, 0);
            case "Overweight" -> new Color(204, 102, 0);
            case "Obese" -> Color.RED;
            default -> Color.BLUE;
        };
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message,
                "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}