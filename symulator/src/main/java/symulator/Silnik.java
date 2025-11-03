package symulator;

public class Silnik extends Komponent {
    int maxObroty;
    int obroty;

    public Silnik(String producent, String model, int maxObroty) {
        super(producent, model);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

    public void uruchom() {
        obroty = 1000;
    }

    public void zatrzymaj() {
        obroty = 0;
    }
}


