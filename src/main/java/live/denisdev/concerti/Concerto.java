package live.denisdev.concerti;

public abstract class Concerto {
    private String artista;
    private String luogo;
    private String data;
    private Double prezzo;
    public Concerto() {
        this.artista = "";
        this.luogo = "";
        this.data = "";
        this.prezzo = 0.0;
    }
    public Concerto(String artista, String luogo, String data, Double prezzo) {
        this.artista = artista;
        this.luogo = luogo;
        this.data = data;
        this.prezzo = prezzo;
    }
    public String getArtista() {
        return artista;
    }
    public String getLuogo() {
        return luogo;
    }
    public String getData() {
        return data;
    }
    public Double getPrezzo() {
        return prezzo;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
    @Override
    public String toString() {
        return "Artista: " + artista + "\nLuogo: " + luogo + "\nData: " + data + "\nPrezzo: " + prezzo;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Concerto) {
            Concerto c = (Concerto) o;
            return artista.equals(c.artista) && luogo.equals(c.luogo) && data.equals(c.data) && prezzo.equals(c.prezzo);
        } else {
            return false;
        }
    }
}
