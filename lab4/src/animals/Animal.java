package animals;

public class Animal {
    String name;
    int legs;


    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public String getDescription() {
        return "To  " + name + " z " + legs + " nogami.";
    }
}
