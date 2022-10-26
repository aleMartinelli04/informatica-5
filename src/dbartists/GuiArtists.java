package dbartists;

import _hikaribase.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GuiArtists extends JFrame {
    private JPanel mainPanel;

    private JTextField idField;
    private JTextField nomeField;
    private JTextField genereField;
    private JTextField attivitaField;
    private JTextField numeroAlbumField;

    private JButton caricaButton;
    private JButton cancellaButton;
    private JButton updateButton;
    private JButton inserisciButton;

    private JTable table;

    private final Database database;

    public GuiArtists(Database db) {
        super("Gui Artists");

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(800, 500);
        setLocationRelativeTo(null);

        this.database = db;

        updateTable(null);

        caricaButton.addActionListener(e -> carica());
        cancellaButton.addActionListener(e -> cancella());
        updateButton.addActionListener(e -> update());
        inserisciButton.addActionListener(e -> inserisci());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int id = (int) table.getValueAt(table.getSelectedRow(), 0);

                database.useConnection(connection -> {
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM artisti WHERE id = ?");
                    statement.setInt(1, id);

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        Artista artista = Artista.fromResultSet(resultSet);

                        idField.setText(String.valueOf(artista.getId()));
                        nomeField.setText(artista.getNome());
                        genereField.setText(artista.getGenere());
                        attivitaField.setText(String.valueOf(artista.isInAttivita()));
                        numeroAlbumField.setText(String.valueOf(artista.getNumeroAlbum()));
                    }
                });
            }
        });
    }

    private void carica() {
        database.useConnection(connection -> {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM artisti");

            ResultSet resultSet = statement.executeQuery();

            List<Artista> artisti = new ArrayList<>();

            while (resultSet.next()) {
                artisti.add(Artista.fromResultSet(resultSet));
            }

            updateTable(artisti);

            resultSet.close();
            statement.close();
        });
    }

    private void cancella() {
        database.useConnection(connection -> {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM artisti WHERE id = ?");
            statement.setInt(1, Integer.parseInt(idField.getText()));

            statement.execute();
        });

        carica();
    }

    private void update() {
        try {
            database.useConnection(connection -> {
                PreparedStatement statement = connection.prepareStatement("UPDATE artisti SET nome = ?, genere = ?, inAttivita = ?, numeroAlbum = ? WHERE id = ?");
                statement.setString(1, nomeField.getText());
                statement.setString(2, genereField.getText());
                statement.setBoolean(3, Boolean.parseBoolean(attivitaField.getText()));
                statement.setInt(4, Integer.parseInt(numeroAlbumField.getText()));
                statement.setInt(5, Integer.parseInt(idField.getText()));

                statement.execute();
            });

            carica();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Errore nel formato dei campi", "ERORRE", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inserisci() {
        database.useConnection(connection -> {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO artisti (nome, genere, inAttivita, numeroAlbum) VALUES (?, ?, ?, ?)");
            statement.setString(1, nomeField.getText());
            statement.setString(2, genereField.getText());
            statement.setBoolean(3, Boolean.parseBoolean(attivitaField.getText()));
            statement.setInt(4, Integer.parseInt(numeroAlbumField.getText()));

            statement.execute();
        });

        carica();
    }

    private void updateTable(List<Artista> artists) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.setColumnIdentifiers(Artista.getColumnIdentifiers());

        if (artists != null) {
            for (Artista artist : artists) {
                model.addRow(artist.getAsArray());
            }
        }

        table.setModel(model);
    }
}
