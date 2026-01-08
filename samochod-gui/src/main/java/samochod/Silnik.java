package samochod;

public class Silnik extends Komponent {
    private int obroty;
    private final int MAX_OBROTY = 6300;

    public void uruchom() {
        this.obroty = 800;
    }

    public void zatrzymaj() {
        this.obroty = 0;
    }

    public void zwiekszObroty() {
        if (obroty > 0 && obroty < MAX_OBROTY) {
            obroty += 500;
        }
    }

    public void zmniejszObroty() {
        if (obroty > 800) {
            obroty -= 500;
        } else if (obroty > 0) {
            obroty = 800;
        }
    }

    public int getObroty() {
        return obroty;
    }
}