import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverterApp extends JFrame implements ActionListener {
    private JTextField amountField, resultField;
    private JComboBox<String> fromCurrency, toCurrency;
    private JButton convertButton;

    public CurrencyConverterApp() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("From Currency:"));
        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY"});
        add(fromCurrency);

        add(new JLabel("To Currency:"));
        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY"});
        add(toCurrency);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = fromCurrency.getSelectedItem().toString();
            String to = toCurrency.getSelectedItem().toString();
            double rate = getExchangeRate(from, to);
            double result = amount * rate;
            resultField.setText(String.format("%.2f", result));
        } catch (Exception ex) {
            resultField.setText("Error");
        }
    }

    private double getExchangeRate(String from, String to) {
        try {
            String apiKey = "YOUR_API_KEY";
            String urlStr = "https://api.exchangerate-api.com/v4/latest/" + from;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            return json.getJSONObject("rates").getDouble(to);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterApp app = new CurrencyConverterApp();
            app.setVisible(true);
            System.out.println("Hello welcome to kaushal's CurrencyConverterApp");    
        });
    }
}
