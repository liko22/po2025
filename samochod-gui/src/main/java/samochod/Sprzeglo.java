package samochod;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla = false;

    public void wcisnij() {
        stanSprzegla = true;
    }

    public void zwolnij() {
        stanSprzegla = false;
    }

    public boolean isStanSprzegla() {
        return stanSprzegla;
    }
}