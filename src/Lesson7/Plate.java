package Lesson7;

public class Plate {
    private int food;
    private int capacity;

    public Plate() {
        this(70, 70);
    }

    public Plate(int food) {
        this(food, 70);
    }

    public Plate(int food, int capacity) {
        this.capacity = capacity;
        if (food > capacity) {
            this.food = capacity;
        } else {
            this.food = food;
        }
    }

    public String toString() {
        return "Состояние тарелки:   вместимость - " + capacity + "\t\tколичество еды - " + food;
    }

    //добавляет опеределенное количество еды в тарелку
    public void addFood(int newFood) {
        food += newFood;
        if (food > capacity) {
            food = capacity;
        }
    }

    //наполняет тарелку едой до краев
    public void fillUp() {
        food = capacity;
    }

    //уменьшает количество еды в тарелке на количество amount, если ее там достаточно
    public boolean decreaseFood(int amount) {
        if (amount < 0) {
            return false;
        }
        if (food < amount) {
            return false;
        }
        food -= amount;
        return true;
    }

}
