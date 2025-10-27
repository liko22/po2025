package tasks;

public class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        int totalLegs = zoo.sumLegs();
        System.out.println("Łączna liczba nóg w zoo: " + totalLegs);
    }
}
