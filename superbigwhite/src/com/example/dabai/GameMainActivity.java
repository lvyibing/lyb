package com.example.dabai;

import com.dabai.database.R;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class GameMainActivity extends Activity {
	public static GameMainActivity instance;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	instance = this;
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new MySurfaceView(this));
        Toast.makeText(this, "重力感应小游戏", 2000).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_dabai, menu);
        return true;
    }
}
