package com.example.dragon.models;

public class Lever extends GameCharacter {

    private Player player;

    public Lever(int x, int y){
        this.x=x;
        this.y=y;
        this.xSize=100;
        this.ySize=100;
    }
    private boolean offFlag = false;

    public void setPlayer(Player player) { this.player = player; }

    public boolean isOff() {
        return offFlag;
    }

    public void move() {
        if (overlap(player)) {
            offFlag = true;
        }
    }

}
