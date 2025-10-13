package Lab2;

import java.util.ArrayList;

public class zad1 {
    public static ArrayList<Integer> tab = new ArrayList<>();

    public static void main(String[] args) 
    {
        ArrayList<Integer> temp = new ArrayList<>();
        while (temp.size() < 5) 
        {
            int liczba = (int) (Math.random() * 49);
            if (!temp.contains(liczba)) 
            {
                temp.add(liczba);
            }
        }
        tab = temp;
        System.out.println("Wylosowane: " + tab);
    }
}
