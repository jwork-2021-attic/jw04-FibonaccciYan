package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import com.anish.world.Player;
import com.anish.world.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Player player;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        
    } 

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Player player, String step) {
        String[] couple = step.split("<->");
        getBroByRank(player, Integer.parseInt(couple[0])).swap(getBroByRank(player, Integer.parseInt(couple[1])));
    }

    private Player getBroByRank(Player player, int rank) {
        
        return null;
    }

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
        if (i < this.sortSteps.length) {
            this.execute(player, sortSteps[i]);
            i++;
        }
        return this;
    }

}
