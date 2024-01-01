import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiFunction;
import java.util.HashMap;
import java.util.Map;

public class WechselkursRechner {
    private JFrame frame;
    private JTextField betragEingabe;
    private JComboBox<String> waehrungAuswahl;
    private JTextField ergebnisAnzeige;
    private BiFunction<Double, Double, Double> wechselkursRechner;
    private Map<String, Double> wechselkurse;

    public WechselkursRechner() {
        // Wechselkursrechner initialisieren
        wechselkursRechner = (betrag, wechselkurs) -> betrag * wechselkurs;

        // Wechselkurse initialisieren
        wechselkurse = new HashMap<>();
        wechselkurse.put("USD", 1.1);
        wechselkurse.put("GBP", 0.9);
        wechselkurse.put("CHF", 1.05);

        // Swing-Komponenten initialisieren
        frame = new JFrame("Wechselkursrechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(400, 200);

        JLabel betragLabel = new JLabel("Betrag in EUR:");
        betragLabel.setBounds(50, 10, 100, 20);
        frame.add(betragLabel);

        betragEingabe = new JTextField();
        betragEingabe.setBounds(50, 30, 100, 30);
        frame.add(betragEingabe);

        String[] waehrungen = {"USD", "GBP", "CHF"};
        waehrungAuswahl = new JComboBox<>(waehrungen);
        waehrungAuswahl.setBounds(200, 30, 100, 30);
        frame.add(waehrungAuswahl);

        JButton berechneButton = new JButton("Berechne");
        berechneButton.setBounds(150, 80, 100, 30);
        berechneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                berechneWechselkurs();
            }
        });
        frame.add(berechneButton);

        ergebnisAnzeige = new JTextField();
        ergebnisAnzeige.setBounds(50, 130, 250, 30);
        ergebnisAnzeige.setEditable(false);
        frame.add(ergebnisAnzeige);

        frame.setVisible(true);
    }

    private void berechneWechselkurs() {

        try {

            String betragText = betragEingabe.getText().replace(',', '.');
            double betrag = Double.parseDouble(betragText);

            String ausgewaehlteWaehrung = (String) waehrungAuswahl.getSelectedItem();
            double wechselkurs = wechselkurse.getOrDefault(ausgewaehlteWaehrung, 0.0);

            double umgerechneterBetrag = wechselkursRechner.apply(betrag, wechselkurs);

            ergebnisAnzeige.setText(String.format("%.2f %s", umgerechneterBetrag, ausgewaehlteWaehrung));

        } catch (NumberFormatException ex) {
            ergebnisAnzeige.setText("Ungültige Eingabe");
        }
    }

    public static void main(String[] args) {
        new WechselkursRechner();
    }
}
