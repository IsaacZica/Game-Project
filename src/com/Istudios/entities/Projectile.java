package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;
import com.Istudios.world.World;

import java.awt.*;

public class Projectile extends Entity {
    private Color color;
    private double x;
    private double y;
    private double xSpeed;
    private double ySpeed;
    private int bounceCount = 0;
    private int maxBounce = 3;
    private int width;
    private int height;
    public int damage = 5;

    public Projectile(int x, int y, int width, int height, Color color, double xSpeed, double ySpeed) {
        super(x, y,width,height, null);
        this.color = color;
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.width = width;
        this.height = height;

    }

    public void tick() {

        if (World.thisTile(x + width,y)) {
            xSpeed *= -1;
            bounceCount++;
        } else if (World.thisTile(x-1,y)) {
            xSpeed *= -1;
            bounceCount++;
        }

        if (World.thisTile(x,y + height)) {
            ySpeed *= -1;
            bounceCount++;
        } else if (World.thisTile(x,y-1)) {
            ySpeed *=-1;
            bounceCount++;
        }



        if (bounceCount >= maxBounce) {
            Game.entities.remove(this);
        }

        x += xSpeed;
        y += ySpeed;

    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) (x - Camera.x),(int) (y + - Camera.y),this.width,this.height); ;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int getX() {
        return (int) x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return (int) y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(double YSpeed) {
        this.ySpeed = YSpeed;
    }
}
