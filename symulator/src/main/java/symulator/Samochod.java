package symulator;

public class Samochod {
    Pozycja pozycja;
    Silnik silnik;
    SkrzyniaBiegow skrzynia;

    public Samochod(Pozycja pozycja, Silnik silnik, SkrzyniaBiegow skrzynia) {
        this.pozycja = pozycja;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
    }
    public class Samochod {
        Pozycja pozycja;
        Silnik silnik;
        SkrzyniaBiegow skrzynia;

        public Samochod(Pozycja pozycja, Silnik silnik, SkrzyniaBiegow skrzynia) {
            this.pozycja = pozycja;
            this.silnik = silnik;
            this.skrzynia = skrzynia;
        }

        public void włącz() {
            silnik.uruchom();
        }

        public void wyłącz() {
            silnik.zatrzymaj();
            skrzynia.ustawBieg(0);
        }
    }

}


