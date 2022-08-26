package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.world.World;

import java.awt.image.BufferedImage;

public class FleshEnemy extends Enemy {

    private double speed = 0.2;
    private int health = 15;
    private int toughness = 0;
    private int damage = 5;

    public FleshEnemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public void tick() {
        if (getCenterX() < Game.player.getCenterX() &&
                World.isFree((int) (x+speed),(int) y) &&
                !isColliding((int) (x+speed),(int) y)) {
            x += speed;
        } else if (getCenterX() > Game.player.getCenterX() &&
                World.isFree((int) (x-speed),(int) y) &&
                !isColliding((int) (x-speed),(int) y)) {
            x -= speed;
        }

        if (getCenterY() < Game.player.getCenterY() &&
                World.isFree((int) x,(int) (y+speed)) &&
                !isColliding((int) x,(int) (y+speed))) {
            y += speed;
        } else if (getCenterY() > Game.player.getCenterY() &&
                World.isFree((int) x,(int) (y-speed)) &&
                !isColliding((int) x,(int) (y-speed))) {
            y -= speed;
        }
    }


}
