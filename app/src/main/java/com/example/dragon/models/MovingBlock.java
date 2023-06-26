package com.example.dragon.models;

public class MovingBlock extends Block {

    public MovingBlock(int x, int y, int yMin, int yMax) {
        super(x, y);
//        this.x = x;
//        this.y = y;
        this.xSize = 200;
        this.ySize = 300;
        this.ySpeed = 1;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public void move() {
        y = y + ySpeed;
        if ( y < yMin ) {
            ySpeed = 1;
        } else if (y > yMax ) {
            ySpeed = -1;
        }
    }



}
