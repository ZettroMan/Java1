package Lesson8;

public class Winline {
    private int startX;
    private int startY;
    private int stepX;
    private int stepY;
    private int length;

    public Winline() {
        clear();
    }

    public void clear() {
        startX = -1;
        startY = -1;
        stepX = 0;
        stepY = 0;
        length = 0;
    }

    public void set(int startY, int startX, int stepY, int stepX, int length) {
        this.startX = startX;
        this.startY = startY;
        this.stepX = stepX;
        this.stepY = stepY;
        this.length = length;
    }


    public boolean contains(int cellY, int cellX) {
        for(int y = startY, x = startX, i = 0; i < length; y += stepY, x += stepX, i++) {
            if((y == cellY) && (x == cellX)) return true;
        }
        return false;
    }
}
