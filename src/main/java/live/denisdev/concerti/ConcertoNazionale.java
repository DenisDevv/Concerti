package live.denisdev.concerti;

public class ConcertoNazionale extends Concerto {
    public ConcertoNazionale(String artista, String luogo, String data, Double prezzo) {
        super(artista, luogo, data, prezzo);
    }
    @Override
    public String toString() {
        return "Concerto Nazionale\n" + super.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof ConcertoNazionale) {
            ConcertoNazionale c = (ConcertoNazionale) o;
            return super.equals(c);
        } else {
            return false;
        }
    }
}
