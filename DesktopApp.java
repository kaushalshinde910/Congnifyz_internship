import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DesktopApp extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel panel;
    private StringBuilder input = new StringBuilder();

    public DesktopApp() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            input.append(command);
            display.setText(input.toString());
        } else if (command.equals("C")) {
            input.setLength(0);
            display.setText("");
        } else if (command.equals("=")) {
            try {
                display.setText(eval(input.toString()));
                input.setLength(0);
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else {
            input.append(" " + command + " ");
            display.setText(input.toString());
        }
    }

    private String eval(String expression) {
        try {
            String[] tokens = expression.split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[2]);
            switch (tokens[1]) {
                case "+": return String.valueOf(a + b);
                case "-": return String.valueOf(a - b);
                case "*": return String.valueOf(a * b);
                case "/": return b != 0 ? String.valueOf(a / b) : "Error";
            }
        } catch (Exception e) {
            return "Error";
        }
        return "Error";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DesktopApp app = new DesktopApp();
            app.setVisible(true);
            System.out.println("Hello, kaushal here");    

        });
    }
}
