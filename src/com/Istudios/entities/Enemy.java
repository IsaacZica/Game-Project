package com.Istudios.entities;

import com.Istudios.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity{
    protected double speed ;
    protected int health ;
    protected int toughness ;
    protected int damage ;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite, double speed, int health, int toughness, int damage) {
        super(x, y, width, height, sprite);
        this.speed = speed;
        this.health = health;
        this.toughness = toughness;
        this.damage = damage;
    }

    public boolean isCollidingWithPlayer() {
        Rectangle currentEnemy = new Rectangle(this.getX() + maskX,this.getY() + maskY,maskWidth,maskHeight);
        Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), Game.player.width, Game.player.height);

        return currentEnemy.intersects(player);
    }

    public boolean isColliding(int nextX, int nextY) {
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

    public void die() {
        Game.entities.remove(this);
        return;
    }

    private void damage() {

    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
