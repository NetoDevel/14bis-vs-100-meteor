package br.com.netodevel.flappy_fire_bird;


import android.graphics.Canvas;

public abstract class GameThings {

    private int x;
    private int y;
    private int width;
    private int height;

    public abstract void update();
    public abstract void draw(Canvas canvas);

    public void updateDistortion() {
        width = (int)(width * GameParametersSingleton.DISTORTION);
        height = (int)(height * GameParametersSingleton.DISTORTION);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
