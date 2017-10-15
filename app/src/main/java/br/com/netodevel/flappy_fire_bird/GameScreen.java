package br.com.netodevel.flappy_fire_bird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;


public class GameScreen extends View implements Runnable {

    private boolean update;
    private int i;
    private Paint paint;
    private InfiniteBackground background;

    private FireBird fireBird;

    public GameScreen(Context context) {
        super(context);
        init();
    }

    public void init() {
        i = 0;
        update = true;
        paint = new Paint();
        paint.setColor(Color.BLACK);

        this.background = new InfiniteBackground();

        GameParametersSingleton.DISTORTION = (float) GameParametersSingleton.HEIGHT / this.background.getHeight();
        this.background.updateDistortion();

        fireBird = new FireBird();
        fireBird.setX(50);
        fireBird.setY(50);
        fireBird.updateDistortion();
    }

    public void update() {
        if (update) {
            this.background.update();
            this.fireBird.update();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.background.draw(canvas);
        this.fireBird.draw(canvas);
    }

    @Override
    public void run() {
        while (true) {
            try {
                update();
                postInvalidate();
                Thread.sleep(50);
            } catch (Exception e) {
                Log.d("GameScreen", "erro: " + e.getMessage());
            }
        }
    }

}
