package samochod;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;

    public SkrzyniaBiegow(int iloscBiegow) {
        this.iloscBiegow = iloscBiegow;
    }

    public void zwiekszBieg() {
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg = aktualnyBieg + 1;
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > -1) {
            aktualnyBieg = aktualnyBieg - 1;
        }
    }

    public int getAktBieg() {
        return aktualnyBieg;
    }

    public void setAktualnyBieg(int bieg) {
        aktualnyBieg = bieg;
    }
}