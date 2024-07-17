import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp {
    private JFrame frame;
    private JTextField textField;
    private String operator = "";
    private double num1 = 0;
    private boolean start = true;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorApp::new);
    }

    public CalculatorApp() {
        frame = new JFrame("Calculator");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textField = new JTextField("0");
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                if (start) {
                    textField.setText("");
                    start = false;
                }
                textField.setText(textField.getText() + command);
            } else if (command.equals("C")) {
                textField.setText("0");
                start = true;
                operator = "";
                num1 = 0;
            } else if (command.equals("=")) {
                double num2 = Double.parseDouble(textField.getText());
                double result = 0;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textField.setText("Error");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
                operator = "";
                start = true;
            } else {
                if (!operator.isEmpty()) {
                    return;
                }
                operator = command;
                num1 = Double.parseDouble(textField.getText());
                start = true;
            }
        }
    }
}
