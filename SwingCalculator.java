import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private String num1, num2, operator;

    public SwingCalculator() {
        setTitle("Swing Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        num1 = num2 = operator = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            textField.setText(textField.getText() + command);
        } else if (command.equals("C")) {
            textField.setText("");
            num1 = num2 = operator = "";
        } else if (command.equals("=")) {
            if (!num1.isEmpty() && !operator.isEmpty()) {
                num2 = textField.getText();
                double result = calculate(Double.parseDouble(num1), Double.parseDouble(num2), operator);
                textField.setText(String.valueOf(result));
                num1 = "";
                operator = "";
            }
        } else {
            if (num1.isEmpty()) {
                num1 = textField.getText();
                operator = command;
                textField.setText("");
            }
        }
    }

    private double calculate(double n1, double n2, String op) {
        switch (op) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/": return n2 != 0 ? n1 / n2 : 0;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        SwingCalculator calculator = new SwingCalculator();
        calculator.setVisible(true);
    }
}
