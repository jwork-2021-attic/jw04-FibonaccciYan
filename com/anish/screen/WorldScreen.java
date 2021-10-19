package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.world.Player;
import com.anish.world.World;
import com.anish.world.Wall;

import asciiPanel.AsciiPanel;

import maze.MazeGenerator;

public class WorldScreen implements Screen {

    public static final int SIZE = 30;

    private World world;
    private Player player;
    private int[][] maze;
    String[] mazeSteps;

    public WorldScreen() {
        world = new World();
        player = new Player(new Color(255, 255, 0), world, 0, 0);
        world.put(player, 0, 0);

        MazeGenerator mazeGenerator = new MazeGenerator(SIZE);
        mazeGenerator.generateMaze();
        maze = mazeGenerator.getMaze();

        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                if(maze[i][j] == 0){
                    world.put(new Wall(world), i, j);
                }
            }
        }
        
    } 

    private boolean isValidMove(int xPos, int yPos){
        if(xPos >= 0 && xPos < SIZE && yPos >= 0 && yPos < SIZE && maze[xPos][yPos] != 0){
            return true;
        }
        return false;
    }

    // private String[] parsePlan(String plan) {
    //     return plan.split("\n");
    // }

    // private void execute(Player player, String step) {
    //     String[] couple = step.split("<->");
    // }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {
                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());
            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        int xPos = player.getxPos();
        int yPos = player.getyPos();
        switch(key.getKeyCode()){
            case 0x25: //left arrow
                if(isValidMove(xPos - 1, yPos)){
                    player.moveTo(xPos, yPos, xPos - 1, yPos);
                    player.setxPos(xPos - 1);
                    player.setyPos(yPos);
                }
                break;
            case 0x26://up arrow
                if(isValidMove(xPos, yPos - 1)){
                    player.moveTo(xPos, yPos, xPos, yPos - 1);
                    player.setxPos(xPos);
                    player.setyPos(yPos - 1);
                }
                break;
            case 0x27://right arrow
                if(isValidMove(xPos + 1, yPos)){
                    player.moveTo(xPos, yPos, xPos + 1, yPos);
                    player.setxPos(xPos + 1);
                    player.setyPos(yPos);
                }
                break;
            case 0x28://down arrow
                if(isValidMove(xPos, yPos + 1)){
                    player.moveTo(xPos, yPos, xPos, yPos + 1);
                    player.setxPos(xPos);
                    player.setyPos(yPos + 1);
                }
                break;
            default:
                break;
        }
        return this;
    }

}
