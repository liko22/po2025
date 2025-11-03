package symulator;

public class SkrzyniaBiegow extends Komponent {
    int aktualnyBieg;
    int iloscBiegow;

    public SkrzyniaBiegow(String producent, String model, int iloscBiegow) {
        super(producent, model);
        this.iloscBiegow = iloscBiegow;
        this.aktualnyBieg = 0;
    }

    public void zwiększBieg() {
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > 0) {
            aktualnyBieg--;
        }
    }

    public void ustawBieg(int bieg) {
        if (bieg >= 0 && bieg <= iloscBiegow) {
            aktualnyBieg = bieg;
        }
    }
}
