package dbhouses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HousesGui extends JFrame {
    private JPanel mainPanel;
    private JTable outTable;
    private JTextField idField;
    private JTextField locationField;
    private JTextField rentableField;
    private JTextField priceField;
    private JTextField availableField;
    private JTextField renterNameField;
    private JTextField queryField;
    private JButton loadQueryButton;

    private final SQLConnection sqlConnection;

    public HousesGui(SQLConnection sqlConnection) {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(800, 600);
        setLocationRelativeTo(null);

        updateTable(null);

        this.sqlConnection = sqlConnection;

        loadQueryButton.addActionListener(e -> loadQuery());
    }

    private void loadQuery() {
        String query = queryField.getText();

        try {
            ResultSet set = sqlConnection.executeStatement(query);

            List<House> houses = new ArrayList<>();

            while (set.next()) {
                houses.add(House.fromResultsSet(set));
            }

            updateTable(houses);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(List<House> houses) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.setColumnIdentifiers(House.getColumnIdentifiers());

        if (houses != null) {
            houses.stream()
                    .map(House::getAsArray)
                    .forEach(model::addRow);
        }

        outTable.setModel(model);
    }
}
