package jsones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JacksonGui extends JFrame {
    private JPanel mainPanel;
    private JTable outputTable;
    private JPanel showFieldsPanel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField permanentField;
    private JTextField addressField;
    private JTextField phoneFields;
    private JTextField roleField;
    private JTextField citiesField;
    private JTextArea propertiesField;
    private JButton caricaButton;
    private JButton cancellaButton;

    private final EmployeeManager employeeManager;
    private DefaultTableModel tableModel;

    public JacksonGui(EmployeeManager employeeManager) {
        setTitle("Jackson Reader");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(1000, 600);
        setLocationRelativeTo(null);

        setContentPane(mainPanel);

        this.employeeManager = employeeManager;
        updateTable();

        outputTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int rowIndex = outputTable.getSelectedRow();

                int id = (int) outputTable.getValueAt(rowIndex, 0);

                Employee selected = employeeManager.getEmployee(id);

                if (selected == null) {
                    return;
                }

                showEmployeeData(selected);
            }
        });

        caricaButton.addActionListener(e -> {
            try {
                employeeManager.loadFromFile();
                updateTable();
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this, "Errore nel caricamento", "ERRORE", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancellaButton.addActionListener(e -> {
            int rowIndex = outputTable.getSelectedRow();

            int id = (int) outputTable.getValueAt(rowIndex, 0);

            employeeManager.removeEmployee(id);

            updateTable();
        });
    }

    private void showEmployeeData(Employee employee) {
        idField.setText(String.valueOf(employee.getId()));
        nameField.setText(employee.getName());
        permanentField.setText(String.valueOf(employee.isPermanent()));
        addressField.setText(String.valueOf(employee.getAddress()));
        phoneFields.setText(Arrays.toString(employee.getPhoneNumbers()));
        roleField.setText(employee.getRole());
        citiesField.setText(String.valueOf(employee.getCities()));
        propertiesField.setText(
                employee.getProperties().entrySet()
                        .stream()
                        .map(entry -> entry.getKey() + ": " + entry.getValue())
                        .collect(Collectors.joining("\n")));
    }

    private void updateTable() {
        this.tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(Employee.getFieldsName());
        outputTable.setModel(tableModel);

        employeeManager.getEmployees().stream()
                .map(Employee::getAsArray)
                .forEach(tableModel::addRow);
    }
}
