package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Comparator;

import com.anish.monsters.QuickSorter;
import com.anish.monsters.Monster;
import com.anish.monsters.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Monster[] monsters;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        monsters = new Monster[256];
        Integer[] rand = new Integer[256];

        for(int i = 0; i < 256; i++){
            rand[i] = i;
        }
        Comparator<Integer> cmp = new MyComparator();
        Arrays.sort(rand, cmp);
        
        for (int i = 0; i < 256; i++) {
            int r = 0, b = 0, g = 0;
            if (i < (double) 256 / 3) {
                r = 255;
                g = (int) Math.ceil(255 * 3 * i / 256);
                b = 0;
            } else if (i < (double) 256 / 2) {
                r = (int) Math.ceil(750 - i * 250 * 6 / 256);
                g = 255;
                b = 0;
            } else if (i < (double) 256 * 2 / 3) {
                r = 0;
                g = 255;
                b = (int) Math.ceil(i * 250 * 6 / 256 - 750);
            } else if (i < (double) 256 * 5 / 6) {
                r = 0;
                g = (int) Math.ceil(1250 - i * 250 * 6 / 256);
                b = 255;
            } else {
                r = (int) Math.ceil(150 * i * 6 / 256 - 750);
                g = 0;
                b = 255;
            }
            monsters[rand[i]] = new Monster(new Color(r, g, b), i, world);
            world.put(monsters[rand[i]], 2 * (rand[i] % 16) + 8, 2 * (rand[i] / 16) + 8);
        }

        QuickSorter<Monster> q = new QuickSorter<>();
        q.load(monsters);
        q.sort();
         
        sortSteps = this.parsePlan(q.getPlan());
    }

    static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Math.random() < 0.5 ? 1 : -1;
        }
    }    

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[] monsters, String step) {
        String[] couple = step.split("<->");
        getBroByRank(monsters, Integer.parseInt(couple[0])).swap(getBroByRank(monsters, Integer.parseInt(couple[1])));
    }

    private Monster getBroByRank(Monster[] monsters, int rank) {
        for (Monster mon : monsters) {
            if (mon.getRank() == rank) {
                return mon;
            }
        }
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
            this.execute(monsters, sortSteps[i]);
            i++;
        }

        return this;
    }

}
