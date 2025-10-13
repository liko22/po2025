package Lab2;

import java.util.ArrayList;
import java.util.Scanner;

public class zad2 {
    public static void main(String[] args) 
    {
        System.out.println("Podaj 6 liczb: ");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> strzaly = new ArrayList<Integer>();
        while(strzaly.size()<6)
        {
                int x = (scanner.nextInt());
                if(!strzaly.contains(x))
                {
                    strzaly.add(x);
                }
        }
        scanner.close();
        System.out.println("Moje liczby: " + strzaly);

        zad1.main(null);
        ArrayList<Integer> trafione = new ArrayList<Integer>(strzaly);
        trafione.retainAll(zad1.tab);
        System.out.println("Trafione liczby: " + trafione);


    }
}
