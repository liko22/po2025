package tasks;

import animals.Animal;
import animals.Dog;
import animals.Parrot;
import animals.Snake;

import java.util.Random;

public class Zoo {

    private Animal[] animals = new Animal[100];

    public Zoo() {
        fillZoo();
    }

    private void fillZoo() {
        Random random = new Random();
        String[] names = {"Rex", "Polly", "Kaa", "Buddy", "Coco", "Slither"};

        for (int i = 0; i < animals.length; i++) {
            int choice = random.nextInt(3);
            String name = names[random.nextInt(names.length)];

            switch (choice) {
                case 0:
                    animals[i] = new Dog(name);
                    break;
                case 1:
                    animals[i] = new Parrot(name);
                    break;
                case 2:
                    animals[i] = new Snake(name);
                    break;
            }
        }
    }

    public void printAnimals() {
        for (Animal animal : animals) {
            if (animal != null) {
                System.out.println(animal.getDescription());
            }
        }
    }

    public int sumLegs() {
        int sum = 0;
        for (Animal animal : animals) {
            if (animal != null) {
                sum += animal.getLegs();
            }
        }
        return sum;
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public void setAnimals(Animal[] animals) {
        this.animals = animals;
    }
}
