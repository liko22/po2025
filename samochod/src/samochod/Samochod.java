package samochod;

public class Samochod {
    private boolean stanWlaczenia;
    private String nrRejest;
    private String model;
    private int predkoscMax;

    private SkrzyniaBiegow skrzynia;
    private Silnik silnik;
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
}