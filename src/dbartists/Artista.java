package dbartists;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Artista {
    private int id;
    private String nome;
    private String genere;
    private boolean inAttivita;
    private int numeroAlbum;

    public Artista(String nome, String genere, boolean inAttivita, int numeroAlbum) {
        this.nome = nome;
        this.genere = genere;
        this.inAttivita = inAttivita;
        this.numeroAlbum = numeroAlbum;
    }

    public static Artista fromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String cognome = resultSet.getString("genere");
        boolean inAttivita = resultSet.getBoolean("inAttivita");
        int numeroAlbum = resultSet.getInt("numeroAlbum");

        Artista artista = new Artista(nome, cognome, inAttivita, numeroAlbum);
        artista.setId(id);

        return artista;
    }

    public static String[] getColumnIdentifiers() {
        return new String[]{"id", "nome", "genere", "inAttivita", "numeroAlbum"};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public boolean isInAttivita() {
        return inAttivita;
    }

    public void setInAttivita(boolean inAttivita) {
        this.inAttivita = inAttivita;
    }

    public int getNumeroAlbum() {
        return numeroAlbum;
    }

    public void setNumeroAlbum(int numeroAlbum) {
        this.numeroAlbum = numeroAlbum;
    }

    public String toString() {
        return "Artista {" +
                " Nome ='" + nome + '\'' +
                ", Genere ='" + genere + '\'' +
                ", In attivitá ='" + inAttivita + '\'' +
                ", Numero Album='" + numeroAlbum + '\'' + '}';
    }

    public Object[] getAsArray() {
        return new Object[]{id, nome, genere, inAttivita};
    }
}
