package samochod;

public class Samochod {
    private boolean stanWlaczenia;
    private String nrRejest;
    private String model;
    private int predkoscMax;

    private SkrzyniaBiegow skrzynia = new SkrzyniaBiegow(6);
    private Silnik silnik = new Silnik();
    private Sprzeglo sprzeglo = new Sprzeglo();
    private Pozycja aktualnaPozycja;

    public void wlacz() {
        stanWlaczenia = true;
        silnik.uruchom();
    }

    public void wylacz() {
        stanWlaczenia = false;
        silnik.zatrzymaj();
        skrzynia.setAktualnyBieg(0);
    }

    public void jedzDo(Pozycja cel) {
    }

    public double getWaga() {
        return 0;
    }

    public int getAktPredkosc() {
        return 0;
    }

    public Pozycja getAktPozycja() {
        return aktualnaPozycja;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }

    public SkrzyniaBiegow getSkrzynia() {
        return skrzynia;
    }
}