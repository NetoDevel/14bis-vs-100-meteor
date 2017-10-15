package br.com.netodevel.flappy_fire_bird;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.io.InputStream;

public class InfiniteBackground {

    private Bitmap figure;
    private int height;
    private int width;
    private Rect src;
    private Rect first;
    private Rect second;

    private final int STEP = 5;

    public InfiniteBackground() {
        try {
            InputStream inputStream = GameParametersSingleton.assetManager.open("background.png");
            this.figure = BitmapFactory.decodeStream(inputStream);
            this.height = this.figure.getHeight();
            this.width = this.figure.getWidth();

            this.src = new Rect(0, 0, width, height);
            this.first = new Rect();
            this.second = new Rect();


        } catch (Exception e) {
            Log.d("InfiniteBackground", "Error: " + e.getMessage());
        }
    }


    public void updateDistortion() {
        setWidth((int)(getWidth() * GameParametersSingleton.DISTORTION));
        setHeight((int)(getHeight() * GameParametersSingleton.DISTORTION));

        first.left = 0;
        first.top = 0;
        first.right = width;
        first.bottom = height;


        second.left = width;
        second.right = second.left + width;
        second.top = 0;
        second.bottom = height;
    }

    public void update() {
        int stepDistorcion = (int)(STEP * GameParametersSingleton.DISTORTION);

        first.left -= stepDistorcion;
        first.right -= stepDistorcion;
        first.top = 0;
        first.bottom = getHeight();


        second.top = 0;
        second.left -= stepDistorcion;
        second.right -= stepDistorcion;
        second.bottom = getHeight();


        if (first.right <= 0) {
            first.left = second.right;
            first.right = second.right + width;
        }

        if (second.right <= 0) {
            second.left = first.right;
            second.right = first.right + width;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.figure, src, first, null);
        canvas.drawBitmap(this.figure, src, second, null);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}

