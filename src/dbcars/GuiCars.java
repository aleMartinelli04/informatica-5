package dbcars;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuiCars extends JFrame {

    private JPanel mainPanel;
    private JTable table;
    private JTextField modelloField;
    private JTextField casaCostruttriceField;
    private JTextField coloreField;
    private JTextField alimentazioneField;
    private JSpinner cilindrataField;
    private JButton inserisciButton;
    private JButton caricaButton;

    private final SQLConnection sqlConnection;

    public GuiCars() throws SQLException, ClassNotFoundException {
        super("Cars");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(800, 500);
        setLocationRelativeTo(null);

        setContentPane(mainPanel);

        this.sqlConnection = new SQLConnection();

        updateTable(false);

        caricaButton.addActionListener(e -> updateTable(true));
        inserisciButton.addActionListener(e -> inserisciAutomobile());
    }

    private void updateTable(boolean showPopup) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(Automobile.getColumnNames());

        try (ResultSet resultSet = sqlConnection.getContent()) {
            if (!resultSet.next()) {
                throw new Exception("Non ci sono automobili salvate!");
            }

            do {
                Automobile automobile = Automobile.fromResultSet(resultSet);

                model.addRow(automobile.getAsArray());
            } while (resultSet.next());

            table.setModel(model);

            if (showPopup) {
                JOptionPane.showMessageDialog(this, "Caricamento avvenuto con successo", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inserisciAutomobile() {
        try {
            Automobile automobile = fromInputFields();

            sqlConnection.insert(automobile);

            // JOptionPane.showMessageDialog(this, "Automobile inserita!", "INFO", JOptionPane.INFORMATION_MESSAGE);
            resetInputFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Automobile fromInputFields() throws Exception {
        if (modelloField.getText().isBlank() ||  casaCostruttriceField.getText().isBlank() || coloreField.getText().isBlank() || (int) cilindrataField.getValue() <= 0 || alimentazioneField.getText().isBlank()) {
            throw new Exception("Campi invalidi");
        }
        return new Automobile(-1, modelloField.getText(), casaCostruttriceField.getText(),
                coloreField.getText(), (int) cilindrataField.getValue(), alimentazioneField.getText());
    }

    private void resetInputFields() {
        modelloField.setText("");
        casaCostruttriceField.setText("");
        coloreField.setText("");
        cilindrataField.setValue(0);
        alimentazioneField.setText("");
    }
}
