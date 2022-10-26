package esapi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;

public class PostGui extends JFrame {
    private JPanel mainPanel;
    private JTable outTable;
    private JTextField userIdField;
    private JTextField postIdField;
    private JTextArea titleField;
    private JTextArea bodyField;
    private JTextField inputTitleField;
    private JButton loadButton;
    private JButton deleteButton;
    private JTextField inputUserIdField;
    private JTextField inputPostIdField;
    private JButton deleteAllButton;

    private final PostsManager postsManager;

    public PostGui() {
        super("Post API GUI");

        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(mainPanel);

        this.postsManager = new PostsManager();

        updateTable();

        loadButton.addActionListener(e -> loadPosts());
        deleteButton.addActionListener(e -> deletePost());
        deleteAllButton.addActionListener(e -> deleteAllPosts());

        outTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPost();
            }
        });
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(Post.getIdentifiers());

        postsManager.getLoadedPosts().stream()
                .sorted(Comparator.comparing(Post::getId))
                .map(Post::getAsArray)
                .forEach(model::addRow);

        outTable.setModel(model);
    }

    private void loadPosts() {
        LinkBuilder link = new LinkBuilder();

        try {
            String id = inputUserIdField.getText();

            if (id.equals("") || id.isBlank()) {
                throw new IllegalArgumentException();
            }

            link.fromUser(Integer.parseInt(id));
            inputUserIdField.setText("");
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Id Utente invalido", "ERRORE", JOptionPane.ERROR_MESSAGE);
        }

        try {
            String postId = inputPostIdField.getText();

            if (postId.equals("") || postId.isBlank()) {
                throw new IllegalArgumentException();
            }

            link.withId(Integer.parseInt(postId));
        } catch (IllegalArgumentException ignored) {

        } catch (Exception ignored) {
            JOptionPane.showMessageDialog(this, "Id Post invalido", "ERRORE", JOptionPane.ERROR_MESSAGE);
        }

        try {
            String title = inputTitleField.getText();

            if (title.equals("") || title.isBlank()) {
                throw new IllegalArgumentException();
            }

            link.withTitle(title);
            inputTitleField.setText("");
        } catch (IllegalArgumentException ignored) {

        } catch (Exception ignored) {
            JOptionPane.showMessageDialog(this, "Titolo invalido", "ERRORE", JOptionPane.ERROR_MESSAGE);
        }

        postsManager.loadPosts(link);

        updateTable();
    }

    private void deletePost() {
        int row = outTable.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Nessun post selezionato", "ERRORE", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) outTable.getValueAt(row, 1);

        postsManager.deletePost(id);
        JOptionPane.showMessageDialog(this, "Post eliminato", "INFO", JOptionPane.INFORMATION_MESSAGE);

        clearOutputFields();
        updateTable();
    }

    private void deleteAllPosts() {
        postsManager.deleteAllPosts();
        updateTable();
    }

    private void showPost() {
        int row = outTable.getSelectedRow();

        int id = (int) outTable.getValueAt(row, 1);

        Post post = postsManager.getById(id);

        if (post == null) {
            JOptionPane.showMessageDialog(this, "Errore imprevisto", "ERRORE", JOptionPane.ERROR_MESSAGE);
            return;
        }

        userIdField.setText(String.valueOf(post.getUserId()));
        postIdField.setText(String.valueOf(post.getId()));
        titleField.setText(post.getTitle());
        bodyField.setText(post.getBody());
    }

    private void clearOutputFields() {
        userIdField.setText("");
        postIdField.setText("");
        titleField.setText("");
        bodyField.setText("");
    }
}
