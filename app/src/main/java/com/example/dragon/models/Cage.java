package com.example.dragon.models;

public class Cage extends GameCharacter {

    private Player player;
    private Dragon dragon;

    public Cage(int x, int y){
        this.x=x;
        this.y=y;
        this.xSize=150;
        this.ySize=150;
        this.ySpeed=0;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }

    public void move() {
        y += ySpeed;
        if (overlap(player)) {
            player.dead();
        }
        if (dragon.isDead() && y < 1000) {
            ySpeed = 2;
        }
        else {
            ySpeed = 0;
        }
    }
}
