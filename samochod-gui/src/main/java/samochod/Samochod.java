package samochod;

public class Samochod {
    private String model;
    private String nrRejestracyjny;
    private int waga;
    private boolean stanWlaczenia;
    private int predkoscMax = 180;
    private SkrzyniaBiegow skrzynia = new SkrzyniaBiegow(6);
    private Silnik silnik = new Silnik();
    private Sprzeglo sprzeglo = new Sprzeglo();

    // Konstruktor domyślny
    public Samochod() {
        this.model = "Nieznany";
        this.nrRejestracyjny = "---";
        this.waga = 1000;
    }

    // Konstruktor z parametrami (wykorzystywany w HelloController)
    public Samochod(String model, String nrRejestracyjny, int waga) {
        this.model = model;
        this.nrRejestracyjny = nrRejestracyjny;
        this.waga = waga;
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

    // Gettery dla nowych pól
    public String getModel() { return model; }
    public String getNrRejestracyjny() { return nrRejestracyjny; }
    public int getWaga() { return waga; }

    public Silnik getSilnik() { return silnik; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public SkrzyniaBiegow getSkrzynia() { return skrzynia; }

    // Metoda toString, aby ComboBox wyświetlał czytelną nazwę
    @Override
    public String toString() {
        return model + " [" + nrRejestracyjny + "]";
    }
}