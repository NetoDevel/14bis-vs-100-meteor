package br.com.netodevel.flappy_fire_bird;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.io.InputStream;

public class FireBird extends GameThings {

    public static final int MOVE_UP = 1;
    public static final int MOVE_DOWN = 0;
    public static final int STEP_UP = 3;
    public static final int STEP_DOWN = 6;

    public Bitmap figure;
    private Rect src;
    private Rect dst;

    private int spriteWidth;
    private int spriteHeight;
    private int currentSprite;
    private int direction;

    public FireBird() {
        try {
            InputStream is = GameParametersSingleton.assetManager.open("bird.png");
            this.figure = BitmapFactory.decodeStream(is);

            spriteWidth = this.figure.getWidth() / 10;
            spriteHeight = this.figure.getHeight();

            setWidth(spriteWidth);
            setHeight(spriteHeight);

            currentSprite = 0;

            src = new Rect(0, 0, getWidth(), getHeight());
            dst = new Rect();

            this.direction = MOVE_DOWN;

        } catch (Exception e) {
            Log.d("FireBird", "erro: " + e.getMessage());
        }
    }

    @Override
    public void update() {

        if (direction == MOVE_UP) {
            setY(getY() - (int)(STEP_UP * GameParametersSingleton.DISTORTION));
        } else {
            setY(getY() + (int)(STEP_DOWN * GameParametersSingleton.DISTORTION));
        }

        src.top = 0;
        src.bottom = spriteHeight;
        src.left = currentSprite * spriteWidth;
        src.right = src.left + spriteWidth;

        currentSprite = (currentSprite + 1)%10;

        dst.left   = super.getX();
        dst.top    = super.getY();
        dst.right  = dst.left + super.getWidth();
        dst.bottom = dst.top + super.getHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.figure, src, dst, null);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
