package aplikasikonversisuhu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplikasiKonversiSuhu extends JFrame {
    private JTextField tfInput;
    private JLabel lblHasil;
    private JComboBox<String> cbFrom, cbTo;

    public AplikasiKonversiSuhu() {
        setTitle("Aplikasi Konversi Suhu");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(230, 230, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblInput = new JLabel("Masukkan Nilai Suhu:");
        lblInput.setFont(new Font("Arial", Font.BOLD, 16));
        tfInput = new JTextField();
        tfInput.setFont(new Font("Arial", Font.PLAIN, 16));
        tfInput.setHorizontalAlignment(JTextField.CENTER);
        tfInput.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 255), 2));

        JLabel lblFrom = new JLabel("Dari Skala:");
        lblFrom.setFont(new Font("Arial", Font.BOLD, 16));
        cbFrom = new JComboBox<>(new String[]{"Celcius", "Fahrenheit", "Reamur", "Kelvin"});
        cbFrom.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel lblTo = new JLabel("Ke Skala:");
        lblTo.setFont(new Font("Arial", Font.BOLD, 16));
        cbTo = new JComboBox<>(new String[]{"Celcius", "Fahrenheit", "Reamur", "Kelvin"});
        cbTo.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton btnConvert = new JButton("Konversi");
        btnConvert.setFont(new Font("Arial", Font.BOLD, 16));
        btnConvert.setBackground(new Color(100, 150, 255));
        btnConvert.setForeground(Color.WHITE);
        btnConvert.setFocusPainted(false);
        btnConvert.setBorder(BorderFactory.createLineBorder(new Color(50, 100, 255), 2));

        lblHasil = new JLabel("Hasil: ", JLabel.CENTER);
        lblHasil.setFont(new Font("Arial", Font.PLAIN, 18));
        lblHasil.setForeground(new Color(0, 0, 0));

        panel.add(lblInput);
        panel.add(tfInput);
        panel.add(lblFrom);
        panel.add(cbFrom);
        panel.add(lblTo);
        panel.add(cbTo);
        panel.add(btnConvert);
        panel.add(new JLabel());
        panel.add(lblHasil);

        add(panel);

        btnConvert.addActionListener(e -> konversiSuhu());

        tfInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        tfInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!tfInput.getText().isEmpty()) {
                    konversiSuhu();
                }
            }
        });
    }

    private void konversiSuhu() {
        try {
            String input = tfInput.getText();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Input tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double nilaiSuhu = Double.parseDouble(input);
            String dariSkala = (String) cbFrom.getSelectedItem();
            String keSkala = (String) cbTo.getSelectedItem();

            double hasil = convertTemperature(nilaiSuhu, dariSkala, keSkala);
            lblHasil.setText(String.format("Hasil: %.2f %s", hasil, keSkala));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double convertTemperature(double nilai, String from, String to) {
        double celcius;
        switch (from) {
            case "Fahrenheit":
                celcius = (nilai - 32) * 5 / 9;
                break;
            case "Reamur":
                celcius = nilai * 5 / 4;
                break;
            case "Kelvin":
                celcius = nilai - 273.15;
                break;
            default:
                celcius = nilai;
        }

        switch (to) {
            case "Fahrenheit":
                return celcius * 9 / 5 + 32;
            case "Reamur":
                return celcius * 4 / 5;
            case "Kelvin":
                return celcius + 273.15;
            default:
                return celcius;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiKonversiSuhu app = new AplikasiKonversiSuhu();
            app.setVisible(true);
        });
    }
}
