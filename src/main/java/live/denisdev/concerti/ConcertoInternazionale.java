package live.denisdev.concerti;

public class ConcertoInternazionale extends Concerto {
    public ConcertoInternazionale(String artista, String luogo, String data, Double prezzo) {
        super(artista, luogo, data, prezzo);
    }
    @Override
    public String toString() {
        return "Concerto Internazionale\n" + super.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof ConcertoInternazionale) {
            ConcertoInternazionale c = (ConcertoInternazionale) o;
            return super.equals(c);
        } else {
            return false;
        }
    }
}
