import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverterApp {
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JLabel fromLabel;
    private JComboBox<String> fromCurrencyComboBox;
    private JLabel toLabel;
    private JComboBox<String> toCurrencyComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    private String[] currencies = {"USD", "EUR", "GBP", "INR"};
    private double[] exchangeRates = {1.0, 0.85, 0.73, 83.16};

    public CurrencyConverterApp() {
        frame = new JFrame("Currency Converte");
        frame.setLayout(new GridLayout(7, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        amountLabel = new JLabel("Amount:");
        frame.add(amountLabel);

        amountTextField = new JTextField();
        frame.add(amountTextField);

        fromLabel = new JLabel("From:");
        frame.add(fromLabel);

        fromCurrencyComboBox = new JComboBox<>(currencies);
        frame.add(fromCurrencyComboBox);

        toLabel = new JLabel("To:");
        frame.add(toLabel);

        toCurrencyComboBox = new JComboBox<>(currencies);
        frame.add(toCurrencyComboBox);

        convertButton = new JButton("Convert");
        frame.add(convertButton);

        resultLabel = new JLabel();
        frame.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private void convertCurrency() {
        String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
        String toCurrency = (String) toCurrencyComboBox.getSelectedItem();

        int fromIndex = fromCurrencyComboBox.getSelectedIndex();
        int toIndex = toCurrencyComboBox.getSelectedIndex();

        double amount = Double.parseDouble(amountTextField.getText());
        double convertedAmount = amount * (exchangeRates[toIndex] / exchangeRates[fromIndex]);

        DecimalFormat df = new DecimalFormat("#.##");
        resultLabel.setText(df.format(amount) + " " + fromCurrency + " = " +
                df.format(convertedAmount) + " " + toCurrency);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverterApp();
            }
        });
    }
}
