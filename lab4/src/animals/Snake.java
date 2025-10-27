package animals;

public class Snake extends Animal {

    public Snake(String name) {
        super(name, 0);
    }

    public String getDescription() {
        return "To " + getName() + ", wąż bez nóg.";
    }
}
