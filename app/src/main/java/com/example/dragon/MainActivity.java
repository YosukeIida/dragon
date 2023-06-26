package com.example.dragon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.dragon.helpers.BaseActivity;
import com.example.dragon.models.Player;
import com.example.dragon.models.World;
import com.example.dragon.views.MainView;

public class MainActivity extends BaseActivity {

    // モデル
    World world;

    // ビュー
    MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 加速度センサー有効
        gravityEnabled = true;

        // 横向き
        orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // モデル生成
        world = new World();

        // ビュー生成
        mainView = new MainView(this);
    }

    public void update() {
        // 加速度センサーで左右に移動
        Player player = world.getPlayer();
        if (accelerationController.y > 2) {
            player.turnRight();
        } else if (accelerationController.y < -2) {
            player.turnLeft();
        } else {
            player.stop();
        }

        // モデル更新
        world.move();

        // ビュー更新
        mainView.draw(world);
    }

    //===========
    // イベントリスナー
    //===========
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:    // 画面から指を離したら
                world.getPlayer().stop();
                break;
            case MotionEvent.ACTION_DOWN: // 画面をタップしたら
//                float tapx = event.getX();
//                float tapy = event.getY();
                world.getPlayer().jump();
                break;
        }
        return true;
    }
}