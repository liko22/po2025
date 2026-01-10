package samochod;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;

    public SkrzyniaBiegow(int iloscBiegow) {
        this.iloscBiegow = iloscBiegow;
        this.aktualnyBieg = 0;
        this.nazwa = "Manual " + iloscBiegow + " biegów";
        this.waga = 50.0;
        this.cena = 4500.0;
    }

    public void zwiekszBieg() {
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > 0) {
            aktualnyBieg--;
        }
    }

    public int getAktBieg() {
        return aktualnyBieg;
    }

    public void setAktualnyBieg(int bieg) {
        if (bieg >= 0 && bieg <= iloscBiegow) {
            this.aktualnyBieg = bieg;
        }
    }
}