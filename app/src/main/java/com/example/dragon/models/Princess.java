package com.example.dragon.models;

public class Princess extends GameCharacter {

    private Player player;

    public Princess (int x, int y) {
        this.x=x;
        this.y=y;
        this.xSize=96;
        this.ySize=96;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void move() {
        if (overlap(player) ) {
            player.clear();
        }
    }
}
