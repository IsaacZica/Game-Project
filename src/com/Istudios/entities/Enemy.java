package com.Istudios.entities;

import com.Istudios.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity{

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public boolean isColliding(int nextX,int nextY) {
        Rectangle currentEnemy = new Rectangle(nextX,nextY,width,height);
        for (Enemy en : Game.enemies) {
            if (en == this) continue;
            Rectangle targetEnemy = new Rectangle(en.getX(), en.getY(), width, height);
            if (currentEnemy.intersects(targetEnemy)) {
                return true;
            }
        }
        return false;
    }
}
