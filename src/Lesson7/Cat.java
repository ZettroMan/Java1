package Lesson7;

public class Cat {
    private String name;
    private boolean isFullUp;
    private int appetite;
    private int lives;

    public Cat()
    {
      this("Безымянный", 25);
    }

    public Cat(String name)
    {
      this(name, 25);
    }

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isFullUp = false;
        lives = 9;
    }

    public void eat(Plate plate) {
        if(isFullUp) {
            System.out.println("Кот " + name + " уже сыт!");
            return;
        }
        if(plate.decreaseFood(appetite)) {
            System.out.println("Кот " + name + " поел! Мяу!)");
            isFullUp = true;
        } else {
            System.out.println("Кот " + name + " расстроился. Ему не хватило еды! (");
            //стало жалко котиков, не стал дописывать...
//            lives--;
//            if(lives < 0) {
//                lives = 0;
//            }
        }
    }

    public void setHungry() {
        isFullUp = false;
    }

    public boolean isFullUp() {
        return isFullUp;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }
}
