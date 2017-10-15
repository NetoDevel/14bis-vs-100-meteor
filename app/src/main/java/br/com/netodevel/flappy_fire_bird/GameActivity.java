package br.com.netodevel.flappy_fire_bird;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    private GameScreen gameScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setupParameters();
        this.gameScreen = new GameScreen(this);

        setContentView(this.gameScreen);

        Thread t = new Thread(this.gameScreen);
        t.start();
    }

    public void setupParameters() {
        GameParametersSingleton.ORIENTATION = GameParametersSingleton.PORTRAIT;
        GameParametersSingleton.WIDTH = getWindowManager().getDefaultDisplay().getWidth();
        GameParametersSingleton.HEIGHT = getWindowManager().getDefaultDisplay().getHeight();

        GameParametersSingleton.assetManager = getAssets();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

}
