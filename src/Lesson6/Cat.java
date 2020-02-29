package Lesson6;

public class Cat extends Animal {
    private static int catsCount = 0;

    public Cat(String name, int runDistance, int jumpHeight) {
        super("Кошак", name, runDistance, jumpHeight, 0);
        catsCount++;
    }

    public Cat(String name) {
        super("Кошак", name, 200, 200, 0);
        catsCount++;
    }

    public Cat() {
        super("Кошак", "", 200, 200, 0);
        catsCount++;
    }

    @Override
    public void sound() {
        System.out.println(type + " " + name + " мяукнул...");
    }

    public static int getCatsCount() {
        return catsCount;
    }
}
