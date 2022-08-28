package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public static BufferedImage HEALTHRESTORE_EN = Game.spritesheet.getSprite(6*16, 0, 16, 16);
    public static BufferedImage MANARESTORE_EN = Game.spritesheet.getSprite(6*16, 16, 16, 16);
    public static BufferedImage SIMPLEENEMY_EN = Game.spritesheet.getSprite(7*16, 16, 16, 16);
    public static BufferedImage WAND_EN = Game.spritesheet.getSprite(7*16, 0, 16, 16);


    protected double x;
    protected double y;
    protected int width;
    protected int height;

    private final BufferedImage sprite;


    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        setX(x);
        setY(y);
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getCenterX() {
        return x + width / 2;
    }

    public double getCenterY() {
        return y + height / 2;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void tick() {

    }

    public void drawSprite(BufferedImage sprite, int x, int y, Graphics g) {
        g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
    }

    public void render(Graphics g) {
        drawSprite(sprite,getX(),getY(),g);
    }


}
