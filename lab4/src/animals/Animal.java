package animals;

public class Animal {
    String name;
    int legs;

    // Constructor to initialize name and legs
    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }

    // Getter and Setter methods for name and legs
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

    // Method to return a description of the animal
    public String getDescription() {
        return "To  " + name + " z " + legs + " nogami.";
    }
}
