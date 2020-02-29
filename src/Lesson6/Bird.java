package Lesson6;

public class Bird extends Animal {
    private static int birdsCount = 0;

    public Bird(String name, int runDistance, int jumpHeight, int swimDistabce) {
        super("Птичка", name, runDistance, jumpHeight, swimDistabce);
        birdsCount++;
    }

    public Bird(String name) {
        super("Птичка", name, 5, 20, 50);
        birdsCount++;
    }

    public Bird() {
        super("Птичка", "", 5, 20, 50);
        birdsCount++;
    }

    @Override
    public void sound() {
        System.out.println(type + " " + name + " чирикнула...");
    }

    public static int getBirdsCount() {
        return birdsCount;
    }
}
