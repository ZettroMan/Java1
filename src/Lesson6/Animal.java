package Lesson6;

public class Animal {

    String type;       //вид животного
    String name;       //кличка животного
    int runDistance;   //максимальная дистанция для бега
    int jumpHeight;    //максимальная высота прыжка (в сантиметрах)
    int swimDistance;  //максимальная дистанция для плавания
    int hungryLevel;   //уровень голода в %, 100% - абсолютно сытое, если <= 20% - значит животное хочет есть)
    private static int animalsCount = 0;  //для подсчета общего количества созданных животных

    //конструктор по умолчанию, животное не понятно какого типа, без имени и ничего не умеет
    public Animal() {
        runDistance = 0;
        jumpHeight = 0;
        swimDistance = 0;
        type = "Животное";
        name = "Без имени";
        hungryLevel = 100;
        animalsCount++;
    }

    //конструктор для животного с именем
    public Animal(String type, String name, int runDistance, int jumpHeight, int swimDistance) {
        this.type = type;
        this.name = name;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance = swimDistance;
        hungryLevel = 100;
        animalsCount++;
    }

    //конструктор для безымянного животного
    public Animal(String type, int runDistance, int jumpHeight, int swimDistance) {
        this.type = type;
        this.name = "Без имени";
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance = swimDistance;
        hungryLevel = 100;
        animalsCount++;
    }

    //дать животному какое-нибудь имя
    public void setName(String name) {
        this.name = name;
    }

    //пробежать дистанцию
    public void run(int distance) {
        if (isHungry()) {
            askToFeed();
            return;
        }
        if(runDistance == 0) {
            System.out.println("Хозяин, ты издеваешься? Я не умею бегать!");
            return;
        }
        if (distance <= runDistance) {
            System.out.println(type + " " + name + " пробежал(-о,-а) дистанцию");
            hungryLevel -= (hungryLevel / 2) * ((float) distance / runDistance);
        } else {
            System.out.println(type + " " + name + " не справился(-ась,-ось) с заданием");
            hungryLevel /= 2;
        }
    }

    public void info() {
        System.out.println(type + " " + name + ":  RUN = " + runDistance +
                "\tJUMP = " + jumpHeight + "\tSWIM = " + swimDistance + (isHungry() ? "\tHUNGRY!!! (" : "\tFULL (") + hungryLevel + "%)");
    }

    //проплыть дистанцию
    public void swim(int distance) {
        if (isHungry()) {
            askToFeed();
            return;
        }
        if(swimDistance == 0) {
            System.out.println("Хозяин, ты издеваешься? Я не умею плавать!");
            return;
        }
        if (distance <= swimDistance) {
            System.out.println(type + " " + name + " проплыл(-о,-а) дистанцию");
            hungryLevel -= (hungryLevel / 2) * ((float) distance / swimDistance);
        } else {
            System.out.println(type + " " + name + " не справился(-ась,-ось) с заданием");
            hungryLevel /= 2;
        }
    }

    //подпрыгнуть на некоторую высоту
    public void jump(int height) {
        if (isHungry()) {
            askToFeed();
            return;
        }
        if(jumpHeight == 0) {
            System.out.println("Хозяин, ты издеваешься? Я не умею прыгать!");
            return;
        }
        if (height <= jumpHeight) {
            System.out.println(type + " " + name + " перепрыгнул(-о,-а) препятствие");
            hungryLevel -= (hungryLevel / 5) * ((float) height / jumpHeight);
        } else {
            System.out.println(type + " " + name + " не справился(-ась,-ось) с заданием");
            hungryLevel /= 2;
        }
    }

    //издать какой-нибудь звук
    public void sound() {
        System.out.println(type + " " + name + " издало какой-то звук)");
    }

    //накормить животненькое
    public void feed() {
        hungryLevel += 30;
        if (hungryLevel > 100) {
            hungryLevel = 100;
        }
        sound();
        System.out.println("Спасибо!!!!");
    }

    //возвращает true, если животное голодно
    public boolean isHungry() {
        return hungryLevel <= 30;
    }

    public void askToFeed() {
        sound();
        System.out.println(type + " " + name + " хочет есть!");
    }

    public void readyForAction() {
        sayHello();
        if (isHungry()) {
            System.out.println("Я очень проголодался. Накорми меня, пожалуйста!");
        } else {
            System.out.println("Чего прикажешь, хозяин?");
        }
    }

    public void sayHello() {
        System.out.println("Привет! Я " + type + " " + name + ".");
    }


    public static int getAnimalsCount() {
        return animalsCount;
    }

    public void learnToRun(int runDistance) {
        if (isHungry()) {
            askToFeed();
            return;
        }
        this.runDistance = runDistance;
        hungryLevel /= 2;
    }

    public void learnToJump(int jumpHeight) {
        if (isHungry()) {
            askToFeed();
            return;
        }
        this.jumpHeight = jumpHeight;
        hungryLevel /= 2;
    }

    public void learnToSwim(int swimDistance) {
        if (isHungry()) {
            askToFeed();
            return;
        }
        this.swimDistance = swimDistance;
        hungryLevel /= 2;
    }
}
