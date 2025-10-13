import java.util.Random;

public class lotto {

    public static void main(String[] args) {
        int ILOSC_LICZB = 6;
        int MAX_LICZBA = 49;
        int[] liczby = new int[ILOSC_LICZB];
        Random random = new Random();

        int i = 0;
        while (i < ILOSC_LICZB) {
            int nowaLiczba = random.nextInt(MAX_LICZBA) + 1;
            boolean powtorzona = false;
            for (int j = 0; j < i; j++) {
                if (liczby[j] == nowaLiczba) {
                    powtorzona = true;
                    break;
                }
            }
            if (!powtorzona) {
                liczby[i] = nowaLiczba;
                i++;
            }
        }
        System.out.println("Wylosowane liczby Lotto:");
        for (int liczba : liczby) {
            System.out.print(liczba + " ");
        }
    }
}
