import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInfoApp {
    private JFrame frame;
    private JTextField nameField, emailField, phoneField, addressField;
    private JTextArea displayArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CustomerInfoApp::new);
    }

    public CustomerInfoApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Customer Information");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        formPanel.add(submitButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetForm());
        formPanel.add(resetButton);

        frame.add(formPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void resetForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
        displayArea.setText("");
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(frame, "Invalid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder customerInfo = new StringBuilder();
            customerInfo.append("Customer Information:\n")
                    .append("Name: ").append(name).append("\n")
                    .append("Email: ").append(email).append("\n")
                    .append("Phone: ").append(phone).append("\n")
                    .append("Address: ").append(address).append("\n");

            displayArea.setText(customerInfo.toString());
        }
    }
}
