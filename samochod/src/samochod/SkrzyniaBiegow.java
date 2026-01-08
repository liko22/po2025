package samochod;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;
    private double aktualnePrzelozenie;

    public void zwiekszBieg() {
    }

    public void zmniejszBieg() {
    }

    public int getAktBieg() {
        return aktualnyBieg;
    }

    public double getAktPrzelozenie() {
        return aktualnePrzelozenie;
    }

    public void setAktualnyBieg(int bieg) {
        aktualnyBieg = bieg;
    }
}