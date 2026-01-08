package samochod;

public class Samochod {
    private boolean stanWlaczenia;
    private int predkoscMax = 180;
    private SkrzyniaBiegow skrzynia = new SkrzyniaBiegow(6);
    private Silnik silnik = new Silnik();
    private Sprzeglo sprzeglo = new Sprzeglo();

    public void wlacz() {
        stanWlaczenia = true;
        silnik.uruchom();
    }

    public void wylacz() {
        stanWlaczenia = false;
        silnik.zatrzymaj();
        skrzynia.setAktualnyBieg(0);
    }

    public boolean isStanWlaczenia() {
        return stanWlaczenia;
    }

    public int getAktPredkosc() {
        if (!stanWlaczenia) return 0;
        int bieg = skrzynia.getAktBieg();
        if (bieg <= 0) return 0;

        int obliczona = bieg * (silnik.getObroty() / 200);
        return Math.min(obliczona, predkoscMax);
    }

    public Silnik getSilnik() { return silnik; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public SkrzyniaBiegow getSkrzynia() { return skrzynia; }
}