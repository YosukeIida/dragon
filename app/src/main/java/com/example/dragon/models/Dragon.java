package com.example.dragon.models;

public class Dragon extends GameCharacter {

    private Player player;
    private  Board board;
    private  Lever lever;

    public Dragon(int x, int y){
        this.x=x;
        this.y=y;
        this.xSize=400;
        this.ySize=360;
        this.ySpeed=-1;
    }

    private boolean deadFlag = false;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setBoard(Board board) { this.board = board; }

    public boolean isDead() {
        return deadFlag;
    }

    public void move() {
        y = y + ySpeed;
        if (board.getY() < 0) {
            ySpeed = -3;
        }
        else if (y < 100 ) {
            ySpeed = 1;
        } else if (y > 700-ySize ) {
            ySpeed = -1;
        }
        if (overlap(player)) {
            player.dead();
        }
        if ( y + ySize < 0) {
            deadFlag = true;
        }


    }

}
