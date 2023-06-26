package com.example.dragon.models;

public class Board extends GameCharacter{

    private Lever lever;
    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 1000;
        this.ySize = 20;
    }
    private int yMovement = 0;

    public void setLever(Lever lever) {
        this.lever = lever;
    }

    public void move() {
        if ( lever.isOff() && y > -20 ) {
            y--;
        }

    }

}
