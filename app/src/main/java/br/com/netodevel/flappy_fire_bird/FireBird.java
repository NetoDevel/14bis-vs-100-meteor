package br.com.netodevel.flappy_fire_bird;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.io.InputStream;

public class FireBird extends GameThings {

    public Bitmap figure;
    private Rect src;
    private Rect dst;

    private int spriteWidth;
    private int spriteHeight;

    private int currentSprite;

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

        } catch (Exception e) {
            Log.d("FireBird", "erro: " + e.getMessage());
        }
    }

    @Override
    public void update() {

        src.top = 0;
        src.bottom = spriteHeight;
        src.left = currentSprite * spriteWidth;
        src.right = src.left + spriteWidth;

        currentSprite = (currentSprite + 1)%10;

        dst.top = getY();
        dst.bottom = getX() + getHeight();
        dst.left = getX();
        dst.right = getX() + getWidth();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.figure, src, dst, null);
    }
}
