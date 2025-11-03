package symulator;

public class Sprzeglo extends Komponent {
    boolean stanSprzegla;

    public Sprzeglo(String producent, String model) {
        super(producent, model);
        this.stanSprzegla = false;
    }

    public void wciśnij() {
        stanSprzegla = true;
    }

    public void zwolnij() {
        stanSprzegla = false;
    }
}
