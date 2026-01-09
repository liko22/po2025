package samochod;

public class Samochod extends Thread {
    private String model;
    private String nrRejestracyjny;
    private int waga;
    private int predkoscMax;
    private boolean stanWlaczenia;
    private SkrzyniaBiegow skrzynia;
    private Silnik silnik = new Silnik();
    private Sprzeglo sprzeglo = new Sprzeglo();
    private Pozycja pozycja = new Pozycja();
    private Pozycja cel;

    public Samochod() {
        this.model = "Nieznany";
        this.nrRejestracyjny = "---";
        this.waga = 1000;
        this.predkoscMax = 180;
        this.skrzynia = new SkrzyniaBiegow(6);
        this.pozycja.setX(0);
        this.pozycja.setY(0);
        this.start();
    }

    public Samochod(String model, String nrRejestracyjny, int waga, int predkoscMax, int iloscBiegow) {
        this.model = model;
        this.nrRejestracyjny = nrRejestracyjny;
        this.waga = waga;
        this.predkoscMax = predkoscMax;
        this.skrzynia = new SkrzyniaBiegow(iloscBiegow);
        this.pozycja.setX(0);
        this.pozycja.setY(0);
        this.start();
    }

    public void jedzDo(Pozycja nowaPozycja) {
        this.cel = nowaPozycja;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int predkosc = getAktPredkosc();
                if (predkosc > 0 && cel != null) {
                    double dx = cel.getX() - pozycja.getX();
                    double dy = cel.getY() - pozycja.getY();
                    double odleglosc = Math.sqrt(dx * dx + dy * dy);

                    if (odleglosc > 5) {
                        double krok = predkosc / 100.0;
                        pozycja.setX(pozycja.getX() + (dx / odleglosc) * krok);
                        pozycja.setY(pozycja.getY() + (dy / odleglosc) * krok);
                    } else {
                        cel = null;
                    }
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getAktPredkosc() {
        if (!stanWlaczenia) return 0;
        int bieg = skrzynia.getAktBieg();
        if (bieg <= 0) return 0;
        int mnoznik = bieg;
        int obliczona = mnoznik * (silnik.getObroty() / 200);
        return Math.min(obliczona, predkoscMax);
    }

    public void wlacz() {
        stanWlaczenia = true;
        silnik.uruchom();
    }

    public void wylacz() {
        stanWlaczenia = false;
        silnik.zatrzymaj();
        skrzynia.setAktualnyBieg(0);
        cel = null;
    }

    public String getModel() { return model; }
    public String getNrRejestracyjny() { return nrRejestracyjny; }
    public int getWaga() { return waga; }
    public boolean isStanWlaczenia() { return stanWlaczenia; }
    public Silnik getSilnik() { return silnik; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public SkrzyniaBiegow getSkrzynia() { return skrzynia; }
    public Pozycja getPozycja() { return pozycja; }
    @Override
    public String toString() { return model + " [" + nrRejestracyjny + "]"; }
}