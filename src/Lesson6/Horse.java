package Lesson6;

public class Horse extends Animal {
    private static int horsesCount = 0;

    public Horse(String name, int runDistance, int jumpHeight, int swimDistance) {
        super("Конь", name, runDistance, jumpHeight, swimDistance);
        horsesCount++;
    }

    public Horse(String name) {
        super("Конь", name, 1500, 300, 100);
        horsesCount++;
    }

    public Horse() {
        super("Конь", "", 1500, 300, 100);
        horsesCount++;
   }

    @Override
    public void sound() {
        System.out.println(type + " " + name + " ржёт и мечет!");
    }

    public static int getHorsesCount() {
        return horsesCount;
    }
}
