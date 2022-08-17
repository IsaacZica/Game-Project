package com.Istudios.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    protected double x;
    protected double y;
    protected int width;
    protected int height;

    private BufferedImage sprite;

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


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(sprite,(int) this.getX(),(int) this.getY(), null);
    }
}
