package Lesson6;

public class Dog extends Animal {
    private static int dogsCount = 0;

    public Dog(String name, int runDistance, int jumpHeight, int swimDistabce) {
        super("Пёс", name, runDistance, jumpHeight, swimDistabce);
        dogsCount++;
    }

    public Dog(String name) {
        super("Пёс", name, 500, 50, 10);
        dogsCount++;
    }

    public Dog()
    {
        super("Пёс", "", 500, 50, 10);
        dogsCount++;
    }

    @Override
    public void sound() {
        System.out.println(type + " " + name + " гавкнул!");
    }

    public static int getDogsCount() {
        return dogsCount;
    }
}
