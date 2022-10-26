package jsonother;

public class Artist {
    private int id;
    private String nome;
    private String genere;
    private boolean inAttivita;
    private int numeroAlbum;

    private Artist() {

    }

    public Artist(int id, String nome, String genere, boolean inAttivita, int numeroAlbum) {
        this.id = id;
        this.nome = nome;
        this.genere = genere;
        this.inAttivita = inAttivita;
        this.numeroAlbum = numeroAlbum;
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
        return new Object[]{nome, genere, inAttivita, numeroAlbum};
    }
}
