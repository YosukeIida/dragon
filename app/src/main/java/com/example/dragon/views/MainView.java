package com.example.dragon.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dragon.MainActivity;
import com.example.dragon.R;
import com.example.dragon.helpers.BaseView;
import com.example.dragon.models.GameCharacter;
import com.example.dragon.models.Lever;
import com.example.dragon.models.Player;
import com.example.dragon.models.World;


public class MainView extends BaseView {

    MainActivity mainActivity;
    ConstraintLayout constraintLayout;
    Context context;

    // 定数
    final int BLOCK_COUNT = 5;

    // リソース
    Bitmap backGroundImage;
    Bitmap playerRightImage;
    Bitmap playerLeftImage;
    Bitmap blockImage;
    Bitmap boardImage;
    Bitmap princessImage;
    Bitmap cageImage;
    Bitmap dragonImage;
    Bitmap leverLeftImage;
    Bitmap leverRightImage;

    // ビュー
    ImageViewBuilder imageViewBuilder;

    TextView gameClearTextView;
    TextView gameOverTextView;

    public MainView(Context context) {
        super(context);
        this.context = context;
        this.mainActivity = (MainActivity) context;

        // リソースの取得
        backGroundImage = loadImage(R.drawable.underground);
        playerRightImage = loadImage(R.drawable.player_right);
        playerLeftImage = loadImage(R.drawable.player_left);
        blockImage = loadImage(R.drawable.block);
        boardImage = loadImage(R.drawable.board);
        princessImage = loadImage(R.drawable.princess);
        cageImage = loadImage(R.drawable.cage);
        dragonImage = loadImage(R.drawable.dragon);
        leverLeftImage = loadImage(R.drawable.lever_left);
        leverRightImage = loadImage(R.drawable.lever_right);


        // ビューの取得
        constraintLayout = new ConstraintLayout(context);
        baseActivity.setContentView(constraintLayout);

        imageViewBuilder = new ImageViewBuilder(constraintLayout, context);

    }

    public void draw(World world) {
        // 横スクロール
        Player player = world.getPlayer();
        if (player.getX() < 700) {
            canvasBaseX = 0;
        } else if (player.getX() < 2150) {
            canvasBaseX = player.getX() - 700;
        } else {
            canvasBaseX = 1450;
        }

        // ImageBuilderをリセット
        imageViewBuilder.reset();

        // 背景を表示
        ImageView imageView = imageViewBuilder.getImageView();
        drawImage(0, 0, 3000, 700, backGroundImage, imageView);

        // プレーヤを表示
        drawPlayer(player);

        // ブロックを表示
        world.getBlocks().forEach(x -> drawCharacter(x, blockImage));
        world.getMovingBlocks().forEach(x -> drawCharacter(x, blockImage));
        drawCharacter(world.getBoard(), boardImage);
        drawCharacter(world.getPrincess(), princessImage);
        drawCharacter(world.getCage(), cageImage);
        drawCharacter(world.getDragon(), dragonImage);
        drawLever(world.getLever());

        // GameOver
        if (player.isDead()) {
            drawGameOver();
        }

        // GameClear
        if (player.isClear()) {
            drawGameClear();
        }
    }

    //======================
    // テキストビュー表示用の関数
    //======================
    public void drawGameClear() {
        if (gameClearTextView == null) {
            gameClearTextView = new TextView(context);
            constraintLayout.addView(gameClearTextView);
        }
        gameClearTextView.setTextSize(32);
        gameClearTextView.setTextColor(Color.WHITE);
        gameClearTextView.setText("Game Clear !!");
        drawTextViewCenter(canvasBaseX + 750, 350, gameClearTextView);
    }

    public void drawGameOver() {
        if (gameOverTextView == null) {
            gameOverTextView = new TextView(context);
            constraintLayout.addView(gameOverTextView);
        }
        gameOverTextView.setTextSize(32);
        gameOverTextView.setTextColor(Color.RED);
        gameOverTextView.setText("Game Over !!");
        drawTextViewCenter(canvasBaseX + 750, 350, gameOverTextView);
    }


    //======================
    // キャラクター表示用の関数
    //======================
    private void drawPlayer(Player player) {
        Bitmap playerImage = (player.getXSpeed() < 0) ? playerLeftImage : playerRightImage;
        drawCharacter(player, playerImage);
    }

    private void drawCharacter(GameCharacter c, Bitmap image) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            drawImage(x, y, xSize, ySize, image, imageView);
        }
    }

    private void drawLever(Lever lever) {
        ImageView imageView= imageViewBuilder.getImageView();
        int lx = lever.getX();
        int ly = lever.getY();
        int lxSize = lever.getxSize();
        int lySize = lever.getySize();
        if (lever.isOff()) {
            drawImage(lx, ly, lxSize, lySize, leverRightImage, imageView);
        } else {
            drawImage(lx, ly, lxSize, lySize, leverLeftImage, imageView);
        }
    }
}


