package samochod;

public class Samochod {
    private String model;
    private String nrRejestracyjny;
    private int waga;
    private int predkoscMax;
    private boolean stanWlaczenia;
    private SkrzyniaBiegow skrzynia;
    private Silnik silnik = new Silnik();
    private Sprzeglo sprzeglo = new Sprzeglo();

    public Samochod() {
        this.model = "Nieznany";
        this.nrRejestracyjny = "---";
        this.waga = 1000;
        this.predkoscMax = 180;
        this.skrzynia = new SkrzyniaBiegow(6);
    }

    public Samochod(String model, String nrRejestracyjny, int waga, int predkoscMax, int iloscBiegow) {
        this.model = model;
        this.nrRejestracyjny = nrRejestracyjny;
        this.waga = waga;
        this.predkoscMax = predkoscMax;
        this.skrzynia = new SkrzyniaBiegow(iloscBiegow);
    }

    public void wlacz() {
        stanWlaczenia = true;
        silnik.uruchom();
    }

    public void wylacz() {
        stanWlaczenia = false;
        silnik.zatrzymaj();
        skrzynia.setAktualnyBieg(0);
    }

    public int getAktPredkosc() {
        if (!stanWlaczenia) return 0;
        int bieg = skrzynia.getAktBieg();
        if (bieg == 0) return 0;

        int mnoznik = Math.abs(bieg);
        int obliczona = mnoznik * (silnik.getObroty() / 200);
        return Math.min(obliczona, predkoscMax);
    }

    @Override
    public String toString() {
        return model + " [" + nrRejestracyjny + "]";
    }

    public String getModel() { return model; }
    public String getNrRejestracyjny() { return nrRejestracyjny; }
    public int getWaga() { return waga; }
    public boolean isStanWlaczenia() { return stanWlaczenia; }
    public Silnik getSilnik() { return silnik; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public SkrzyniaBiegow getSkrzynia() { return skrzynia; }
}