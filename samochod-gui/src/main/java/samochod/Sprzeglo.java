package samochod;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla = false;

    public Sprzeglo() {
        this.nazwa = "cierne";
        this.waga = 15.0;
        this.cena = 1200.0;
    }

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