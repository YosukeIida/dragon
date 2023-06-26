package com.example.dragon.models;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class World {
    // 定数
    public static final int WIDTH = 3000;

    // モデル
    Player player;
    List<Block> blocks;
    Board board;
    Princess princess;
    Cage cage;
    Dragon dragon;
    Lever lever;
    List<MovingBlock> movingBlocks;

    public World() {
        // モデルの取得
        player = new Player(0, 100);

        blocks = new LinkedList<Block>();
        blocks.add(new Block(0, 0));
        blocks.add(new Block(0,0));
        blocks.add(new Block(100,0));
        blocks.add(new Block(200,0));
        blocks.add(new Block(1400,0));
        blocks.add(new Block(1500,0));
        blocks.add(new Block(2600,0));
        blocks.add(new Block(2700,0));
        blocks.add(new Block(2800,0));
        blocks.add(new Block(2900,0));

        movingBlocks = new LinkedList<MovingBlock>();
        movingBlocks.add(new MovingBlock(400, 100, 0, 200));
        movingBlocks.add(new MovingBlock(750, 100, 0, 300));
        movingBlocks.add(new MovingBlock(1100, 100, 0, 400));

        board = new Board(1600, 80);

        princess = new Princess(2880, 100);

        cage = new Cage(2850, 100);

        dragon = new Dragon(2200, 200);

        lever = new Lever(2700, 100);

        // モデルの接続

        for (Block block : blocks) {
            player.addLimitCharacter(block);
        }

        for (MovingBlock movingBlock : movingBlocks) {
            player.addLimitCharacter(movingBlock);
        }
//        ※以下と同じ
//        for (int i=0; i<blocks.size(); i++) {
//            Block block = blocks.get(i);
//            player.addLimitCharacter(block);
//        }

        player.addLimitCharacter(board);
        princess.setPlayer(player);
        cage.setPlayer(player);
        dragon.setPlayer(player);
        lever.setPlayer(player);

        board.setLever(lever);
        dragon.setBoard(board);
        cage.setDragon(dragon);




    }

    public void move() {
        blocks.forEach(x -> x.move());
        movingBlocks.forEach(x -> x.move());
        board.move();
        player.move();
        princess.move();
        cage.move();
        dragon.move();
        lever.move();
    }

    public Player getPlayer() {
        return player;
    }

    public List<Block> getBlocks() {
        return blocks;
    }
    public List<MovingBlock> getMovingBlocks() {return movingBlocks; }

    public Board getBoard() { return board; }

    public Princess getPrincess() { return  princess; }
    public Cage getCage() { return cage; }
    public Dragon getDragon() { return dragon; }
    public Lever getLever() { return lever; }



}
