package com.example.dragon.models;

public class Player extends GameCharacter {
    private int xSpeed = 0;
    private int ySpeed = 0;
    private int yAccel = -1;
    private boolean jumpFlag = false;
    private boolean deadFlag = false;
    private boolean clearFlag = false;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 96;
        this.ySize = 96;
        this.xSpeed = 0;
        this.ySpeed = 0;
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public void turnRight() {
        xSpeed = 5;
    }

    public void turnLeft() {
        xSpeed = -5;
    }

    public void stop() {
        xSpeed = 0;
    }

    public boolean isDead() {
        return deadFlag;
    }

    public void dead() {
        deadFlag = true;
    }
    public boolean isClear() { return clearFlag; }
    public void clear() { clearFlag = true; }

    public void jump() {
        if (jumpFlag == false) {
            ySpeed = 24;
            jumpFlag = true;
        }
    }


    public void move() {
        // 移動
        x += xSpeed;
        y += ySpeed;
        ySpeed += yAccel;

        // プレーヤーが画面からはみ出さないようにする
        if (x < 0) {
            x = 0;
        } else if (x > World.WIDTH - xSize) {
            x = World.WIDTH - xSize;
        }

        // Y座標が０以下になると死亡
        if (y <= 0) {
            dead();
        }

        // どこかに着地したらjumpFlagをfalse
        if (y < yMin) {
            ySpeed = 0;
            jumpFlag = false;
        }

        // 登録されたLimitCharacterに侵入しないように位置を調整
        correctPosition();
    }
}
