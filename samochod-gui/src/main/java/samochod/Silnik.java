package samochod;

public class Silnik extends Komponent {
    private int obroty;
    private final int MAX_OBROTY = 6300;

    public Silnik() {
        this.nazwa = "1.9 TDI";
        this.waga = 200.0;
        this.cena = 7000.0;
        this.obroty = 0;
    }

    public Silnik(String nazwa, double waga, double cena) {
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
        this.obroty = 0;
    }

    public void uruchom() {
        this.obroty = 800;
    }

    public void zatrzymaj() {
        this.obroty = 0;
    }

    public void zwiekszObroty() {
        if (obroty > 0) {
            if (obroty + 500 <= MAX_OBROTY) {
                obroty += 500;
            } else {
                obroty = MAX_OBROTY;
            }
        }
    }

    public void zmniejszObroty() {
        if (obroty > 800) {
            obroty -= 500;
            if (obroty < 800) obroty = 800;
        }
    }

    public int getObroty() {
        return obroty;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getWaga() {
        return waga;
    }

    public double getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return nazwa;
    }
}