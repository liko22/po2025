package animals;

public class Parrot extends Animal {

    public Parrot(String name) {
        super(name, 2);
    }

    public String getDescription() {
        return "To " + getName() + ", papuga z " + getLegs() + " nogami.";
    }
}
