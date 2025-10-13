package Lab2;

import java.util.ArrayList;

public class zad1 {
    public static void main(String[] args) {
        ArrayList<Integer> tab = new ArrayList<Integer>();
        while (tab.size() < 5) 
        {
            int liczba = (int)(Math.random() * 100);
            if (!tab.contains(liczba)) 
            {
                tab.add(liczba);
            }
        }
        System.out.println(tab);
    }
}
