package jsonother;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class JsonOtherGui extends JFrame {
    private JPanel mainPanel;

    private JTable table;

    private JTextField idField;
    private JTextField nomeField;
    private JTextField genereField;
    private JTextField inAttivitaField;
    private JTextField albumField;

    private JButton aggiornaButton;
    private JButton eliminaButton;

    private final ArtistsManager manager;

    public JsonOtherGui(ArtistsManager manager) {
        super("JsonOtherGui");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(800, 600);
        setLocationRelativeTo(null);

        setContentPane(mainPanel);

        this.manager = manager;
        updateTable(null);

        aggiornaButton.addActionListener(e -> updateTable(manager.getArtists()));
        eliminaButton.addActionListener(e -> elimina());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int id = (int) table.getValueAt(table.getSelectedRow(), 0);

                Artist artist = manager.getArtists().stream()
                        .filter(a -> a.getId() == id)
                        .findFirst()
                        .orElse(null);

                if (artist == null) {
                    return;
                }

                idField.setText(String.valueOf(artist.getId()));
                nomeField.setText(artist.getNome());
                genereField.setText(artist.getGenere());
                inAttivitaField.setText(String.valueOf(artist.isInAttivita()));
                albumField.setText(String.valueOf(artist.getNumeroAlbum()));
            }
        });
    }

    private void updateTable(List<Artist> artists) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.setColumnIdentifiers(Artist.getColumnIdentifiers());

        if (artists != null) {
            for (Artist artist : artists) {
                model.addRow(artist.getAsArray());
            }
        }

        table.setModel(model);
    }

    private void elimina() {
        int id = (int) table.getValueAt(table.getSelectedRow(), 0);

        manager.getArtists().removeIf(a -> a.getId() == id);
    }
}
