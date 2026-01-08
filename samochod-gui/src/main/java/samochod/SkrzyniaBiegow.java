package samochod;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;

    public SkrzyniaBiegow(int iloscBiegow) {
        this.iloscBiegow = iloscBiegow;
    }

    public void zwiekszBieg() {
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > -1) {
            aktualnyBieg--;
        }
    }

    public int getAktBieg() {
        return aktualnyBieg;
    }

    public void setAktualnyBieg(int bieg) {
        this.aktualnyBieg = bieg;
    }
}